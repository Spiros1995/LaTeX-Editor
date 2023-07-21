package model;

import java.util.ArrayList;

public interface VersionsStrategy {
	public void putVersion(Document document);
	public Document getVersion();
	public void setEntireHistory(ArrayList <Document> versionsHistory);
	public ArrayList<Document> getEntireHistory();
	public void removeDocument();
	public void clearArrayList();
}
