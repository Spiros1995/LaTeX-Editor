package model;

public class Document {
	
	protected String type = " ";
	protected String author = " ";
	protected String date = " ";
	protected String copyright = " ";
	protected int versionID = 0;
	protected String contents;
	
	public Document(){
		type = " ";
		author = " ";
		date = " ";
		copyright = " ";
		versionID = 0;
	}
	
	
	public Document(String type, String author, String date, String copyright, int versionID, String contents){
		this.type=type;
		this.author=author;
		this.date=date;
		this.copyright=copyright;
		this.versionID=versionID;
		this.contents=contents;
	}
	
	//clears a Document
	public void clear() {
		type = " ";
		author = " ";
		date = " ";
		copyright = " ";
		contents="";
		versionID = 0;
	}
	
	//Add contents at the end
	public void addContents(String content){
		contents = contents + content;
	}

	//Setters
	public void setType(String type) {
		this.type=type;
	}
	
	public void setContents(String content) {
		contents = content;
	} 
		
	public void setAuthor(String author){
		this.author=author;
	}
	
	public void setCopyright(String copyright){
		this.copyright=copyright;
	}

	public void setDate(String parseInt) {
		date=parseInt;
	}


	public void setVersionID(int parseInt) {
		versionID=parseInt;
	}
	
	//Getters
	public String getType() {
		return type;
	}


	public String getAuthor() {
		return author;
	}


	public String getDate() {
		return date;
	}


	public int getVersionID() {
		return versionID;
	}


	public String getCopyright() {
		return copyright;
	}
	
	public String getContents(){
		return contents;
	}

	//Tests with print
	public void printContents() {
		System.out.println(contents);
		System.out.println(author);
		System.out.println(copyright);
		System.out.println(date);
		System.out.println(versionID);
		System.out.println(type);

	}
	
	public void test() {
		System.out.println(author);
		System.out.println(copyright);
	}

}
