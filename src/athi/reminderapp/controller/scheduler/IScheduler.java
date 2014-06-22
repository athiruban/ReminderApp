package athi.reminderapp.controller.scheduler;

import java.util.List;

import athi.reminderapp.model.Event;

public interface IScheduler {	
	/*
	 * the ideal add function should accept the parameters and it should 
	 * delegate the work to AllEventList class first after successful creation it has
	 * to setup the reminder in case if it is a future event.
	 */
	public void addEvent(String reminderDesc, String reminderDate, String reminderTime);
	
	public List<Event> pullEvents();
	
	/*
	 * How to remove an event does it has any attributes to identify??????????
	 */
	public void removeEvent(Event reminderObj);
}