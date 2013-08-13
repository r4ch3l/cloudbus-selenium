package util.report;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;





import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import testcase.TestLinkCase;
import testcase.TestStep;
import util.CfgLoader;
/**
 * Load test cases descriptions from xml file which exported from TestLink webUI suite.
 * @author XiaoXue_Chen
 *
 */
public class TestCaseLoader {
	static Map<Integer, TestLinkCase> testCases = new HashMap<Integer, TestLinkCase>();
	static {

		 try {
		 readCase(CfgLoader.testcasesPath);
		 } catch (DocumentException e) {
		 e.printStackTrace();
		 }
//		 System.out.println(testCases.size());
	}


	/**
	 * Read Testcases from xml.
	 * @param filename
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public static void readCase(String filename) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(new File(filename));
		Element rootElmt = doc.getRootElement();

		for (Iterator iter = rootElmt.elementIterator(); iter.hasNext();) {
			Element testsuit = (Element) iter.next();
			String testSuitName = "";
//			System.out.println(" get name " + testsuit.getName());
			if (testsuit.getName().equals("testsuite")) {
				testSuitName = testsuit.attribute("name").getValue();
				for (Iterator i = testsuit.elementIterator(); i.hasNext();) {
					Element testcase = (Element) i.next();
//					System.out.println(" get case " + testcase.getName());
					if (testcase.getName().equals("testcase")) {
						TestLinkCase tlc = new TestLinkCase();

						tlc.setExternalid(Integer.parseInt(testcase
								.elementText("externalid")));
						tlc.setInternalid(Integer.parseInt(testcase.attribute(
								"internalid").getValue()));
						tlc.setName(testcase.attribute("name").getValue());
						tlc.setTestSuitName(testSuitName);
						Element steps = testcase.element("steps");
						if (steps != null) {
							tlc.setSteps(readStep(steps));
						}
						testCases.put(tlc.getExternalid(), tlc);
					}
				}
			}

		}
		// System.out.println(testCases.size());
	}

	
	
	@SuppressWarnings("rawtypes")
	/**
	 * Read steps from xml
	 * @param steps
	 * @return
	 */
	public static Map<Integer, TestStep> readStep(Element steps) {
		Map<Integer, TestStep> testSteps = new HashMap<Integer, TestStep>();
		Element rootElmt = steps;
		Iterator iter = rootElmt.elementIterator();
		while (iter.hasNext()) {
			Element step = (Element) iter.next();
			TestStep ts = new TestStep();
			ts.setActions(step.elementText("actions"));
			ts.setExpectedresults(step.elementText("expectedresults"));
			ts.setStepNumber(Integer.parseInt(step.elementText("step_number")));
			testSteps.put(ts.getStepNumber(), ts);
		}
		return testSteps;

	}
	
	
	public static TestLinkCase getCaseByid(Integer caseid) {
		return testCases.get(caseid);
	}

	public static TestStep getCaseByid(Integer caseid, Integer stepid) {
		TestLinkCase testcase = testCases.get(caseid);
		return testcase.getSteps().get(stepid);

	}
}
