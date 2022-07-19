package com.news.controllers;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.news.constants.Category;
import com.news.exceptions.BusinessException;
import com.news.forms.PostForm;
import com.news.models.Post;
import com.news.services.PostService;

@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	

	 @RequestMapping(value = {"", "/"})
	 public String index() {
		return "index";
	 }
	 
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	 public String login(Model model) {
		 return "login";
	 }
	 
	 @RequestMapping(value = "/newdetail", method = RequestMethod.GET)
	 public String newDetail(Model model){
		 return "newdetail";
	 }
	 
	 @RequestMapping(value = "/editnew", method = RequestMethod.GET)
	 public String editNew(Model model) {
		 return "editnew";
	 }
	 
	 @RequestMapping(value = "/addnew", method = RequestMethod.GET	)
	 public String addNew(Model model) {
		 
		 model.addAttribute("categories", Category.values());
		 model.addAttribute("postForm", new PostForm());
		 return "addnew";
	 }
	 
	 @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	 public String home(Model model) {
		 return "home";
	 }
	 
	 @RequestMapping(value = {"/admin/addnew"}, method =  RequestMethod.POST)
	 public String createNew(@ModelAttribute("postForm") @Validated PostForm postForm,
			 	Model model, BindingResult result,
			 		final RedirectAttributes redirectAttributes) throws BusinessException {
		 if(result.hasErrors()) {
			 return "addnew";
		 }
		 
		 // postForm convert post
		 Post post = new Post();
		 BeanUtils.copyProperties(postForm, post);
		 post.setThumbnail("chua xu ly");
		 postService.add(post);
		 
		 return "addnew";
		 
	 }
	 
	 @RequestMapping(value = {"/admin/editnew"}, method = RequestMethod.POST)
	 public String updateNew(@ModelAttribute("postForm") @Validated Post post,
			 		Model model, BindingResult result,
			 			final RedirectAttributes redirectAttributes) {
		 if(result.hasErrors()) {
			 return "editnew";
		 }
		 try {
			postService.update(post);
		} catch (Exception e) {
			// TODO: handle exception
			
			
		}
		 
		 return "editnew";
	 }
	 
//	 @RequestMapping(value = {"/admin/newdetail"}, method = RequestMethod.POST)
//	 public String deleteNew() {
//		 
//		 
//		 postService.delete(???);
//	 }
	 

}
