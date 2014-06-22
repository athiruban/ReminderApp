package athi.reminderapp.model;

import java.util.List;

public abstract class EventList {
	public abstract void setEventList(List<Event> remlist);
	public abstract List<Event> getEventList();
	public abstract Event addNewEvent(String desc, String eventdate, String eventtime);
}
