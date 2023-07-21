package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import controller.*;
import model.*;

public class loadTest {

	@Test
	public void test() throws IOException {
		Controller controller = new Controller();
		controller.setContents("");
		controller.setFilePath("C:/Users/Spyros/Desktop/Software Engineering/src/tests");
		controller.setFileName("test.tex");
		Load load = new Load();
		load.loadFile(controller);
		assertEquals(controller.getContents(),"testing123"+"\n");
	}

}
