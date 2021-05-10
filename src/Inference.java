
public class Inference {
	public static Boolean burglary_set;
	public static Boolean earthquake_set;
	public static Boolean alarm_set;
	public static Boolean john_calls_set;
	public static Boolean mary_calls_set;
	
	public static Boolean burglary_given;
	public static Boolean earthquake_given;
	public static Boolean alarm_given;
	public static Boolean john_calls_given;
	public static Boolean mary_calls_given;
	
	public static boolean error;
	

	public static void usage_error() {
		if (error != true) {
			System.out.println("Incorrect usage.");
			System.out.println("Usage is: bayesian_inference.jar [event][t/f]");
			System.out.println("1-5 arguments are possible for the first event.");
			System.out.println("Options:");
			System.out.println("'given' [event][t/f]]");
			System.out.println("1-4 arguments may be entered in the 'given' option");
			System.out.println("Possible events are:");
			System.out.println("B --burglary");
			System.out.println("E --earthquake");
			System.out.println("A --alarm");
			System.out.println("J --John Calls");
			System.out.println("M --Mary Calls");

			error = true;
		}
	}
	
	public static void parse_arguments(String[] arguments) {
		int index = 0;
		int parameter_count = 0;
		int given_count = 0;
		
		for (int i = index; i < arguments.length; i++) {
			String argument = arguments[i];
			
			if (argument == "given") {
				index = i++;
				break;
			}
			
			if (argument.length() != 2) {
				usage_error();
				break;
			}
			
			String formatted_argument = argument.toUpperCase();
			
			switch (formatted_argument.charAt(0)) {
			
			case 'B':
				event_info(formatted_argument, burglary_set);
				break;
				
			case 'E':
				event_info(formatted_argument, earthquake_set);
				break;
				
			case 'A':
				event_info(formatted_argument, alarm_set);
				break;
				
			case 'J':
				event_info(formatted_argument, john_calls_set);
				break;
				
			case 'M':
				event_info(formatted_argument, mary_calls_set);
				break;
				
			default:
				usage_error();
				break;
				
			}
			
			parameter_count++;
			
		}
		
		for (int i = index; i < arguments.length; i++) {
			String argument = arguments[i];
			
			if (argument.length() != 2) {
				usage_error();
				break;
			}
			
			String formatted_argument = argument.toUpperCase();
			
			switch (formatted_argument.charAt(0)) {
			
			case 'B':
				event_info(formatted_argument, burglary_given);
				break;
				
			case 'E':
				event_info(formatted_argument, earthquake_given);
				break;
				
			case 'A':
				event_info(formatted_argument, alarm_given);
				break;
				
			case 'J':
				event_info(formatted_argument, john_calls_given);
				break;
				
			case 'M':
				event_info(formatted_argument, mary_calls_given);
				break;
				
			default:
				usage_error();
				break;
				
			}
			
			given_count++;
			
		}
		
		if (parameter_count + given_count > 5 ||  parameter_count > 5 || given_count > 4) {
			usage_error();
		}
		
	}
	
	public static void event_info(String argument, Boolean event) {
		if (argument.charAt(1) == 'T') {
			event = true;
		} else if (argument.charAt(1) == 'F') {
			event = false;
		} else {
			usage_error();
		}
	}
	
	public static double calculate_event_probability(Bayesian_Network network) {
		double probability = network.compute_probability(burglary_set, earthquake_set, alarm_set, john_calls_set, mary_calls_set);
		double given_probability = network.compute_given_probability(burglary_given, earthquake_given, alarm_given, john_calls_given, mary_calls_given);
		
		double event_probability = probability / given_probability;
		
		return event_probability;
	}
	
	public static void main(String[] args) {
		error = false;
		
		if (args.length > 6 || args.length < 1) {
			usage_error();
			return;
		}
		
		parse_arguments(args);
		
		if(error) {
			return;
		}
		
		Bayesian_Network network = Bayesian_Network.generate_instance();
		double probability = calculate_event_probability(network);
		
		System.out.println("Probability: " + probability);
		
	}

}
