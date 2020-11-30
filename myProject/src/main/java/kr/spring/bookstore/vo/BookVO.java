package kr.spring.bookstore.vo;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookVO {
	private String title;
	private String contents;
	private String url;
	private String isbn;
	private LocalDateTime datetime;
	private String[] authors;
	private String publisher;
	private String[] translators;	
	private int price;
	private int sale_price;
	private String thumbnail;
	private String status;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String[] getTranslators() {
		return translators;
	}
	public void setTranslators(String[] translators) {
		this.translators = translators;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSale_price() {
		return sale_price;
	}
	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	public static BookVO parse(JSONObject book) {
		BookVO vo = new BookVO();
		if(book.has("authors")) {
			JSONArray authorsArr = book.getJSONArray("authors");
			String[] authors = new String[authorsArr.length()];
			for(int i=0; i<authorsArr.length(); i++) {
				authors[i] = authorsArr.getString(i);
			}
			vo.setAuthors(authors);
		}
		if(book.has("contents")) {
			vo.setContents(book.getString("contents"));
		}
		if(book.has("isbn")) {
			vo.setIsbn(book.getString("isbn"));
		}
		if(book.has("price")) {
			vo.setPrice(book.getInt("price"));
		}
		if(book.has("publisher")) {
			vo.setPublisher(book.getString("publisher"));
		}
		if(book.has("sale_price")) {
			vo.setSale_price(book.getInt("sale_price"));
		}
		if(book.has("status")) {
			vo.setStatus(book.getString("status"));
		}
		if(book.has("thumbnail")) {
			vo.setThumbnail(book.getString("thumbnail"));
		}
		if(book.has("title")) {
			vo.setTitle(book.getString("title"));
		}
		if(book.has("url")) {
			vo.setUrl(book.getString("url"));
		}
		return vo;
	}
}
