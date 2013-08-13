package tests;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.report.ReportUtil;




/**
 * This is the testsuit contains 
 * @author XiaoXue_Chen
 *
 */
public class TestSet {
	private final static Logger logger = LoggerFactory.getLogger(TestSet.class);
	long currentTime = System.currentTimeMillis();

	
	@Before
	public void setup() {
		ReportUtil.getReport();
	}
	
	@Test
	public void test() {
		Result r = org.junit.runner.JUnitCore.runClasses(ValidateSignInDialog.class,ValidateRegisterDialog.class,ValidateWhyRegisterPage.class,ValidateHomePageAsGuest.class);
		int run=r.getRunCount();
		int fail=r.getFailureCount();
		int x=r.getIgnoreCount();
		ReportUtil.updateValuePrepare(run, fail, r.getRunTime());
		logger.info("run count:"+run+"    run failure:"+fail+"    ignore count:"+x);
	}

	
	
}
