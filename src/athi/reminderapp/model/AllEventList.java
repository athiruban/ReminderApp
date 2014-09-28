package athi.reminderapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Just to initialize the system
// Using singleton pattern create a model class for the list of reminders.

public class AllEventList extends EventList implements Serializable {
	/**
	 * This application can access to almost only one AllEventList object and
	 * this object can access only the static List<Event> container.
	 */
	private static final long serialVersionUID = -1353363242484017538L;
	private static AllEventList allEventlist;
	private List<Event> eventlist;

	{
		eventlist = new ArrayList<Event>();
	}
	
	static{
		allEventlist = null;
	}
	
	private AllEventList() {
	}

	public static AllEventList getInstance() {
		if (allEventlist == null) {
			allEventlist = new AllEventList();
		}
		return allEventlist;
	}

	public List<Event> getEventList() {
		return eventlist;
	}

	public void setEventList(ArrayList<Event> remlist) {
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
		try{
			eventlist.add(newEventObj);
		}
		catch (NullPointerException e){
			System.out.println(e.toString());
		}
		return newEventObj;
	}
}