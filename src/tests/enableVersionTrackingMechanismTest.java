package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import controller.Controller;
import model.VersionsStrategyFactory;

public class enableVersionTrackingMechanismTest {

	@Test
	public void test() throws IOException {
		VersionsStrategyFactory versionsStrategyFactory = new VersionsStrategyFactory("volatile");
		
		Controller controller = new Controller();
		controller.setStrategyType("volatile");
		controller.enableVersions();
		
		assertEquals(versionsStrategyFactory.createStrategy().getClass(),controller.getStrategy().getClass());
	}

}
