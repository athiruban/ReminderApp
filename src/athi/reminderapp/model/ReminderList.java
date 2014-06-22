package athi.reminderapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Just to initialize the system
// Using singleton pattern create a model class for the list of reminders.

public class ReminderList implements Serializable {
	/**
	 * This application can access to almost only one ReminderList object and
	 * this object can access only the static List<Reminder> container.
	 */
	private static final long serialVersionUID = -1353363242484017538L;
	private static ReminderList remlist;
	private static List<Reminder> reminderlist;

	private ReminderList() {
	}

	public static ReminderList getInstance() {
		if (remlist == null) {
			remlist = new ReminderList();
			// Create an empty array list object
			reminderlist = new ArrayList<Reminder>();
		}
		return remlist;
	}

	public List<Reminder> getReminderList() {
		return reminderlist;
	}

	public void setReminderList(List<Reminder> remlist) {
		reminderlist = remlist;
	}
	
	public void setReminderList(ReminderList remlist) {
		reminderlist = remlist.getReminderList();
	}
}