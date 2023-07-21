package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Save{	
	
	File file;
	
	public void saveFile(String name, String path, String contents) throws IOException {
		file = new File(path + "/" + name + ".tex");
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String str = contents;
		FileOutputStream outputStream = new FileOutputStream(file);
		byte[] strToBytes = str.getBytes();
		outputStream.write(strToBytes);
		outputStream.close();
	}
	
	public void saveInfo(String author, String date, int versionID, String copyright, String name, String path) throws IOException{
		if(System.getProperty("os.name").equals("Linux")){
			file = new File(path + "/" + "." + name + ".txt");
			Path p = Paths.get("." + path + "/" + "." + name + ".txt");
			
			if(file.exists()){
				file.delete();
			}

			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			String str="";
			if (author.equals("")){
				author="default";
				str+=author + "$$" + date + "$$" + versionID + "$$";
			}else{
				str+=author + "$$" + date + "$$" + versionID + "$$";
			}
			if (copyright.equals("")){
				copyright="default";
				str+=copyright;
			}else{
				str+=copyright;
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			byte[] strToBytes = str.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		   // Files.setAttribute(p, "dos:hidden", true); //only works on windows :(
		}else{
			file = new File(path + "\\" + name + ".txt");
			Path p = Paths.get(path + "\\" + name + ".txt");
			
			if(file.exists()){
				file.delete();
			}

			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			String str="";
			if (author.equals("")){
				author="default";
				str+=author + "$$" + date + "$$" + versionID + "$$";
			}else{
				str+=author + "$$" + date + "$$" + versionID + "$$";
			}
			if (copyright.equals("")){
				copyright="default";
				str+=copyright;
			}else{
				str+=copyright;
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			byte[] strToBytes = str.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		    Files.setAttribute(p, "dos:hidden", true);

		}

	}
	
}
