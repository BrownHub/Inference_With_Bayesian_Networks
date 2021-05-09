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
		// TODO Auto-generated method stub
		double[] probabilities;
		
		if (this.is_set) {
			//probability of this event if both it and its dependency are known
			if (this.dependency.get_is_set()) {
				probabilities = new double[1];
				
				if (this.is_true) {
					probabilities[0] = this.probability_table.get(dependency.get_is_true());
				} else {
					probabilities[0] = 1 - this.probability_table.get(dependency.get_is_true());
				}
				
			} else {
				//probabilities of this event if it is known, but its dependency is not
				probabilities = new double[2];
				
				if (this.is_true) {
					probabilities[0] = this.probability_table.get(true);
					probabilities[1] = this.probability_table.get(false);
				} else {
					probabilities[0] = 1 - this.probability_table.get(true);
					probabilities[1] = 1 - this.probability_table.get(false);
				}
			}
			
		} else {
			//probabilities of this event if it is not known, but its dependency is
			if (this.dependency.get_is_set()) {
				probabilities = new double[2];
				
				probabilities[0] = this.probability_table.get(dependency.get_is_true());
				probabilities[1] = 1 - this.probability_table.get(dependency.get_is_true());

			} else {
				//probabilities of this event if neither it nor its dependency is known
				probabilities = new double[4];
				
				probabilities[0] = this.probability_table.get(true);
				probabilities[1] = 1 - this.probability_table.get(true);
				probabilities[2] = this.probability_table.get(false);
				probabilities[3] = 1 - this.probability_table.get(false);
				
			}
		}
		
		return probabilities;
	}

}
