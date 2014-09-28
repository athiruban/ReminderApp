package athi.reminderapp.controller.eventhandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import athi.reminderapp.view.AddReminderView;

public class AddEventKeyListener extends KeyAdapter {
	private AddReminderView addReminderViewObj;

	@SuppressWarnings("unused")
	private AddEventKeyListener() {
		// Won't allow
	}

	public AddEventKeyListener(AddReminderView addReminderViewObj) {
		this.addReminderViewObj = addReminderViewObj;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String strTime = addReminderViewObj.getReminderTime();
		int strLen = strTime.length();
		if (strLen > 8) {
			addReminderViewObj.setReminderTime(
					strTime.substring(0, 8));
		}
	}
}