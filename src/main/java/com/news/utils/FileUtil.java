package com.news.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.news.constants.Constant;

public class FileUtil {
	private FileUtil() {}
	
	public static File initFile(String fileName) {
		return new File(Constant.DATA_ROOT_PATH, fileName);
	}
	
	public static void writeObject(Object obj, File file) throws FileNotFoundException, IOException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(obj);
		}
	}
	
	public static Object readObject(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return ois.readObject();
		}
	}
	
	public static byte[] readByte(File file) throws FileNotFoundException, IOException {
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
			byte[] result = new byte[bis.available()];
			for(int i=0; i<result.length; i++) {
				result[i] = (byte) bis.read();
			}
			return result;
		}
	}
	
	public static void writeByte(File file, byte[] bytes) throws FileNotFoundException, IOException {
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
			bos.write(bytes);
		}
	}
	
	
}
