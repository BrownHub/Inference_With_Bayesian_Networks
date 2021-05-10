import java.util.ArrayList;

public class Bayesian_Network {
	private int num_events;
	private Event[] events;
	private double[][] event_probabilities;
	private ArrayList<Double> probabilities_to_be_summed;
	private static Bayesian_Network instance;
	
	public Bayesian_Network() {
		super();
		
		Event burglary = new Event_No_Dependencies(0.001);
		Event earthquake = new Event_No_Dependencies(0.002);
		
		double[] alarm_probabilities = {0.95, 0.94, 0.29, 0.001};
		Event alarm = new Event_Two_Dependencies(alarm_probabilities, burglary, earthquake);
		
		double[] john_probabilities = {0.9, 0.05};
		Event john_calls = new Event_One_Dependency(john_probabilities, alarm);
		
		double[] mary_probabilities = {0.7, 0.01};
		Event mary_calls = new Event_One_Dependency(mary_probabilities, alarm);
		
		num_events = 5;
		
		events = new Event[num_events];
		events[0] = burglary;
		events[1] = earthquake;
		events[2] = alarm;
		events[3] = john_calls;
		events[4] = mary_calls;
		
		event_probabilities = new double[num_events][];
		
		probabilities_to_be_summed = new ArrayList<Double>();
	}

	public static Bayesian_Network generate_instance() {
		instance = new Bayesian_Network();
		
		return instance;
	}
	
	public static Bayesian_Network get_current_network() {
		return instance;
	}
	
	public double compute_probability(Boolean b, Boolean e, Boolean a, Boolean j, Boolean m) {
		Boolean[] event_values = new Boolean[num_events];
		event_values[0] = b;
		event_values[1] = e;
		event_values[2] = a;
		event_values[3] = j;
		event_values[4] = m;
		
		initialize_events(event_values);
		generate_probabilities_to_sum(0, 1);
		double probability = sum_probabilities();
		
		return probability;
	}
	
	public void initialize_events(Boolean[] event_values) {
		for (int i = 0; i < num_events; i++) {
			if (event_values[i] != null) {
				events[i].set_event(true);
				events[i].set_is_true(event_values[i]);
			}
		}
		
	}
	
	public double compute_given_probability(Boolean b, Boolean e, Boolean a, Boolean j, Boolean m) {
		Boolean[] event_values = new Boolean[num_events];
		event_values[0] = b;
		event_values[1] = e;
		event_values[2] = a;
		event_values[3] = j;
		event_values[4] = m;
		
		initialize_given_events(event_values);
		generate_probabilities_to_sum(0, 1);
		double probability = sum_probabilities();
		
		return probability;
	}
	
	public void initialize_given_events(Boolean[] event_values) {
		for (int i = 0; i < num_events; i++) {
			if (event_values[i] != null) {
				events[i].set_is_given(true);
				events[i].set_is_true(event_values[i]);
			}
		}
		
	}
	
	public void get_probabilities() {
		for (int i = 0; i < num_events; i++) {
			Event event = events[i];
			event_probabilities[i] = event.get_probabilities();
		}
	}
	
	public void generate_probabilities_to_sum(int i, double p) {
		int index = i;
		double probability = p;
		
		if (index == num_events - 1) {
			for (double event_probability : event_probabilities[index]) {
				double temp_probability = event_probability * probability;
				probabilities_to_be_summed.add(temp_probability);
			}
		} else {
			for (double event_probability : event_probabilities[index]) {
				double temp_probability = event_probability * probability;
				generate_probabilities_to_sum(index++, temp_probability);
			}
		}
	}
	
	public double sum_probabilities() {
		double probability = 0;
		
		for (double event_probability : probabilities_to_be_summed) {
			probability += event_probability;
		}
		
		return probability;
	}
	
}
