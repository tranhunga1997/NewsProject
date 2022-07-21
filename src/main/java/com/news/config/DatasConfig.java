package com.news.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.news.models.Datas;
import com.news.utils.FileUtil;

@Configuration
public class DatasConfig {
	
	private Datas datas;
	
	@PostConstruct
	public void postConstruct() throws FileNotFoundException, ClassNotFoundException {
		try {
		this.datas =  (Datas) FileUtil.readObject(FileUtil.initFile("post-data.ser"));
		} catch (IOException e) {
			this.datas = new Datas();
		}
		
	}
	
	@Bean
	public Datas beanInit() {
		return datas;
	}
	
	@PreDestroy
	public void preDestroy() throws FileNotFoundException, IOException {
		FileUtil.writeObject(datas, FileUtil.initFile("post-data.ser"));
	}
	
}
