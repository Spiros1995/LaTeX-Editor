package model;

public class Book extends Document{
	
	public Document setBook(String date){
		clear();
		type = "book";
		this.date=date;
		versionID=0;
		setBookContents();
		return new Document(new String(this.type), new String(this.author), new String(this.date), new String(this.copyright),new Integer(this.versionID),new String(this.contents));
	}
	
	public void setBookContents(){
		addContents("\\documentclass[11pt,a4paper]{book}\n");
		addContents("\\begin{document}\n");
		addContents("\\title{ }\n");
		addContents("\\author{ }\n");
		addContents("\\date{\\today}\n");
		addContents("\\maketitle\n");
		addContents("\\frontmatter\n");
		addContents("\\chapter{Preface}\n");
		addContents("% ...\n");
		addContents("\\mainmatter\n");
		addContents("\\chapter{ }\n");
		addContents("\\section{ }\n");
		addContents("\\chapter*{References}\n");
		addContents("\\backmatter\n");
		addContents("\\chapter{Last note}\n");
		addContents("\\end{document}\n");
	}
}
