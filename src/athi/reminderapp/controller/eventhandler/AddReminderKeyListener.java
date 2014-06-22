package athi.reminderapp.controller.eventhandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import athi.reminderapp.view.AddReminderView;

public class AddReminderKeyListener extends KeyAdapter {
	private AddReminderView addReminderViewObj;

	@SuppressWarnings("unused")
	private AddReminderKeyListener() {
		// Won't allow
	}

	public AddReminderKeyListener(AddReminderView addReminderViewObj) {
		this.addReminderViewObj = addReminderViewObj;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String strTime = addReminderViewObj.getTextReminderTime().getText();
		int strLen = strTime.length();
		if (strLen > 8) {
			addReminderViewObj.getTextReminderTime().setText(
					strTime.substring(0, 8));
		}
	}
}