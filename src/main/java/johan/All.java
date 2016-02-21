package johan;

public class All implements CalcInt{

	public int last(int a, int b) {
		return a+b;
	}

	public String all(int a, int b) {
		String s = "";
		for (int i = 0; i <= b; i++) {
			s+=" "+(a+i);
		}
		return s;
	}
	
	
}
