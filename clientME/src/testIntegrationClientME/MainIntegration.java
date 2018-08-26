package testIntegrationClientME;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import testIntegrationClientME.TestDispenser;;

public class MainIntegration {
	public static void main(String args[]) {
		
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(TestDispenser.class);
		

		System.out.println(" ");
		System.out.println("Was successful? " + result.wasSuccessful());
		System.out.println("Number of tests runned: " + result.getRunCount());
		System.out.println("Number of failures: " + result.getFailureCount());
		System.out.println("Number of success: " + (result.getRunCount()-result.getFailureCount()));
		System.out.println("Number of test ignored: " + result.getIgnoreCount());
		System.out.println("Failures: " + result.getFailures());
		System.out.println(" ");
		}
}
