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

	static void solve4() throws IOException {
		String filePath = "sample.json";
		Path jsonFilePath = Paths.get(filePath);
		String wholeData = new String(Files.readAllBytes(jsonFilePath));
//		Gson gson = new Gson();
//		JsonTest jTest = gson.fromJson(wholeData, JsonTest.class);
//		System.out.println(jTest.name + " " + jTest.age);
	}

//	Read Directory
	void FileDirList()
	{
		File directory = new File(".");
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if(file.isDirectory()) {
				System.out.println("["+file.getName()+"]");
			}
			else {
				System.out.println(file.getName());
			}
		}
	}

//	cf) 전체 디렉토리 재귀 호출
	void FileSearchAll(String path)
	{
		File directory = new File(path);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isDirectory()) {
				FileSearchAll(file.getPath());
			}
			else {
				System.out.println(file.getName());
			}
		}
	}

//	java 8 files.walk
	void fileSearchAll_Files_walk() throws IOException {
		Files.walk(Paths.get("/path/to/folder"))
				.filter(Files::isRegularFile)
				.forEach(System.out::println);
		 Files.walk(Paths.get("/path/to/folder"))
				.filter(Files::isRegularFile)
				.collect(Collectors.toList());

		 List<File> filesInFolder = Files.walk(Paths.get("/path/to/folder"))
				.filter(Files::isRegularFile)
				.map(Path::toFile)
				.collect(Collectors.toList());

	}

//	file Size
	public class JavaGetFileSize {

		static final String FILE_NAME = "/Users/pankaj/Downloads/file.pdf";

		public void main(String[] args) {
			File file = new File(FILE_NAME);
			if (!file.exists() || !file.isFile()) return;

			System.out.println(getFileSizeBytes(file));
			System.out.println(getFileSizeKiloBytes(file));
			System.out.println(getFileSizeMegaBytes(file));
		}

		private String getFileSizeMegaBytes(File file) {
			return (double) file.length() / (1024 * 1024) + " mb";
		}

		private  String getFileSizeKiloBytes(File file) {
			return (double) file.length() / 1024 + "  kb";
		}

		private  String getFileSizeBytes(File file) {
			return file.length() + " bytes";
		}
	}
}
