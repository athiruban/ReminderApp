package athi.reminderapp.controller.scheduler;

import java.awt.TrayIcon.MessageType;

import athi.reminderapp.controller.MyApp;
import athi.reminderapp.model.Reminder;

public class NotifyReminder extends Thread {
	private Reminder reminderObj;
	private static String welcomeMessage = "Hi, App Activated";

	@SuppressWarnings("unused")
	private NotifyReminder() {
	}

	public NotifyReminder(Reminder reminderObj) {
		this.reminderObj = reminderObj;
	}

	public void run() {
		MyApp.getInstance()
				.getTrayIcon()
				.displayMessage("Alert", reminderObj.getReminderTitle(),
						MessageType.WARNING);
		System.out.println(welcomeMessage);
	}
}
