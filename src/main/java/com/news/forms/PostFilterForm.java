package com.news.forms;

import com.news.constants.Category;

import lombok.Data;

@Data
public class PostFilterForm {
	private Long id;
	private String title;
	private Category category;
}
