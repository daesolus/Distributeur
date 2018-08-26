package testUnitaireClientME;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestLiquid.class, TestOrder.class, TestReceiver.class, TestBottle.class})
public class AllUnitTests { 

}
