package tests;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

import controller.Controller;

public class editContentsViaCommandsViaGuiTest {

	@Test
	public void test() {
		Controller controller = new Controller();
		controller.setType("article");
		controller.factoryMethod();
		
		//test to add a chapter on our document
		if(controller.getType().equals("letter")) {
			JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
		}
		else {
			controller.setContents(controller.getContents()+"\\chapter{ }");
		}
		
		assertEquals(controller.getContents(),"\\documentclass[11pt,twocolumn,a4paper]{article}\n"+"\\begin{document}\n"+"\\title{ }\n"+"\\author{ }\n"+"\\date{\\today}\n"+"\\maketitle\n"+"\\section{ }\n"+"\\section*{References}\n"+"\\end{document}\n"+"\\chapter{ }");
	}

}
