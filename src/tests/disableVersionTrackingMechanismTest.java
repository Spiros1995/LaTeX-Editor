package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.Controller;

public class disableVersionTrackingMechanismTest {

	@Test
	public void test() throws IOException {
		Controller controller = new Controller();
		controller.setStrategyType("volatile");
		controller.enableVersions();
		controller.disableVersions();
		
		//versionID must be 0 since tracking mechanism is off
		assertEquals(controller.getVersionID(),0);
	}

}
