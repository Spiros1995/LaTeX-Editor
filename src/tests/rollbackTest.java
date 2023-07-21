package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.Controller;
import model.Document;

public class rollbackTest {

	@Test
	public void test() throws IOException {
		Document document = new Document();
		Controller controller = new Controller();
		controller.setStrategyType("volatile");
		controller.enableVersions();
		document.setContents("test");
		controller.getStrategy().putVersion(document);
		document.setContents("test 2");
		controller.getStrategy().putVersion(document);
		document.setContents("test 3");
		controller.getStrategy().putVersion(document);
		document.setContents("test 4");
		//set rollback to version id 1
		controller.setRollback(1);
		//rollback
		controller.rollbackToPrevious();
		//test new contents
		assertEquals(controller.getContents(),"test 2");

	}

}
