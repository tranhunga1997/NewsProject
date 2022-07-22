package com.news.models;


import java.io.Serializable;
import com.news.constants.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = Constant.POST_SERIAL;
	private String email;
	private String password;
}
