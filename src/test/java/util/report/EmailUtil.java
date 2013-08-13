package util.report;

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
import org.junit.Test;

import util.CfgLoader;

/**
 * This is the utility for sending the email after test There are three types:
 * SimpleEmail, HTMLEmail, MutiEmail
 * 
 * @author XiaoXue_Chen
 * 
 */
public class EmailUtil {

	private static String hostName;
	private static String authenticationName;
	private static String authenticationPwd;
	private static String sentFrom;
	private static String subject;

	private static String sentTo[];
	private static String testResultPath;
	static {
		hostName = CfgLoader.hostName;
		authenticationName = CfgLoader.authenticationName;
		authenticationPwd = CfgLoader.authenticationPwd;
		sentFrom = CfgLoader.sentFrom;
		subject = CfgLoader.subject;
		sentTo=CfgLoader.sendTo;
		testResultPath=CfgLoader.testResultPath;
	}

	public static void sendHTMLEmail() {
		try {

			HtmlEmail email = new HtmlEmail();
			email.setHostName(hostName);
			email.setAuthentication(authenticationName, authenticationPwd);
			email.setCharset("UTF-8");
			email.addTo(sentTo);
			email.setFrom(sentFrom);// here must the same as AuthenticationName
			email.setSubject(subject);
			email.attach(getZipAsAttachement("target","target.zip"));
			email.attach(getZipAsAttachement("log","log.zip"));
			email.setHtmlMsg(getHTMLReport());
			email.addCc(CfgLoader.ccTo);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendSimpleEmail(String content) {
		try {
			// email sending
			SimpleEmail email = new SimpleEmail();
			email.setHostName(hostName);
			email.setAuthentication(authenticationName, authenticationPwd);
			email.setCharset("UTF-8");
			email.addTo(sentTo);
			email.setFrom(sentFrom);// here must the same as AuthenticationName
			email.setSubject(subject);
			email.setMsg(content);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static void sendMutiEmail(String content) {
		try {
		
			// email sending
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(hostName);
			email.setAuthentication(authenticationName, authenticationPwd);
			email.setCharset("UTF-8");
			email.addTo(sentTo);
			email.setFrom(sentFrom);// here must the same as AuthenticationName
			email.setSubject(subject);
			email.setMsg(content);
			email.attach(getZipAsAttachement("target","target.zip"));
			email.attach(getZipAsAttachement("log","log.zip"));
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private static String getHTMLReport() {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(testResultPath)));
			String text;
			while ((text = input.readLine()) != null)
				buffer.append(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();

	}


	private static EmailAttachment getZipAsAttachement(String srcPath,String targetFileName) {
		compress(srcPath, targetFileName);
		EmailAttachment zip = new EmailAttachment();
		zip.setPath(targetFileName);
		zip.setDisposition(EmailAttachment.ATTACHMENT);
		zip.setDescription("Screen Shot");
		return zip;
	}


	public static void compress(String srcPath,String zipfileName) {
		File srcdir = new File(srcPath);
		File zipFile = new File(zipfileName);
		
		if (!srcdir.exists())
			throw new RuntimeException(srcPath + "doesn't exists");
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		fileSet.setIncludes("screenshot_*/*/*");
		fileSet.setIncludes("toCheck*/*");
		fileSet.setIncludes("TestResult*");
		fileSet.setIncludes("*.log");
		zip.addFileset(fileSet);
		zip.execute();
	}
	@Test
	public void sendReport(){
		EmailUtil.sendHTMLEmail();
}}
