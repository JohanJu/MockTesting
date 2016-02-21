package johan;

public class Series {
	CalcInt ci;

	public String calc(CalcInt ci, int a, int b) {
		if (this.ci == null)
			return "Last: " + ci.last(a, b) + "\n All:" + ci.all(a, b);
		return "Last: " + this.ci.last(a, b) + "\n All:" + this.ci.all(a, b);
	}
}
