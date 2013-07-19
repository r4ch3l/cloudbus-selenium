package sele.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * This is the utility for sending the email after test There are three types:
 * SimpleEmail, HTMLEmail, MutiEmail
 * 
 * @author XiaoXue_Chen
 * 
 */
public class EmailUtil {
	private static Properties config = new Properties();
	private static String hostName;
	private static String authenticationName;
	private static String authenticationPwd;
	private static String sentFrom;
	private static String subject;
	private static String dateformat;

	static {
		hostName = CfgLoader.hostName;
		authenticationName = CfgLoader.authenticationName;
		authenticationPwd = CfgLoader.authenticationPwd;
		sentFrom = CfgLoader.sentFrom;
		subject = CfgLoader.subject;
		dateformat = CfgLoader.dateformat;
	}

	public static void sendHTMLEmail(String address) {
		try {

			HtmlEmail email = new HtmlEmail();
			email.setHostName(hostName);
			email.setAuthentication(authenticationName, authenticationPwd);
			email.setCharset("UTF-8");
			email.addTo(address);
			email.setFrom(sentFrom);// here must the same as AuthenticationName
			email.setSubject(subject);
			email.attach(getZipAsAttachement());
			// URL url = new
			// URL("https://cloudbus.tibco.com/images/tibco_cloudbus.png");
			// String cid = email.embed(url, "cloudbus logo");
			// set the html message
			email.setHtmlMsg(getHTMLReport());
			// set the alternative message
			// email.setTextMsg("Your email client does not support HTML messages");
			// send the email
			email.addCc(CfgLoader.ccTo);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sendSimpleEmail(String address, String content) {
		try {
			Date a = new Date(System.currentTimeMillis());
			DateFormat fmt = new SimpleDateFormat(dateformat);
			String str = "\n\n" + "POT autometion testing result : \n\n"
					+ content + "\nDate :  " + fmt.format(a)
					+ "\n\nBest Regards";

			// email sending
			SimpleEmail email = new SimpleEmail();
			email.setHostName(hostName);
			email.setAuthentication(authenticationName, authenticationPwd);
			email.setCharset("UTF-8");
			email.addTo(address);
			email.setFrom(sentFrom);// here must the same as AuthenticationName
			email.setSubject(subject);
			email.setMsg(str);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void sendMutiEmail(String address, String content) {
		try {
			Date a = new Date(System.currentTimeMillis());
			DateFormat fmt = new SimpleDateFormat(dateformat);
			String str = "\n\n" + "Autometion testing result : \n\n" + content
					+ "\nDate :  " + fmt.format(a) + "\n\nBest Regards";

			// email sending
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(hostName);
			email.setAuthentication(authenticationName, authenticationPwd);
			email.setCharset("UTF-8");
			email.addTo(address);
			email.setFrom(sentFrom);// here must the same as AuthenticationName
			email.setSubject(subject);
			email.setMsg(str);
			email.attach(getReportAsAttachement());
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private static String getHTMLReport() {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(
					"target" + File.separatorChar + "TestReport.htm")));
			String text;
			while ((text = input.readLine()) != null)
				buffer.append(text);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer.toString();

	}

	private static EmailAttachment getReportAsAttachement() {
		EmailAttachment report = new EmailAttachment();
		report.setPath("target" + File.separatorChar + "TestReport.htm");
		report.setDisposition(EmailAttachment.ATTACHMENT);
		report.setDescription("Test Result");
		return report;
	}

	private static EmailAttachment getZipAsAttachement() {
		compress("target", "target.zip");
		EmailAttachment zip = new EmailAttachment();
		zip.setPath("target.zip");
		zip.setDisposition(EmailAttachment.ATTACHMENT);
		zip.setDescription("Screen Shot");
		return zip;
	}

	public static void compress(String srcPathName, String targetPathName) {
		File zipFile = new File(targetPathName);
		File srcdir = new File(srcPathName);
		if (!srcdir.exists())
			throw new RuntimeException(srcPathName + "doesn't exists");
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		// fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
		// fileSet.setIncludes("**/*.java");
		fileSet.setExcludes("classes");// 排除哪些文件或文件夹
		fileSet.setExcludes("test-classes");// 排除哪些文件或文件夹
		zip.addFileset(fileSet);
		zip.execute();
	}

}
