
package athi.reminderapp.model;

/*
 * This is my application core bean interface
 */

public interface IEvent extends java.io.Serializable{
	public String getEventDesc();
	public String getEventDate();
	public String getEventTime();
	public String getActivationTime();
}