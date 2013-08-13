package testcase;

import java.util.HashMap;
import java.util.Map;

public class TestLinkCase {
	private Integer internalid;
	private Integer externalid;
	private String name;
	private String testSuitName;
	
	Map<Integer,TestStep> steps=new HashMap<Integer,TestStep>();
	
	
	
	public String getTestSuitName() {
		return testSuitName;
	}
	public void setTestSuitName(String testSuitName) {
		this.testSuitName = testSuitName;
	}
	public Map<Integer, TestStep> getSteps() {
		return steps;
	}
	public void setSteps(Map<Integer, TestStep> steps) {
		this.steps = steps;
	}
	public Integer getInternalid() {
		return internalid;
	}
	public void setInternalid(Integer internalid) {
		this.internalid = internalid;
	}
	public Integer getExternalid() {
		return externalid;
	}
	public void setExternalid(Integer externalid) {
		this.externalid = externalid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
