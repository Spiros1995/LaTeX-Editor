package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StableVersionsStrategy implements VersionsStrategy{
	private Document document; 
	private ArrayList <Document> versionsHistory;
	private Save tempSave = new Save();
	public StableVersionsStrategy() {
		document = new Document();
		versionsHistory = new ArrayList<Document>();
	}
	
	public void putVersion(Document document) {
		this.document=document;
		Document tempDoc = new Document();
		tempDoc.setContents(document.getContents());
		tempDoc.setAuthor(document.getAuthor());
		tempDoc.setCopyright(document.getCopyright());
		tempDoc.setDate(document.getDate());
		tempDoc.setVersionID(document.getVersionID());
		tempDoc.setType(document.getType());
		versionsHistory.add(tempDoc);
		
		new File(System.getProperty("user.dir") + "/src/tempSaves").mkdirs();

		try {
			String path = System.getProperty("user.dir") + "/src/tempSaves";
			tempSave.saveFile(Integer.toString(tempDoc.getVersionID()), path, tempDoc.getContents());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Document getVersion() {
		return versionsHistory.get(versionsHistory.size()-1);
	}
	public void setEntireHistory(ArrayList <Document> versionsHistory) {
		this.versionsHistory = versionsHistory;
	}
	public ArrayList<Document> getEntireHistory(){
		return versionsHistory;
	}
	
	public void removeDocument() {
		versionsHistory.remove(versionsHistory.size()-1);
	}
	
	public void clearArrayList() {
		versionsHistory.clear();
	}
	
}
