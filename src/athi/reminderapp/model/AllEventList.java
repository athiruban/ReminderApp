package athi.reminderapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Just to initialize the system
// Using singleton pattern create a model class for the list of reminders.

public class AllEventList implements EventList, Serializable {
	/**
	 * This application can access to almost only one AllEventList object and
	 * this object can access only the static List<Event> container.
	 */
	private static final long serialVersionUID = -1353363242484017538L;
	private static AllEventList allEventlist;
	private static List<Event> eventlist;

	private AllEventList() {
	}

	public static AllEventList getInstance() {
		if (allEventlist == null) {
			allEventlist = new AllEventList();
			// Create an empty array list object
			eventlist = new ArrayList<Event>();
		}
		return allEventlist;
	}

	public List<Event> getEventList() {
		return eventlist;
	}

	public void setEventList(List<Event> remlist) {
		eventlist = remlist;
	}
	
	public void setAllEventList(AllEventList remlist) {
		eventlist = remlist.getEventList();
	}

	/*
	 * This method should have the right to create new Event object.
	 * Every time when we want to create new event we have to call this method.
	 * @see athi.reminderapp.model.EventList#addNewEvent(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Event addNewEvent(String eventDesc, String eventDate, String eventTime) {
		Event newEventObj = new Event();
		
		newEventObj.setEventDesc(eventDesc);
		newEventObj.setEventDate(eventDate);
		newEventObj.setEventTime(eventTime);
		
		return newEventObj;
	}
}