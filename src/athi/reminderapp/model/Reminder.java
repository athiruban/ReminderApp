package athi.reminderapp.model;

public class Reminder implements IReminder{
	/**
	 * This is the core of the application data
	 * 
	 * Every reminder object should hold an unique key to identify it easily without needing
	 * to search the entire list....
	 * Who will use the unique key?
	 * - Removing reminder methods
	 * Frequency?
	 * - Less
	 * 
	 * Can reminder be grouped on something else?
	 * Yes using time-frame we can group events easily.
	 * Who will use the time based retrieval?
	 * - Display methods
	 * Frequency?
	 * - Moderate
	 * 
	 * How about when we move reminder data to SQLite database?
	 * It is obvious that reminder will be searched based on the time, location and other factors
	 * so it should have a mechanism to persist those information by itself.
	 *  
	 */
	private static final long serialVersionUID = 538551431272156716L;
	private String reminderDesc;
	private String reminderTriggerTime;

	public Reminder() {
		this.reminderDesc = "";
	}

	@Override
	public String getReminderTitle() {
		return this.reminderDesc;
	}

	public String toString() {
		return "An alert is ready at " + " with title as " + this.reminderDesc;
	}

	public boolean equals(Reminder check) {
		return false;
	}

	@Override
	public void setReminderTitle(String title) {
		this.reminderDesc = title;
	}

	@Override
	public String getActivationTime() {
		return this.reminderTriggerTime;
	}

	@Override
	public void setActivationTime(String strDate, String strTime) {
		this.reminderTriggerTime = strDate + " " + strTime;
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
}