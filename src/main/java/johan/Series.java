package johan;
public class Series {
	
	public String calc(CalcInt ci, int a,int b){
		return "Last: "+ci.last(a, b)+"\n All:"+ci.all(a, b);
	}
}
