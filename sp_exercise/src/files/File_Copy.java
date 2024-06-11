package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class File_Copy {

	/**
	 * INPUT 폴더 하위에 위치한 파일들의 파일명(상대경로 포함), 크기를 Console화면에 출력하시오.
	 */
	public static void main(String[] args) {
		
		List<File> result = new ArrayList<>();
		try {
			List<File> filesInFolder = Files.walk(Paths.get("./INPUT"))
			        .filter(Files::isRegularFile)
			        .map(Path::toFile)
			        .collect(Collectors.toList());
			for(File file : filesInFolder) {
				
				System.out.println(file.getPath() + " : " + file.length() + "bytes.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
