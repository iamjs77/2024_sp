package process;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainRunner {

	private static final String INPUT_RESOURCE = "./thread";

	public static void main(String[] args) throws Exception {

		System.out.println(String.format("Start - " + new Date().toString())); // 현재시각출력
		List<Thread> threads = new ArrayList<>();
		File input = new File(INPUT_RESOURCE, "NUM.txt");

		List<String> lines = Files.readAllLines(input.toPath());

		for (String line : lines) {
			String[] s1 = line.split(" ");
			String i1 = s1[0];
			String i2 = s1[1];

			SubThread st = new SubThread(i1, i2);

			Thread t = new Thread(st);
			t.start();
			threads.add(t);
		}

		for (Thread st : threads) {
			st.join();
		}
		System.out.println("End - " + new Date().toString()); // 현재시각출력
	}
}
