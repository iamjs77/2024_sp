package files.answer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;

public class LogReader {

	public static void main(String[] args) throws Exception {
		File f = new File("LOG.TXT");
		while (!f.exists() ) {
			Thread.sleep(100);
			continue;
		}
		
		BufferedReader br = new BufferedReader(new FileReader("LOG.TXT"));
		while (true) {
			String line = br.readLine();
			if (line == null) {
				Thread.sleep(1);
				continue;
			}
			String [] words = line.split(" - ");
			String [] strNums = words[1].split(" ");
			int sum = Integer.parseInt(strNums[0]) + Integer.parseInt(strNums[1]);	
			System.out.println(String.format("[%s] %d", LocalDateTime.now(), sum));
		}
		//br.close();
	
	}

}
