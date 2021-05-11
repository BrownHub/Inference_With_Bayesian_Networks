
public class Event_No_Dependencies extends Event {
	private double probability;
	
	public Event_No_Dependencies(char name, double probability) {
		super();
		this.probability = probability;
		this.event_name = name;
	}
	
	@Override
	public void set_dependencies(char name, boolean true_false) {
		//Intentionally blank
	}
	
	@Override
	public void reset_dependencies() {
		//Intentionally blank
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
