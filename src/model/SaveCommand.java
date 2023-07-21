package model;

import java.io.IOException;

public class SaveCommand implements Command{
	private Save save = new Save();
	private Document document;
	private String fileName;
	private String path;

	public SaveCommand(Document document, String fileName, String path) {
		this.document=document;
		this.fileName=fileName;
		this.path=path;
	}
	public void execute() throws IOException{
		save.saveFile(fileName,path,document.getContents());
		save.saveInfo(document.getAuthor(),document.getDate(),document.getVersionID(),document.getCopyright(),fileName,path);
	}
}
