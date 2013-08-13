package util.report;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import testcase.TestLinkCase;
import testcase.TestStep;
import util.CfgLoader;

/**
 * This is the utility for generating HTML testing reports.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class ReportUtil {


private static String testResultSamplePath = CfgLoader.testResultSamplePath;
private static String testResultPath = CfgLoader.testResultPath;
	
	private static int id = 0;

	/**
	 * Copy a empty ReportSample to " target " directory for reporting
	 */

	public static void getReport() {
		 try {
		 FileUtils.copyFile(new File(testResultSamplePath), new
		 File(testResultPath));
		
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
	}

	/**
	 * Prepare the values and update the report file.
	 * 
	 * @param run
	 * @param fail
	 * @param duration
	 */
	public static void updateValuePrepare(int run, int fail, long duration) {
		String durationToString = String.format(
				"%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes(duration),
				TimeUnit.MILLISECONDS.toSeconds(duration)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(duration)));
		String projectName = CfgLoader.projectName;
		String date = getExecuteDate();
		String summary = "  complete rate: 100%";
		String rate = String
				.format("%.2f", ((double) (run - fail) / run) * 100) + "%";
		String testEnvironment = " OS: " + CfgLoader.os + ";	\t Browser: "
				+ CfgLoader.browserVersion + ";	\t Location: "
				+ CfgLoader.testLocation;
		String[] search = new String[] { "$ProjectName", "$Total", "$Passed",
				"$Failed", "$TestDate", "$Duration", "$TestEnvironment",
				"$Summary", "$Rate" };
		String[] replace = new String[] { projectName, String.valueOf(run),
				String.valueOf(run - fail), String.valueOf(fail), date,
				durationToString, testEnvironment, summary, rate };
		UpdateContent(testResultPath, testResultPath, search, replace);
	}

	private static String getExecuteDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(date);
	}

	/**
	 * Prepare case line HTML　
	 * 
	 * @param id
	 * @param caseId
	 * @param taskname
	 * @param description
	 * @param result
	 * @param seleniumId
	 * @param comment
	 */
	public static void insertReportLine(String id, String caseId,
			String taskname, String description, String result,
			String seleniumId, String comment) {
		String html = "   <tr>" + "\n" + "    <td>" + id + "</td>" + "\n"
				+ "    <td>" + caseId + "</td>" + "\n" + "    <td>" + taskname
				+ "</td>" + "\n" + "    <td>" + description + "</td>" + "\n"
				+ "    <td>" + result + "</td>" + "\n" + "    <td>"
				+ getExecuteTime() + "</td>" + "\n" + "    <td>" + seleniumId
				+ "</td>" + "\n" + "    <td>" + comment + "</td>" + "\n"
				+ "   </tr>" + "\n";
		AppendContent(testResultPath, html);
	}

	
//	public static void insertTestSuiteResultLine(
//			String casename, String run, String pass,
//			String failed) {
//		String html = "   <tr>" + "\n" + "    <td>" + casename+ "</td>" + "\n"
//				+ "    <td>" + run + "</td>" + "\n" + "    <td>" + pass
//				+ "</td>" + "\n" + "    <td>" + failed + "</td>" + "\n"
//				+ "   </tr>" + "\n";
//		AppendContent(testResultPath, html);
//	}
	
	/**
	 * For appending content to a file.
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void AppendContent(String fileName, String content) {
		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String getExecuteTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		return fmt.format(date);

	}

	/**
	 * Replace the specific content in the target file.
	 * 
	 * @param sourcePath
	 * @param targetPath
	 * @param search
	 * @param replace
	 */
	private static void UpdateContent(String sourcePath, String targetPath,
			String[] search, String[] replace) {
		try {
			FileReader reader = new FileReader(sourcePath);
			char[] dates = new char[1024];
			int count = 0;
			StringBuilder sb = new StringBuilder();
			while ((count = reader.read(dates)) > 0) {
				String str = String.valueOf(dates, 0, count);
				sb.append(str);
			}
			reader.close();

			String str = sb.toString();
			for (int i = 0; i < search.length; i++) {
				str = str.replace(search[i], replace[i]);
			}
			FileWriter writer = new FileWriter(targetPath);
			writer.write(str.toCharArray());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get test result and report ｉｔ.
	 * 
	 * @param methodName
	 * @param ifPass
	 */
	public static void report(String methodName, boolean ifPass) {

		String caseid = methodName.split("\\$")[1];
		String id[] = caseid.split("_");
		TestStep step = new TestStep();
		if (!id[1].equals("0")) {
			step = TestCaseLoader.getCaseByid(Integer.parseInt(id[0]),
					Integer.parseInt(id[1]));
		}
		// TestLinkCase c= TestCaseLoader.getCaseByid(Integer.parseInt(id[0]));
		// insertReportLine(String.valueOf(getIndex()),"CB"+c.getExternalid()+"_"+step.getStepNumber(),
		// c.getName(),
		// step.getActions(), step.getExpectedresults(), ifPass==true?
		// "pass":"fail",
		// methodName, "none");
		TestLinkCase c = TestCaseLoader.getCaseByid(Integer.parseInt(id[0]));
		insertReportLine(String.valueOf(getIndex()), "CB" + c.getExternalid(),
				c.getTestSuitName(), c.getName(), ifPass == true ? "pass"
						: "fail", methodName, "none");
	}

	synchronized public static int getIndex() {
		id = id + 1;
		return id;
	}
	

}
