package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;

public class editContentsViaGuiTest {

	@Test
	public void test() {
		Controller controller = new Controller();
		
		//simulate text field inputs
		String text = "Didn't exactly know how to make tests for Gui";
		
		controller.setContents(text);
		
		assertEquals(controller.getContents(),"Didn't exactly know how to make tests for Gui");
	}

}
