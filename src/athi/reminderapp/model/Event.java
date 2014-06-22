package athi.reminderapp.model;

public class Event implements IEvent{
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
	private String eventDesc;
	private String eventDate;
	private String eventTime;
	
	public Event() {
		this.eventDesc = "";
	}

	@Override
	public String getReminderTitle() {
		return this.eventDesc;
	}

	@Override
	public void setEventDesc(String title) {
		this.eventDesc = title;
	}

	@Override
	public String getActivationTime() {
		return (this.eventDate + " " + this.eventTime);
	}
	
	@Override
	public void setEventDate(String strDate) {
		this.eventDate = strDate;
	}

	@Override
	public void setEventTime(String strTime) {
		this.eventTime = strTime;
	}
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}

	public String toString() {
		return "An alert is ready at " + " with title as " + this.eventDesc;
	}

	public boolean equals(Event check) {
		return false;
	}

}