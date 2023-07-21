package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.*;

public class createDocumentTest {

	@Test
	public void test() {
		Controller controller = new Controller();
		controller.setType("article");
		controller.factoryMethod();
		assertEquals(controller.getContents(),"\\documentclass[11pt,twocolumn,a4paper]{article}\n"+"\\begin{document}\n"+"\\title{ }\n"+"\\author{ }\n"+"\\date{\\today}\n"+"\\maketitle\n"+"\\section{ }\n"+"\\section*{References}\n"+"\\end{document}\n");
	}

}
