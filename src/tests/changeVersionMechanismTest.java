package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.Controller;
import model.VersionsStrategyFactory;

public class changeVersionMechanismTest {

	@Test
	public void test() throws IOException {
		
		VersionsStrategyFactory versionsStrategyFactory = new VersionsStrategyFactory("stable");

		Controller controller = new Controller();
		controller.setStrategyType("volatile");
		controller.enableVersions();
		controller.changeVersions();
		
		assertEquals(versionsStrategyFactory.createStrategy().getClass(),controller.getStrategy().getClass());
	}

}
