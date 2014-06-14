package athi.reminderapp.model;

public class Reminder implements IReminder{
	/**
	 * This is the core of the application data
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