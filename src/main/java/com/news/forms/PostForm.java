package com.news.forms;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.news.constants.Category;

import lombok.Data;

@Data
public class PostForm {
	@NotBlank(message = "Chưa nhập tiêu đề.")
	@Max(value = 50, message = "Vượt quá 50 ký tự.")
	@Pattern(regexp = "^\\w+$", message = "Chỉ được nhập chữ và số.")
	private String title;
	
	@NotBlank
	private MultipartFile thumbnail;
	
	@NotBlank(message = "Chưa nhập nội dung ngắn.")
	@Max(value = 200, message = "Vượt quá 200 ký tự.")
	private String shortContent;
	
	@NotBlank(message = "Chưa nhập nội dung chính.")
	private String content;
	
	@NotEmpty(message = "Chưa chọn danh mục.")
	private Set<Category> category;
}
