package com.news.models;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import com.news.constants.Constant;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Datas implements Serializable {
	private static final long serialVersionUID = Constant.POST_SERIAL;
	private List<Post> posts = new Vector<>(); 

}
