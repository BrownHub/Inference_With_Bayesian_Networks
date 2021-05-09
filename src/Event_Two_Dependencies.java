import java.util.HashMap;
import java.util.Map;

public class Event_Two_Dependencies extends Event{
	
	private Map<Boolean[], Double> probability_table;
	Event[] dependencies;

	public Event_Two_Dependencies(double[] probabilities, Event dependency_1, Event dependency_2) {
		super();
		this.probability_table = new HashMap<Boolean[], Double>();
		Boolean[] key = {true, true};
		this.probability_table.put(key, probabilities[0]);
		key[1] = false;
		this.probability_table.put(key, probabilities[1]);
		key[0] = false;
		key[1] = true;
		this.probability_table.put(key, probabilities[2]);
		key[1] = false;
		this.probability_table.put(key, probabilities[3]);
		
		this.dependencies = new Event[2];
		this.dependencies[0] = dependency_1;
		this.dependencies[1] = dependency_2;
		
	}

	@Override
	public double[] get_probabilities() {
		// TODO Auto-generated method stub
		double[] probabilities;
		
		if (this.is_set) {
			//probabilities of this event if it and its dependencies are known
			if (this.dependencies[0].get_is_set() && this.dependencies[1].get_is_set()) {
				probabilities = new double[1];
				Boolean[] key = {this.dependencies[0].get_is_true(), this.dependencies[1].get_is_true()};
				
				if (this.is_true) {
					//event is true / dependency 1 is known / dependency 2 is known
					probabilities[0] = this.probability_table.get(key);
				} else {
					//event is false / dependency 1 is known / dependency 2 is known
					probabilities[0] = 1 - this.probability_table.get(key);
				}
				
			} else if (this.dependencies[0].get_is_set() || this.dependencies[1].get_is_set()) {
				//probabilities of this event if it and only one of its dependencies are known
				probabilities = new double[2];
				
				if (this.dependencies[0].get_is_set()) {
					Boolean[] key = {this.dependencies[0].get_is_true(), true};
					
					if (this.is_true) {
						//event is true / dependency 1 is known / dependency 2 is true
						probabilities [0] = this.probability_table.get(key);
						
						//event is true / dependency 1 is known / dependency 2 is false
						key[1] = false;
						probabilities [1] = this.probability_table.get(key);
					} else {
						//event is true / dependency 1 is known / dependency 2 is true
						probabilities [0] = 1 - this.probability_table.get(key);
						
						//event is false / dependency 1 is known / dependency 2 is false
						key[1] = false;
						probabilities [1] = 1 - this.probability_table.get(key);
					}
					
				}
			}
		}
		
		return null;
	}

}