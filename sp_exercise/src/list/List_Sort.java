package list;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class List_Sort {

	private static final String INPUT_RESOURCE = "./list_map";

	public static void main(String[] args) throws IOException {

		File input = new File(INPUT_RESOURCE, "List_Sample.txt");


		List<InputDto> results = new ArrayList<>();

		List<String> lines = Files.readAllLines(input.toPath());

		for (String line : lines) {
			String[] inputs = line.split("\t");

			InputDto dto = new InputDto(inputs);
			results.add(dto);
		}
		
		Scanner scanner = new Scanner(System.in);
		boolean flag = false; 
		
		String param = "QUIT";
		while(!flag && scanner.hasNextLine()) {
			
			param = scanner.nextLine(); 
			
			sortList(param, results);
			
			for(InputDto dto : results) {
				System.out.println(dto);
			}
			
			if(param.equals("QUIT")) {
				flag = true;
			}
			
		}
		scanner.close();
	}

	private static void sortList(String type, List<InputDto> results) {

		Collections.sort(results, new Comparator<InputDto>() {

			@Override
			public int compare(InputDto o1, InputDto o2) {

				int result = 0;

				switch (type) {
				case "KOREAN":
					result = o2.korean - o1.korean;
					break;

				case "ENGLISH":
					result = o2.english - o1.english;

					break;
				case "MATH":
					result = o2.math - o1.math; 
				
				case "PRINT":
					result = o2.name.compareTo(o1.name); 
					break;
				default:
					break;
				}

				if (result == 0) {
					result = o1.name.compareTo(o2.name);
				}
				return result;
			}
		});

	}
}
