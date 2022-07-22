package com.news.constants;

public enum Category {
	Environmental("Môi trường"),
	Government("Chính phủ"),
	Media("Truyền thông"),
	Sports("Thể thao"),
	Technology("Công nghệ"),
	Weather("Thời tiết");
	
	private String name;
	
	private Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
