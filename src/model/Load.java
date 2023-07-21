package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import controller.Controller;

public class Load {

	public void loadFile(Controller controller) throws IOException {
		File file = new File(controller.getFilePath() + "/" + controller.getFileName()); 
	    Scanner sc = new Scanner(file);
	    String line;
	    String[] firstline;
	    String type = "empty";
	    int count =0;
	    while (sc.hasNextLine()){
    		line = sc.nextLine()+"\n";
	    	if(count==0){
	    		firstline = line.split("\\{");
	            type = firstline[firstline.length - 1];
	            count++;
	    	}
	    	controller.addContents(line);
	    }
	    type=type.replace("}", "");
	    type=type.replaceAll("\\s", "");
	    controller.setType(type.replace("}", ""));
	    sc.close();
	}
	
	public void loadInfo(Controller controller) throws IOException {
		File file;
		Path p;
		if(System.getProperty("os.name").equals("Linux")){
			file = new File((controller.getFilePath() + "/" + "." + controller.getFileName()).replace(".tex",".txt"));
			p = Paths.get((controller.getFilePath() + "/" + "." + controller.getFileName()).replace(".tex",".txt"));
		}else{
			file = new File((controller.getFilePath() + "/" + controller.getFileName()).replace(".tex",".txt"));
			p = Paths.get((controller.getFilePath() + "/" + controller.getFileName()).replace(".tex",".txt"));
		}

		if (file.exists()){
		    Scanner sc = new Scanner(file); 
		    String temp="";
		    while (sc.hasNextLine()){
		    	temp+=sc.nextLine();
		    }
		    String [] documentComponents = temp.split("\\$");
		    //Set author, date, versionID, copyright from the hidden file
		    controller.setAuthor(documentComponents[0]);
		    controller.setDate((documentComponents[2]));
		    controller.setVersionID(0);
		    controller.setCopyright(documentComponents[6]);
		    sc.close();
		}
		else{
			//If there was no hidden file, create one and set it up along with the fields
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String author = JOptionPane.showInputDialog("Enter author name: ");
			String copyright = JOptionPane.showInputDialog("Enter copyrights: ");
			if(author.equals("")){
				author="default";
			}
			if(copyright.equals("")){
				copyright="default";
			}
			controller.setAuthor(author);
			controller.setCopyright(copyright);
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String str = author + "$$" + date + "$$" + "0" + "$$" + copyright;
			FileOutputStream outputStream = new FileOutputStream(file);
			byte[] strToBytes = str.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		}
		if(!System.getProperty("os.name").equals("Linux")){
			Files.setAttribute(p, "dos:hidden", true);
		}
	}

}
