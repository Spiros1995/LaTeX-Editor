package model;

public class Letter extends Document{
	
	public Document setLetter(String date){
		clear();
		type = "letter";
		this.date=date;
		versionID=0;
		setBookContents();
		return new Document(new String(this.type), new String(this.author), new String(this.date), new String(this.copyright),new Integer(this.versionID),new String(this.contents));
	}
	
	public void setBookContents(){
		addContents("\\documentclass{letter}\n");
		addContents("\\usepackage{hyperref}\n");
		addContents("\\signature{ }\n");
		addContents("\\address{ }\n");
		addContents("\\begin{document}\n");
		addContents("\\begin{letter}{Destination address....}\n");
		addContents("\\opening{ }\n");
		addContents("I am writing to you .......\n");
		addContents("\\closing{ }\n");
		addContents("\\ps\n");
		addContents("P.S. text .....\n");
		addContents("\\encl{Copyright permission form}\n");
		addContents("\\end{letter}\n");
		addContents("\\end{document}\n");
	}

}
