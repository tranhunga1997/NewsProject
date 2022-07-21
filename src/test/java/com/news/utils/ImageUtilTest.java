package com.news.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.news.exceptions.BusinessException;

class ImageUtilTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIsImageExtension() throws BusinessException {
		String isImage = ImageUtil.isImageExtension("My-Java-Roadmap-1.png");
		System.out.println(isImage);
	}

}
