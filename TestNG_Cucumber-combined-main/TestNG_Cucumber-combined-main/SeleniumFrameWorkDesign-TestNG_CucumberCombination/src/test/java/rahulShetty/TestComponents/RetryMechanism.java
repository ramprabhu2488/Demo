package rahulShetty.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMechanism implements IRetryAnalyzer {

	// Here we say, how many times, that failed testcases need to be rerun.To make sure, the testcases is passed or fail.
	// it only trigger, when testcase failed.
	
	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry) {
			count++;
			return true;
		}
		
		return false;
	}
	

}
