package com.news.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.news.models.Post;
import com.news.models.Datas;

class FileUtilTest {

	@Test
	void testInitFile() {
		File file = FileUtil.initFile("abc.txt");
		System.out.println(file.getAbsolutePath());
	}

	@Test
	void testWriteObject() {
		Post post1 = new Post();
		post1.setTitle("tin tuc 1");
		Post post2 = new Post();
		post2.setTitle("tin tuc 2");
		Post post3 = new Post();
		post3.setTitle("tin tuc 3");
		Datas datas = new Datas();
		datas.getPosts().addAll(Arrays.asList(post1, post2, post3));
		try {
			FileUtil.writeObject(datas, FileUtil.initFile("post-data.ser"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testReadObject() {
		try {
			Datas posts = (Datas) FileUtil.readObject(FileUtil.initFile("post-data.ser"));
			posts.getPosts().forEach(System.out::println);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testReadByte() {
		fail("Not yet implemented");
	}

	@Test
	void testWriteByte() {
		fail("Not yet implemented");
	}

}
