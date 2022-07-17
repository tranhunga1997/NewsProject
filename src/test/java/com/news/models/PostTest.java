package com.news.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostTest {

	@Test
	void test() {
		Post post1 = new Post();
		System.out.println(post1.hashCode());
	}

}
