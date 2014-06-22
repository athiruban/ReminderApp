package athi.reminderapp.controller.scheduler;

import java.awt.TrayIcon.MessageType;

import athi.reminderapp.controller.MyApp;
import athi.reminderapp.model.Event;

public class NotifyReminder extends Thread {
	private Event reminderObj;
	private static String welcomeMessage = "Hi, App Activated";

	@SuppressWarnings("unused")
	private NotifyReminder() {
	}

	public NotifyReminder(Event reminderObj) {
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
