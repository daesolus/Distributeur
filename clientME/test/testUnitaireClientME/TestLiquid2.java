package testUnitaireClientME;

import jmunit.framework.cldc11.TestCase;

public class TestLiquid2 extends TestCase {

	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public TestLiquid2() {
		super(8, "TestLiquid2");
	}

	public void Liquid1Test() {
		fail("Not Yet Implemented.");
	}

	public void Liquid2Test() {
		fail("Not Yet Implemented.");
	}

	public void getName1Test() {
		fail("Not Yet Implemented.");
	}

	public void setName1Test() {
		fail("Not Yet Implemented.");
	}

	public void getQuantity1Test() {
		fail("Not Yet Implemented.");
	}

	public void setQuantity1Test() {
		fail("Not Yet Implemented.");
	}

	public void getPump1Test() {
		fail("Not Yet Implemented.");
	}

	public void setPump1Test() {
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
			Liquid1Test();
			break;
		case 1:
			Liquid2Test();
			break;
		case 2:
			getName1Test();
			break;
		case 3:
			setName1Test();
			break;
		case 4:
			getQuantity1Test();
			break;
		case 5:
			setQuantity1Test();
			break;
		case 6:
			getPump1Test();
			break;
		case 7:
			setPump1Test();
			break;
		}
	}

}
