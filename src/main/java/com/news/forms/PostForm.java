package com.news.forms;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.news.constants.Category;

import lombok.Data;

@Data
public class PostForm {
	private Long id;
	@NotBlank(message = "Chưa nhập tiêu đề.")
	@Size(max = 200, message = "Vượt quá 50 ký tự.")
//	@Pattern(regexp = "^\\w+$", message = "Chỉ được nhập chữ và số.")
	private String title;
	
	private MultipartFile thumbnail;
	
	@NotBlank(message = "Chưa nhập nội dung ngắn.")
	@Size(max = 400, message = "Vượt quá 200 ký tự.")
	private String shortContent;
	
	@NotBlank(message = "Chưa nhập nội dung chính.")
	private String content;
	
	@NotEmpty(message = "Chưa chọn danh mục.")
	private Set<Category> category;
}
