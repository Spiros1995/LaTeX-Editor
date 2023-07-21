package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import controller.Controller;
import model.*;

public class saveTest {

	@Test
	public void test() throws IOException {
		Controller controller = new Controller();
		controller.setContents("testing123");
		Save save = new Save();
		save.saveFile("test", "C:/Users/Spyros/Desktop/Software Engineering/src/tests", controller.getContents());
		File file = new File("C:/Users/Spyros/Desktop/Software Engineering/src/tests/test.tex"); 
	    Scanner sc = new Scanner(file);
	    String contents="";
	    String line;
	    while (sc.hasNextLine()){
    		line = sc.nextLine()+"\n";
	    	contents = contents + line;
	    }
	    sc.close();
	    assertEquals(contents,"testing123"+"\n");
	}

}
