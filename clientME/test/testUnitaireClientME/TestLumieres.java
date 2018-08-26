package testUnitaireClientME;

import jmunit.framework.cldc11.TestCase;

public class TestLumieres extends TestCase {

	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public TestLumieres() {
		super(8, "TestLumieres");
	}

	public void Lumieres1Test() {
		fail("Not Yet Implemented.");
	}

	public void Rouge1Test() {
		fail("Not Yet Implemented.");
	}

	public void Vert1Test() {
		fail("Not Yet Implemented.");
	}

	public void closeAll1Test() {
		fail("Not Yet Implemented.");
	}

	public void isInstantialized1Test() {
		fail("Not Yet Implemented.");
	}

	public void isRougeWorking1Test() {
		fail("Not Yet Implemented.");
	}

	public void isVertWorking1Test() {
		fail("Not Yet Implemented.");
	}

	public void isClosed1Test() {
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
			Lumieres1Test();
			break;
		case 1:
			Rouge1Test();
			break;
		case 2:
			Vert1Test();
			break;
		case 3:
			closeAll1Test();
			break;
		case 4:
			isInstantialized1Test();
			break;
		case 5:
			isRougeWorking1Test();
			break;
		case 6:
			isVertWorking1Test();
			break;
		case 7:
			isClosed1Test();
			break;
		}
	}

}
