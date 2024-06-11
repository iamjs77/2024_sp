package list;

public class InputDto {

	public String name; 
	public int korean; 
	public int english; 
	public int math; 
	
	public InputDto(String name, int korean, int english, int math) {
		this.name = name;
		this.korean = korean; 
		this.english = english;
		this.math = math;
		 
	}
	
	public InputDto(String[] inputs) {
		this.name = inputs[0];
		this.korean = Integer.parseInt(inputs[1]);
		this.english = Integer.parseInt(inputs[2]);
		this.math = Integer.parseInt(inputs[3]);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		
		sb.append(this.name).append("\t").append(this.korean)
			.append("\t").append(this.english).append("\t").append(this.math);
		
		
		return sb.toString();
			
	}
}
