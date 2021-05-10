
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
	

	public static void usage_error() {
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
	}
	
	public static void parse_arguments(String[] arguments) {
		int index = 0;
		
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
