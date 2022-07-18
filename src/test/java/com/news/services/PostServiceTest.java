package com.news.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.news.exceptions.BusinessException;
import com.news.forms.PostFilterForm;
import com.news.models.Post;

@SpringBootTest
class PostServiceTest {
	@Autowired
	PostService postService;
	
	@Test
	void testFilter() {
		PostFilterForm filter = new PostFilterForm();
		filter.setTitle("3");
		List<Post> posts = postService.filter(null, 1, 5);
		posts.forEach(System.out::println);
	}

	@Test
	void testAdd() {
		Post post = new Post();
		post.setTitle("tin tuc 4");
		try {
			postService.add(post);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdate() {
		Post post = new Post();
		try {
			postService.update(post);
		} catch (BusinessException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	void testDelete() {
		
	}
	
	@Test
	void test1() {
		
	}

}
