package testUnitaireClientME;

import jmunit.framework.cldc11.TestCase;

public class TestSocketServeur extends TestCase {

	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public TestSocketServeur() {
		super(10, "TestSocketServeur");
	}

	public void SocketServeur1Test() {
		fail("Not Yet Implemented.");
	}

	public void SocketServeur2Test() {
		fail("Not Yet Implemented.");
	}

	public void getPort1Test() {
		fail("Not Yet Implemented.");
	}

	public void setSocket1Test() {
		fail("Not Yet Implemented.");
	}

	public void isStart1Test() {
		fail("Not Yet Implemented.");
	}

	public void envoyerMsg1Test() {
		fail("Not Yet Implemented.");
	}

	public void setPrintWriter1Test() {
		fail("Not Yet Implemented.");
	}

	public void getServerSocket1Test() {
		fail("Not Yet Implemented.");
	}

	public void getLed1Test() {
		fail("Not Yet Implemented.");
	}

	public void closeServer1Test() {
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
			SocketServeur1Test();
			break;
		case 1:
			SocketServeur2Test();
			break;
		case 2:
			getPort1Test();
			break;
		case 3:
			setSocket1Test();
			break;
		case 4:
			isStart1Test();
			break;
		case 5:
			envoyerMsg1Test();
			break;
		case 6:
			setPrintWriter1Test();
			break;
		case 7:
			getServerSocket1Test();
			break;
		case 8:
			getLed1Test();
			break;
		case 9:
			closeServer1Test();
			break;
		}
	}

}
