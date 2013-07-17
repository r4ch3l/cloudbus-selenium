package sele.pot.testsuit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sele.util.CfgLoader;
import sele.util.EmailUtil;
import sele.util.ReportUtil;



/**
 * This is the testsuit contains the testing maven would run by
 * command "mvn test"
 * @author XiaoXue_Chen
 *
 */
public class TestSuit {
	private final static Logger logger = LoggerFactory.getLogger(TestSuit.class);
	long currentTime = System.currentTimeMillis();

	@Before
	public void setup() {
		ReportUtil.getReport();
	}

	@Test
	public void test() {
		Result r = org.junit.runner.JUnitCore.runClasses(HomeTest.class);
//		Result r = org.junit.runner.JUnitCore.runClasses(HomeTest.class);
		int run=r.getRunCount();
		int fail=r.getFailureCount();
		int x=r.getIgnoreCount();
		ReportUtil.count(run, fail, r.getRunTime());
		logger.info("run count:"+run+"    run failure:"+fail+"    ignore count:"+x);
		
	}

	@After
	public void email() {
		EmailUtil.sendHTMLEmail(CfgLoader.sendTo);
	}

}
