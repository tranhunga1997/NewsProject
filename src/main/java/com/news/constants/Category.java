package com.news.constants;

public enum Category {
	Environmental("môi trường"),
	Government("chính phủ"),
	Media("truyền thông"),
	Sports("thể thao"),
	Technology("công nghệ"),
	Weather("thời tiết");
	
	private String name;
	
	private Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
