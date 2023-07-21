package model;

import java.util.ArrayList;

public class VolatileVersionsStrategy implements VersionsStrategy{
	private Document document; 
	private ArrayList <Document> versionsHistory;
	public VolatileVersionsStrategy() {
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
