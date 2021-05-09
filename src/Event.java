import java.util.Map;

public abstract class Event {
	protected boolean is_true;
	protected boolean is_set;
	protected boolean is_given;
	
	public Event() {
		is_set = false;
		is_given = false;
	}
	
	public void set_is_true(boolean true_false) {
		this.is_true = true_false;
	}
	
	public void set_event(boolean is_set) {
		this.is_set = is_set;
	}
	
	public void set_is_given(boolean is_given) {
		this.is_given = is_given;
	}
	
	public boolean get_is_true() {
		return is_true;
	}
	
	public boolean get_is_set() {
		return is_set;
	}
	
	public boolean get_is_given() {
		return is_given;
	}
	
	public abstract double[] get_probabilities();
	
	
}
