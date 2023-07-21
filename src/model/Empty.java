package model;

public class Empty extends Document{
	

	public Document setEmpty(String date){
		clear();
		this.date=date;
		versionID=0;
		type="empty";
		return new Document(new String(this.type), new String(this.author), new String(this.date), new String(this.copyright),new Integer(this.versionID),new String(this.contents));
	}
}
