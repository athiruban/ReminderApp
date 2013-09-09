
package athi.reminderapp.model;

public interface IReminder{
	public String getReminderTitle();
	public String getActivationTime();
	public void setReminderTitle(String title);
	public void setActivationTime(String strDate, String strTime);
}