package testIntegrationClientME;

import static org.junit.Assert.assertTrue;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import clientME.Lumieres;
import jmunit.framework.cldc11.TestCase;

public class TestMLumieres extends TestCase {

	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public TestMLumieres() {
		super(8, "TestMLumieres");
	}

	public void Lumieres1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
		assertTrue("Error: lumieres is not instantialized", lumieres.isInstantialized() == true);
	}

	public void Rouge1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
		lumieres.Rouge();
		assertTrue("Error: Function Rouge() didn't work as expected...", lumieres.isRougeWorking() == true);
	}

	public void Vert1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
		lumieres.Vert();
		assertTrue("Error: Function Vert() didn't work as expected...", lumieres.isVertWorking() == true);
	}

	public void closeAll1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
		lumieres.closeAll();
		assertTrue("Error: Function closeAll() didn't work as expected...", lumieres.isClosed() == true);
	}

	public void isInstantialized1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
	}

	public void isRougeWorking1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
		assertTrue("Error: False was expected...", lumieres.isRougeWorking() == false);
	}

	public void isVertWorking1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
		assertTrue("Error: False was expected...", lumieres.isVertWorking() == false);
	}

	public void isClosed1Test() {
		GpioController controller = GpioFactory.getInstance();
		Lumieres lumieres = new Lumieres(controller);
		assertTrue("Error: False was expected...", lumieres.isClosed() == false);
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
