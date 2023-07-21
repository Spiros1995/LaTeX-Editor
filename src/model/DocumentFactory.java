package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DocumentFactory {
	
	private String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	private Article article = new Article();
	private Report report = new Report();
	private Book book = new Book();
	private Letter letter = new Letter();
	private Empty empty = new Empty();

	public Document factoryMethod(String type){
		if (type.equals("article")){
			return article.setArticle(date);
		}else if (type.equals("report")){
			return report.setReport(date);
		}else if (type.equals("book")){
			return book.setBook(date);
		}else if (type.equals("letter")){
			return letter.setLetter(date);
		}else{
			return empty.setEmpty(date);
		}
	}
}
