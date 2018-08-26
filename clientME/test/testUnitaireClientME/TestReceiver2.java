package testUnitaireClientME;

import jmunit.framework.cldc11.TestCase;

public class TestReceiver2 extends TestCase {

	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public TestReceiver2() {
		super(5, "TestReceiver2");
	}

	public void Receiver1Test() {
		fail("Not Yet Implemented.");
	}

	public void run1Test() {
		fail("Not Yet Implemented.");
	}

	public void isJSONRequest1Test() {
		fail("Not Yet Implemented.");
	}

	public void getPort1Test() {
		fail("Not Yet Implemented.");
	}

	public void getOrderList1Test() {
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
			Receiver1Test();
			break;
		case 1:
			run1Test();
			break;
		case 2:
			isJSONRequest1Test();
			break;
		case 3:
			getPort1Test();
			break;
		case 4:
			getOrderList1Test();
			break;
		}
	}

}
