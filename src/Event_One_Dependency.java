import java.util.HashMap;
import java.util.Map;

public class Event_One_Dependency extends Event {
	
	private Map<Boolean, Double> probability_table;
	Event dependency;
	
	public Event_One_Dependency (double[] probabilities, Event dependency) {
		super();
		this.probability_table = new HashMap<Boolean, Double>();
		this.probability_table.put(true, probabilities[0]);
		this.probability_table.put(false, probabilities[1]);
		this.dependency = dependency;
	}
	
	@Override
	public double[] get_probabilities() {
		double[] probabilities;
		
		if (this.is_set) {
			//probability of this event if both it and its dependency are known
			if (this.dependency.get_is_set()) {
				probabilities = new double[1];
				
				if (this.is_true) {
					//event is true
					probabilities[0] = this.probability_table.get(dependency.get_is_true());
				} else {
					//event is false
					probabilities[0] = 1 - this.probability_table.get(dependency.get_is_true());
				}
				
			} else {
				//probabilities of this event if it is known, but its dependency is not
				probabilities = new double[2];
				
				if (this.is_true) {
					//event is true / dependency is true
					probabilities[0] = this.probability_table.get(true);
					//event is true / dependency is false
					probabilities[1] = this.probability_table.get(false);
				} else {
					//event is false / dependency is true
					probabilities[0] = 1 - this.probability_table.get(true);
					//event is false / dependency is false
					probabilities[1] = 1 - this.probability_table.get(false);
				}
			}
			
		} else {
			//probabilities of this event if it is not known, but its dependency is
			if (this.dependency.get_is_set()) {
				probabilities = new double[2];
				
				// event is true / dependency is known
				probabilities[0] = this.probability_table.get(dependency.get_is_true());
				// event is false / dependency is known
				probabilities[1] = 1 - this.probability_table.get(dependency.get_is_true());

			} else {
				//probabilities of this event if neither it nor its dependency is known
				probabilities = new double[4];
				
				// event is true / dependency is true
				probabilities[0] = this.probability_table.get(true);
				// event is false / dependency is true
				probabilities[1] = 1 - this.probability_table.get(true);
				// event is true / dependency is false
				probabilities[2] = this.probability_table.get(false);
				// event is false / dependency is false
				probabilities[3] = 1 - this.probability_table.get(false);
				
			}
		}
		
		return probabilities;
	}

}
