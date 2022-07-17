package com.news.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.news.constants.Category;
import com.news.constants.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Post implements Serializable {
	
	private static final long serialVersionUID = Constant.POST_SERIAL;
	private final long id;
	private String title;
	private String thumbnail;
	private String shortContent;
	private String content;
	private Set<Category> category;
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;
	
	public Post() {
		this.id = (long) (Math.random()*1000000000);
	}
	
	@Override
	public int hashCode() {
		return (int) this.id;
	}
	
	public boolean equalsTitle(Post otherPost) {
		return this.title.equals(otherPost.getTitle()) ? true : false;
	}
	
	
}
