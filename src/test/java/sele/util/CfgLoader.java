package sele.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * This is a loader tool for loading the properties form the configure file.
 * @author XiaoXue_Chen
 *
 */
public class CfgLoader {
	// the path of 'conf.properties' file
	static String confFile = "conf/conf.properties";
	// type of browser
	public static String browserType = load("browser.type");

	// browser drivers path
	public static String chromeDriverPath = load("browser.chromeDriverPath");
	public static String ieDriverPath = load("browser.ieDriverPath");

	// html file tag name
	public static String RunTag = load("report.runtag");
	public static String PassTag = load("report.passtag");
	public static String FailTag = load("report.failtag");
	public static String ErrorTag = load("report.errortag");

	// report simple model
	public static String reportSamplePath = load("report.samplePath");

	// properties using in test report
	public static String projectName = load("report.projectName");
	public static String reportPath = load("report.reportPath");
	public static String os = load("report.os");
	public static String browserVersion = load("report.browserVersion");
	public static String testLocation = load("report.testLocation");

	// properties of email
	public static String hostName = load("email.hostName");
	public static String authenticationName = load("email.authenticationName");
	public static String authenticationPwd = load("email.authenticationPwd");
	public static String sentFrom = load("email.sentFrom");
	public static String subject = load("email.subject");
	public static String dateformat = load("email.dateformat");
	public static String sendTo = load("email.sentTo");
	public static String ccTo = load("email.ccTo");

	public static String load(String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(confFile));
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
