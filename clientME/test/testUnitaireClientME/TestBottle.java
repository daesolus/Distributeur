package testUnitaireClientME;

import static org.junit.Assert.*;

import org.junit.Test;

import clientME.Bottle;

public class TestBottle {

	@Test
	public void test() {
		Bottle bottle = new Bottle();
		assertTrue("Error: pump should be null...", bottle.getPump() ==  null);
	}

}
