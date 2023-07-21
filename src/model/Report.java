package model;

public class Report extends Document{
	
	public Document setReport(String date){
		clear();
		type = "report";
		this.date=date;
		versionID=0;
		setReportContents();
		return new Document(new String(this.type), new String(this.author), new String(this.date), new String(this.copyright),new Integer(this.versionID),new String(this.contents));
	}

	public void setReportContents(){
		addContents("\\documentclass[11pt,a4paper]{report}\n");
		addContents("\\begin{document}\n");
		addContents("\\title{ }\n");
		addContents("\\author{ }\n");
		addContents("\\date{\\today}\n");
		addContents("\\maketitle\n");
		addContents("\\begin{abstract}\n");
		addContents("\\end{abstract}\n");
		addContents("\\chapter{ }\n");
		addContents("\\section{ }\n");
		addContents("\\chapter{ }\n");
		addContents("\\chapter*{References}\n");
		addContents("\\end{document}\n");
	}

}
