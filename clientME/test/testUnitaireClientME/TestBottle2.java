package testUnitaireClientME;

import jmunit.framework.cldc11.TestCase;

public class TestBottle2 extends TestCase {

	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public TestBottle2() {
		super(5, "TestBottle2");
	}

	public void Bottle1Test() {
		fail("Not Yet Implemented.");
	}

	public void Bottle2Test() {
		fail("Not Yet Implemented.");
	}

	public void stop1Test() {
		fail("Not Yet Implemented.");
	}

	public void pour1Test() {
		fail("Not Yet Implemented.");
	}

	public void getPump1Test() {
		fail("Not Yet Implemented.");
	}

	/**
	 * This method stores all the test methods invocation. The developer must
	 * implement this method with a switch-case. The cases must start from 0 and
	 * increase in steps of one until the number declared as the total of tests
	 * in the constructor, exclusive. For example, if the total is 3, the cases
	 * must be 0, 1 and 2. In each case, there must be a test method invocation.
	 * 
	 * @param testNumber the test to be executed.
	 * @throws Throwable anything that the executed test can throw.
	 */
	public void test(int testNumber) throws Throwable {
		switch (testNumber) {
		case 0:
			Bottle1Test();
			break;
		case 1:
			Bottle2Test();
			break;
		case 2:
			stop1Test();
			break;
		case 3:
			pour1Test();
			break;
		case 4:
			getPump1Test();
			break;
		}
	}

}
