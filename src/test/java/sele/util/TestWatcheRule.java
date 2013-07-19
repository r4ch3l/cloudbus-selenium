package sele.util;

import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import temp.tempTest;

public class TestWatcheRule  implements MethodRule {
	Logger logger;
	WebDriver driver;
	
	public TestWatcheRule(Logger logger,WebDriver driver){
		super();
		this.driver=driver;
		this.logger=logger;
	}
	    public Statement apply(final Statement base, final FrameworkMethod method,
	            Object target) {
	        return new Statement() {
	            @Override
	            public void evaluate() throws Throwable {
	                starting(method);
	                try {
	                    base.evaluate();
	                    succeeded(method);
	                } catch (AssumptionViolatedException e) {
	                    throw e;
	                } catch (Throwable t) {
	                    failed(t, method);
	                    throw t;
	                } finally {
	                    finished(method);
	                }
	            }
	        };
	    }

	    /**
	     * Invoked when a test method succeeds
	     */
	    public void succeeded(FrameworkMethod method) {
	    	logger.info("{}: succeeded", method.getName());
	    }

	    /**
	     * Invoked when a test method fails
	     */
	    public void failed(Throwable e, FrameworkMethod method) {
	    	logger.info("{}: failed", method.getName());
	    }

	    /**
	     * Invoked when a test method is about to start
	     */
	    public void starting(FrameworkMethod method) {
	    	logger.info("{}: starting", method.getName());
	    }


	    /**
	     * Invoked when a test method finishes (whether passing or failing)
	     */
	    public void finished(FrameworkMethod method) {
	    	logger.info("{}: finished", method.getName());
	    }
}
