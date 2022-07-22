package com.news.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.constants.Category;
import com.news.constants.Constant;
import com.news.exceptions.BusinessException;
import com.news.forms.PostFilterForm;
import com.news.forms.PostForm;
import com.news.models.Post;
import com.news.models.User;
import com.news.services.PostService;
import com.news.utils.FileUtil;
import com.news.utils.ImageUtil;

@Controller
public class PostController {

	@Autowired
	PostService postService;

	private static final int PAGE_SIZE = 3;
	
	@RequestMapping("/404")
	public String accessDenied() {
		return "/404";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("categories", Category.values());
		return "login";
	}
	
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String checkLogin(Model model, HttpServletRequest request) {
		String userEmail ="conguan@gmail.com";
		String userPwd =  "1234";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
		
        if(email.equals(userEmail) && password.equals(userPwd)) {
        	
        	return "redirect:/admin/home";
        }
        return "login";
		
	}

	
	@RequestMapping(value = { "", "/", "/category"})
	public String index(Model model, @ModelAttribute("postForm") PostForm postForm, 
			@RequestParam(defaultValue = "1", required = false) int page) throws BusinessException, FileNotFoundException, IOException {
		model.addAttribute("posts", postService.filter(null, page, PAGE_SIZE));
		model.addAttribute("totalPage", postService.getTotalPage(null, PAGE_SIZE));
		model.addAttribute("categories", Category.values());
		model.addAttribute("page", page);
		return "index";
	}
	

	@RequestMapping(value = "/post/detail", method = RequestMethod.GET)
	public String postDetail(Model model, String title) {
		PostFilterForm form = new PostFilterForm();
		form.setTitle(title);
		List<Post> posts = postService.filter(form, 1, 1); // detail nên page_size: 1
		Post post = posts.get(0);
		model.addAttribute("post", post);
		model.addAttribute("totalPage", postService.getTotalPage(null, PAGE_SIZE));
		model.addAttribute("categories", Category.values());
		return "postdetail";
	}

	@RequestMapping(value = "post/edit", method = RequestMethod.GET)
	public String postEdit(Model model, long id) {
		PostFilterForm form = new PostFilterForm();
		form.setId(id);
		Post post = postService.filter(form, 1, 5).get(0);

		model.addAttribute("categories", Category.values());
		model.addAttribute("post", post);
		return "editnew";
	}

	@RequestMapping(value = { "post/add" }, method = RequestMethod.GET)
	public String postAdd(Model model) {

		model.addAttribute("categories", Category.values());
		model.addAttribute("postForm", new PostForm());
		return "addnew";
	}

	@RequestMapping(value = { "post/add" }, method = RequestMethod.POST)
	public String postAdd(@ModelAttribute("postForm") @Validated PostForm postForm, Model model, BindingResult result)
			throws BusinessException, FileNotFoundException, IOException {

		model.addAttribute("categories", Category.values());
		model.addAttribute("postForm", new PostForm());
		if (result.hasErrors()) {
			return "addnew";
		}

		if (postForm.getThumbnail().isEmpty()) {
			System.out.println("thumbnail is empty");
			return "addnew";
		}
		
		// lấy đuôi định dạng của image
		String imageExtension = ImageUtil.isImageExtension(postForm.getThumbnail().getOriginalFilename());

		// postForm convert post
		Post post = new Post();
		post.setTitle(postForm.getTitle());
		post.setShortContent(postForm.getShortContent());
		post.setContent(postForm.getContent());
		post.setCategory(postForm.getCategory());
		post.setThumbnail(
				postService.uploadImage(postForm.getThumbnail().getBytes(), post.getId() + "." + imageExtension));
		postService.add(post);

		return "redirect:/";

	}

	 @RequestMapping(value = {"post/edit"}, method = RequestMethod.POST)
	 public String updateNew(@ModelAttribute("postForm") @Validated PostForm postForm,
			 		Model model, BindingResult result) throws BusinessException, FileNotFoundException, IOException {
		 
		 
		 if(result.hasErrors()) {
			 return "editnew";
		 }
		 
		// lấy đuôi định dạng của image
		String imageExtension = ImageUtil.isImageExtension(postForm.getThumbnail().getOriginalFilename());
		 // postForm convert post
		Post post = new Post();
		post.setThumbnail(
					postService.uploadImage(postForm.getThumbnail().getBytes(), post.getId() + "." + imageExtension));
			postService.add(post);
		 BeanUtils.copyProperties(postForm, post);
		 postService.update(post);
		 
		 return "redirect:/post/detail";

	 }

	@RequestMapping(value = { "post/delete" }, method = RequestMethod.GET)
	public String postDelete(HttpServletRequest request, Model model, @RequestParam long id) {

		postService.delete(id);

		return "redirect:/";
	}

	/**
	 * Xem hình ảnh
	 * 
	 * @param imageName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@GetMapping("/image/{imageName}")
	@ResponseBody
	public ResponseEntity<byte[]> viewImage(@PathVariable String imageName) throws FileNotFoundException, IOException {
		byte[] image = FileUtil.readByte(ImageUtil.initFile(imageName));
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

	/*
	 * ADMIN CONTROLLER
	 */

	 @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
		public String home(Model model, @ModelAttribute("postForm") PostForm postForm, 
				@ModelAttribute("postImg") Post post,
				@RequestParam(defaultValue = "1", required = false) int page) throws BusinessException, FileNotFoundException, IOException {
			model.addAttribute("postImg", post.getThumbnail());
			model.addAttribute("posts", postService.filter(null, page, PAGE_SIZE));
			model.addAttribute("totalPage", postService.getTotalPage(null, PAGE_SIZE));
			model.addAttribute("page", page);
			return "home";
	 }

	/*
	 * tổng trang trang hiện tại
	 * 
	 */
}