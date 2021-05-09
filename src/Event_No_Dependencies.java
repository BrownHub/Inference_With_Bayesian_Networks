
public class Event_No_Dependencies extends Event {
	private double probability;
	
	public Event_No_Dependencies(double probability) {
		super();
		this.probability = probability;
	}

	@Override
	public double[] get_probabilities() {
		double[] probabilities;
		
		if (this.is_set) {
			probabilities = new double[1];
			
			if (this.is_true) {
				probabilities[0] = this.probability;
			} else {
				probabilities[0] = 1 - this.probability;
			}
		} else {
			probabilities = new double[2];
			
			probabilities[0] = this.probability;
			probabilities[1] = 1 - this.probability;
		}
		
		
		
		return probabilities;
	}
	
}
