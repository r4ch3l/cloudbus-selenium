package temp;


import static org.junit.Assert.assertEquals;




import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.util.ReportUtil;





/**
 * 

 * @author XiaoXue_Chen
 *
 */

public class tempTest {
	 private final static Logger logger=LoggerFactory.getLogger(tempTest.class);
	
@Test	 
public void testtemp1(){
	boolean result=false;
	ReportUtil
	.insertReportLine(
			"CB-26_4",	// caseID
			"fake",	// CaseName
			"login with a pair of wrong username and password",	// CaseDescription
			"there will be a notification like'The email or password you entered is invalid!' ",	// expected
			result == true ? "pass" : "fail",	// test result
			"none");	// comment
	}
@Test	
public void testtemp2(){
	boolean result=true;
	ReportUtil
	.insertReportLine(
			"CB-26_4",	// caseID
			"fake",	// CaseName
			"login with a pair of wrong username and password",	// CaseDescription
			"there will be a notification like'The email or password you entered is invalid!' ",	// expected
			result == true ? "pass" : "fail",	// test result
			"none");	// comment
}

@Test	
public void testtemp3(){
	boolean result=true;
	ReportUtil
	.insertReportLine(
			"CB-26_4",	// caseID
			"fake",	// CaseName
			"login with a pair of wrong username and password",	// CaseDescription
			"there will be a notification like'The email or password you entered is invalid!' ",	// expected
			"error",	// test result
			"none");	// comment
}

}
