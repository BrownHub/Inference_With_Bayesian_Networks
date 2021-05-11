import java.util.HashMap;
import java.util.Map;

public class Event_Two_Dependencies extends Event{
	
	private Map<String, Double> probability_table;
	Event[] dependencies;

	public Event_Two_Dependencies(char name, double[] probabilities, Event dependency_1, Event dependency_2) {
		super();
		this.probability_table = new HashMap<String, Double>();
		String key1 = get_key(true, true);
		String key2 = get_key(true, false);
		String key3 = get_key(false, true);
		String key4 = get_key(false, false);
		this.probability_table.put(key1, probabilities[0]);
		this.probability_table.put(key2, probabilities[1]);
		this.probability_table.put(key3, probabilities[2]);
		this.probability_table.put(key4, probabilities[3]);
		
		this.dependencies = new Event[2];
		this.dependencies[0] = dependency_1;
		this.dependencies[1] = dependency_2;
		
		this.event_name = name;
		
	}
	
	@Override
	public void set_dependencies(char name, boolean true_false) {
		for (Event event : this.dependencies) {
			if (event.get_name() == name) {
				event.set_event(true);
				event.set_is_true(true_false);
			}
		}
	}
	
	@Override
	public void reset_dependencies() {
		for (Event event : dependencies) {
			event.set_event(false);
		}
	}
	
	public String get_key(Boolean one, Boolean two) {
		String key = new String();
		if (one && two) {
			key = "TT";
		} else if (one){
			key = "TF";
		} else if (two) {
			key = "FT";
		} else {
			key = "FF";
		}
		
		return key;
	}

	@Override
	public double[] get_probabilities() {
		double[] probabilities;
		
		if (this.is_set) {
			//probabilities of this event if it and its dependencies are known
			if (this.dependencies[0].get_is_set() && this.dependencies[1].get_is_set()) {
				probabilities = new double[1];
				String key = get_key(this.dependencies[0].get_is_true(), this.dependencies[1].get_is_true());
				
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
				
				//dependency 1 is known
				if (this.dependencies[0].get_is_set()) {
					String key = get_key(this.dependencies[0].get_is_true(), true);
					
					if (this.is_true) {
						//event is true / dependency 1 is known / dependency 2 is true
						probabilities [0] = this.probability_table.get(key);
						
						//event is true / dependency 1 is known / dependency 2 is false
						key = get_key(this.dependencies[0].get_is_true(), false);
						probabilities [1] = this.probability_table.get(key);
					} else {
						//event is false / dependency 1 is known / dependency 2 is true
						probabilities [0] = 1 - this.probability_table.get(key);
						
						//event is false / dependency 1 is known / dependency 2 is false
						key = get_key(this.dependencies[0].get_is_true(), false);
						probabilities [1] = 1 - this.probability_table.get(key);
					}
					
				} else {
					//dependency 2 is known
					String key = get_key(true, this.dependencies[1].get_is_true());
					
					if (this.is_true) {
						//event is true / dependency 1 is true / dependency 2 is known
						probabilities [0] = this.probability_table.get(key);
						
						//event is true / dependency 1 is false / dependency 2 is known
						key = get_key(false, this.dependencies[1].get_is_true());
						probabilities [1] = this.probability_table.get(key);
					} else {
						//event is false / dependency 1 is true / dependency 2 is known
						probabilities [0] = 1 - this.probability_table.get(key);
						
						//event is false / dependency 1 is false / dependency 2 is known
						key = get_key(false, this.dependencies[1].get_is_true());
						probabilities [1] = 1 - this.probability_table.get(key);
					}
					
				}
				
			} else {
				//probabilities of this event if it is known and none of its dependencies are known
				probabilities = new double[4];
				String key = get_key(true, true);
				
				if (this.is_true) {
					//Event is true / dependency 1 is true / dependency 2 is true
					probabilities [0] = this.probability_table.get(key);
					
					//Event is true / dependency 1 is true / dependency 2 is false
					key = get_key(true, false);
					probabilities [1] = this.probability_table.get(key);
					
					//Event is true / dependency 1 is false / dependency 2 is true
					key = get_key(false, true);
					probabilities [2] = this.probability_table.get(key);
					
					//Event is true / dependency 1 is false / dependency 2 is false
					key = get_key(false, false);
					probabilities [3] = this.probability_table.get(key);
				} else {
					//Event is false / dependency 1 is true / dependency 2 is true
					probabilities [0] = 1 - this.probability_table.get(key);
					
					//Event is false / dependency 1 is true / dependency 2 is false
					key = get_key(true, false);
					probabilities [1] = 1 - this.probability_table.get(key);
					
					//Event is false / dependency 1 is false / dependency 2 is true
					key = get_key(false, true);
					probabilities [2] = 1 - this.probability_table.get(key);
					
					//Event is false / dependency 1 is false / dependency 2 is false
					key = get_key(false, false);
					probabilities [3] = 1 - this.probability_table.get(key);
				}
				
			}
			
		} else {
			//probabilities of this event if it is not known and its dependencies are known
			if (this.dependencies[0].get_is_set() && this.dependencies[1].get_is_set()) {
				probabilities = new double[2];
				String key = get_key(this.dependencies[0].get_is_true(), this.dependencies[1].get_is_true());
				
				//Event is true / dependency 1 is known / dependency 2 is known
				probabilities[0] = this.probability_table.get(key);
				//Event is false / dependency 1 is known / dependency 2 is known
				probabilities[1] = 1 - this.probability_table.get(key);
				
			} else if (this.dependencies[0].get_is_set() || this.dependencies[1].get_is_set()) {
				//probabilities of this event if it is not known and only one of its dependencies are known
				probabilities = new double[4];
				
				//dependency 1 is known
				if (this.dependencies[0].get_is_set()) {
					String key = get_key(this.dependencies[0].get_is_true(), true);
					

					//event is true / dependency 1 is known / dependency 2 is true
					probabilities [0] = this.probability_table.get(key);
					//event is false / dependency 1 is known / dependency 2 is true
					probabilities [1] = 1 - this.probability_table.get(key);
					
					//event is true / dependency 1 is known / dependency 2 is false
					key = get_key(this.dependencies[0].get_is_true(), false);
					probabilities [2] = this.probability_table.get(key);
					//event is false / dependency 1 is known / dependency 2 is false
					probabilities [3] = 1 - this.probability_table.get(key);


				} else {
					//dependency 2 is known
					String key = get_key(true, this.dependencies[1].get_is_true());
					
					//event is true / dependency 1 is true / dependency 2 is known
					probabilities [0] = this.probability_table.get(key);
					//event is false / dependency 1 is true / dependency 2 is known
					probabilities [1] = 1 - this.probability_table.get(key);
					
					//event is true / dependency 1 is false / dependency 2 is known
					key = get_key(false, this.dependencies[1].get_is_true());
					probabilities [2] = this.probability_table.get(key);
					//event is false / dependency 1 is false / dependency 2 is known
					probabilities [3] = 1 - this.probability_table.get(key);
					
				}
				
			} else {
				//probabilities of this event if none of its dependencies are known
				probabilities = new double[8];
				String key = get_key(true, true);
				
				//Event is true / dependency 1 is true / dependency 2 is true
				probabilities [0] = this.probability_table.get(key);
				//Event is true / dependency 1 is true / dependency 2 is true
				probabilities [1] = 1 - this.probability_table.get(key);

				//Event is true / dependency 1 is true / dependency 2 is false
				key = get_key(true, false);
				probabilities [2] = this.probability_table.get(key);
				//Event is false / dependency 1 is true / dependency 2 is false
				probabilities[3] = 1 - this.probability_table.get(key);

				//Event is true / dependency 1 is false / dependency 2 is true
				key = get_key(false, true);
				probabilities [4] = this.probability_table.get(key);
				//Event is false / dependency 1 is false / dependency 2 is true
				probabilities [5] = 1 - this.probability_table.get(key);

				//Event is true / dependency 1 is false / dependency 2 is false
				key = get_key(false, false);
				probabilities [6] = this.probability_table.get(key);
				//Event is false / dependency 1 is false / dependency 2 is false
				probabilities [7] = 1 - this.probability_table.get(key);

			}
			
		}
		
		return probabilities;
	}

}
