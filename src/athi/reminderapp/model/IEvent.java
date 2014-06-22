
package athi.reminderapp.model;

/*
 * This is my application core bean interface
 */

public interface IEvent extends java.io.Serializable{
	public void setEventDesc(String title);
	public void setEventDate(String strDate);
	public void setEventTime(String strTime);
	public String getReminderTitle();
	public String getActivationTime();
}