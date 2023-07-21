package model;

public class Article extends Document {
	
	public Document setArticle(String date){
		clear();
		type = "article";
		this.date=date;
		versionID=0;
		setArticleContents();
		return new Document(new String(this.type), new String(this.author), new String(this.date), new String(this.copyright),new Integer(this.versionID),new String(this.contents));
	}

	public void setArticleContents(){
		addContents("\\documentclass[11pt,twocolumn,a4paper]{article}\n");
		addContents("\\begin{document}\n");
		addContents("\\title{ }\n");
		addContents("\\author{ }\n");
		addContents("\\date{\\today}\n");
		addContents("\\maketitle\n");
		addContents("\\section{ }\n");
		addContents("\\section*{References}\n");
		addContents("\\end{document}\n");
	}
		
}
