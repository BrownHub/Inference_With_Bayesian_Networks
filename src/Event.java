public abstract class Event {
	protected Boolean is_true;
	protected boolean is_set;
	public char event_name;
	
	public Event() {
		is_set = false;
	}
	
	public abstract void set_dependencies(char name, boolean true_false);
	
	public abstract void reset_dependencies();
	
	public void set_is_true(boolean true_false) {
		this.is_true = true_false;
	}
	
	public void set_event(boolean is_set) {
		this.is_set = is_set;
	}
	
	public boolean get_is_true() {
		return is_true;
	}
	
	public boolean get_is_set() {
		return is_set;
	}
	
	public char get_name() {
		return event_name;
	}
	
	public abstract double[] get_probabilities();
	
	
}
