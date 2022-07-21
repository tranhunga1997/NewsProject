package com.news.utils;

import java.io.File;

import com.news.constants.Constant;
import com.news.exceptions.BusinessException;

public class ImageUtil {
	
	public static String isImageExtension(String image) throws BusinessException {
		if (!image.endsWith(".jpg") && image.endsWith(".jpeg") && image.endsWith(".png")) {
			throw new BusinessException("Định dạng hình ảnh không đúng.");
		}
		return image.split("[.]")[1];
	}

	// tạo đối tượng file
	public static File initFile(String image) {
		return new File(Constant.IMAGE_ROOT_PATH, image);
	}
	
	
}
