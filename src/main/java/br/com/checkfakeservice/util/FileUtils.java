package br.com.checkfakeservice.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {
	
	public static String readFileAsString(String fileName)throws Exception
	  {
		Path path = Paths.get("src/test/resources/" + fileName);
	    return new String(Files.readAllBytes(path));
	  }
	
}