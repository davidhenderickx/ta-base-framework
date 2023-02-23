package frmwrk.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
	
	public static void createFolderIfNotExists(String path) {
		File directory = new File(path);
		if (! directory.exists()){
		    directory.mkdirs();
		}
	}
	
	public static void CreateFile(String filePath) {
		try {
			File myObj = new File(filePath);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void WriteToFile(String filePath, String text) {
		try {
			FileWriter myWriter = new FileWriter(filePath, true);
			myWriter.write(text);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void WriteToFile(String filePath, String[] textArray) {
		try {

			String text = "";
			for (String textItem : textArray) {
				text = text + textItem + ";";
			}
			if (text != null && text.length() > 0 && text.charAt(text.length() - 1) == ';') {
				text = text.substring(0, text.length() - 1);
		    }			

			FileWriter myWriter = new FileWriter(filePath, true);
			myWriter.append(text);
			myWriter.append('\n'); //append new line
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
