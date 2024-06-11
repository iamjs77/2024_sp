package process;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class SubThread implements Runnable {

	private String i1;
	private String i2;

	public SubThread(String i1, String i2) {
		this.i1 = i1;
		this.i2 = i2;
	}

	@Override
	public void run() {
		try {
			String sum = getProcessOutput(Arrays.asList("./thread/add_2sec.exe",i1, i2));
			
			System.out.println(i1 +  " + " + i2 + " >>>>> " + sum);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getProcessOutput(List<String> cmdList) throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder(cmdList);
		Process process = builder.start();
		InputStream psout = process.getInputStream();
		byte[] buffer = new byte[1024];
		psout.read(buffer);
		return (new String(buffer));
	}
}
