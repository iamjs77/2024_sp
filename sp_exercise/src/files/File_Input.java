package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.processing.SupportedOptions;

public class File_Input {

	/**
	 * INPUT 폴더 하위에 위치한 파일들 중 크기가 3Kbyte가 넘는 파일들은 모두 OUTPUT 폴더로 복사하시오. 
	 * (OUTPUT 폴더 및 서브 폴더 생성)
	 */
	public static void main(String[] args) {
		
		List<File> result = new ArrayList<>();
		try {
			List<File> filesInFolder = Files.walk(Paths.get("./INPUT"))
			        .filter(Files::isRegularFile)
			        .map(Path::toFile)
			        .collect(Collectors.toList());
			
			
			
			for(File file : filesInFolder) {
				
//				System.out.println(file.getPath() + " : " + file.length() + "bytes.");
				if(getFileSizeKB(file) >= 3) {
					result.add(file);
				}
			}
			
//			for(File file : result) {
//				
//				File out = new File("./OUTPUT", file.getParent()); 
//				
//				if(!out.exists()) {
//					out.mkdirs(); 
//				}
//				
//				Files.copy(file.toPath(), out.toPath());
//				
//			}
			
			for(File file : result) {
				File target = new File("./OUTPUT", file.getPath().substring(7)); 
				
				if(!target.exists()) {
					target.mkdirs(); 
				}
				
			 Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING );
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static long getFileSizeKB(File file) {
		return (file.length() / 1024);
	}
}
