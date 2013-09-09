
package athi.reminderapp.model;

public class Reminder implements IReminder{
	private String reminderDesc;
	private boolean status;
	private String reminderTriggerTime;
	
	public Reminder(){
		this.reminderDesc = "";
	}
	
	@Override
	public String getReminderTitle() {
		return this.reminderDesc;
	}

	public String toString(){
		return "An alert is ready at "+ " with title as " + this.reminderDesc;
	}
	
	public boolean equals(Reminder check){
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
		this.reminderTriggerTime=strDate+" "+strTime;
	}

}