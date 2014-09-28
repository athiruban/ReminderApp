package athi.reminderapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class EventList implements Serializable{
	public abstract void setEventList(ArrayList<Event> remlist);
	public abstract List<Event> getEventList();
	public abstract Event addNewEvent(String desc, String eventdate, String eventtime);
}
