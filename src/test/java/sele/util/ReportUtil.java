package sele.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

/**
 * This is the utility for generating HTML testing reports.
 * 
 * @author XiaoXue_Chen
 * 
 */
public class ReportUtil {

	public static void getReport() {
		try {
			FileUtils.copyFile(new File("conf" + File.separatorChar
					+ "ListSample.htm"), new File("target" + File.separatorChar
					+ "TestReport.htm"));
			// Files.copy(new File("conf"+File.separatorChar+"ListSample.htm"),
			// new File("target"+File.separatorChar+"TestReport.htm"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void count(int run, int fail, long duration) {
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
		String testEnvironment = " os: " + CfgLoader.os + ";	\t browser: "
				+ CfgLoader.browserVersion + ";	\t location: "
				+ CfgLoader.testLocation;
		String[] search = new String[] { "$ProjectName", "$Total", "$Passed",
				"$Failed", "$TestDate", "$Duration", "$TestEnvironment",
				"$Summary", "$Rate" };
		String[] replace = new String[] { projectName, String.valueOf(run),
				String.valueOf(run - fail), String.valueOf(fail), date,
				durationToString, testEnvironment, summary, rate };
		ReplaceContent("target" + File.separatorChar + "TestReport.htm",
				"target" + File.separatorChar + "TestReport.htm", search,
				replace);
	}

	private static String getExecuteDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(date);
	}

	public static void insertReportLine(String id, String taskname,
			String description, String expected, String result,
			String seleniumId, String comment) {
		String html = "   <tr>" + "\n" + "    <td>" + id + "</td>" + "\n"
				+ "    <td>" + taskname + "</td>" + "\n" + "    <td>"
				+ description + "</td>" + "\n" + "    <td>" + getExecuteTime()
				+ "</td>" + "\n" + "    <td>" + expected + "</td>" + "\n"
				+ "    <td>" + result + "</td>" + "\n" + "    <td>"
				+ seleniumId + "</td>" + "\n" + "    <td>" + comment + "</td>"
				+ "\n" + "   </tr>" + "\n";
		AppendContent("target" + File.separatorChar + "TestReport.htm", html);

	}

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

	private static void ReplaceContent(String sourcePath, String targetPath,
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
}
