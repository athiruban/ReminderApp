package athi.reminderapp.model;

import java.util.List;

public interface EventList {
	public void setEventList(List<Event> remlist);
	public List<Event> getEventList();
	public Event addNewEvent(String desc, String eventdate, String eventtime);
}
