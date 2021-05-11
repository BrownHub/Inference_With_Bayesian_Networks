import java.util.HashMap;
import java.util.Map;

public class Event_One_Dependency extends Event {
	
	private Map<String, Double> probability_table;
	Event dependency;
	
	public Event_One_Dependency (char name, double[] probabilities, Event dependency) {
		super();
		this.probability_table = new HashMap<String, Double>();
		this.probability_table.put(get_key(true), probabilities[0]);
		this.probability_table.put(get_key(false), probabilities[1]);
		this.dependency = dependency;
		
		this.event_name = name;
	}
	
	@Override
	public void set_dependencies(char name, boolean true_false) {
		if (this.dependency.get_name() == name) {
			this.dependency.set_event(true);
			this.dependency.set_is_true(true_false);
		}
	}

	@Override
	public void reset_dependencies() {
		this.dependency.set_event(false);
	}
	
	public String get_key(Boolean dep) {
		String key = new String();
		
		if (dep) {
			key = "T";
		} else {
			key = "F";
		}
		
		return key;
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
					probabilities[0] = this.probability_table.get(get_key(dependency.get_is_true()));
				} else {
					//event is false
					probabilities[0] = 1 - this.probability_table.get(get_key(dependency.get_is_true()));
				}
				
			} else {
				//probabilities of this event if it is known, but its dependency is not
				probabilities = new double[2];
				
				if (this.is_true) {
					//event is true / dependency is true
					probabilities[0] = this.probability_table.get(get_key(true));
					//event is true / dependency is false
					probabilities[1] = this.probability_table.get(get_key(false));
				} else {
					//event is false / dependency is true
					probabilities[0] = 1 - this.probability_table.get(get_key(true));
					//event is false / dependency is false
					probabilities[1] = 1 - this.probability_table.get(get_key(false));
				}
			}
			
		} else {
			//probabilities of this event if it is not known, but its dependency is
			if (this.dependency.get_is_set()) {
				probabilities = new double[2];
				
				// event is true / dependency is known
				probabilities[0] = this.probability_table.get(get_key(dependency.get_is_true()));
				// event is false / dependency is known
				probabilities[1] = 1 - this.probability_table.get(get_key(dependency.get_is_true()));

			} else {
				//probabilities of this event if neither it nor its dependency is known
				probabilities = new double[4];
				
				// event is true / dependency is true
				probabilities[0] = this.probability_table.get(get_key(true));
				// event is false / dependency is true
				probabilities[1] = 1 - this.probability_table.get(get_key(true));
				// event is true / dependency is false
				probabilities[2] = this.probability_table.get(get_key(false));
				// event is false / dependency is false
				probabilities[3] = 1 - this.probability_table.get(get_key(false));
				
			}
		}
		
		return probabilities;
	}

}
