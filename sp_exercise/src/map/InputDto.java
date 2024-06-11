package map;

/**
 * 월 사번 이름 A프로젝트 B프로젝트 C프로젝트 
 * 1 201001 Kim 1 0 
 *
 */
public class InputDto {

	public int num; 
	public String name; 
	public int aa; 
	public int bb; 
	public int cc; 
	
	public InputDto(String[] inputs) {
		this.name = inputs[1];
		this.aa = Integer.parseInt(inputs[2]);
		this.bb = Integer.parseInt(inputs[3]);
		this.cc = Integer.parseInt(inputs[4]);
	}
}
