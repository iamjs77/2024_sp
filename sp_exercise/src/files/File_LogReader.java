package files;

import java.io.File;
import java.util.Scanner;

/**
 * LOG.TXT파일을 Monitoring하여, 
 * 새로운 라인이 쓰일 때 마다 숫자 두 개의 합을 콘솔에 출력하는 프로그램을 구현하시오. 
 *
 */
public class File_LogReader {

	public static void main(String[] args) throws Exception {
		
		File log = new File("LOG.txt");
		
		while(!log.exists()) {
			Thread.sleep(100);
			continue;
		}
		
		
		Scanner scanner = new Scanner(log);
		while (true) {
			if(!scanner.hasNextLine()) {
				Thread.sleep(10);
				System.out.println(">>>>");
				continue;
			}
			
				String line = scanner.nextLine();
				String[] words = line.split(" - ");
				String[] numbers = words[1].split(" ");
				
				int sum = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]); 
				System.out.println(words[0] + " " + sum);
		}
	}
}
