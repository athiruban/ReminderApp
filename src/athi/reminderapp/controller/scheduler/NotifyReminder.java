package athi.reminderapp.controller.scheduler;

import java.awt.TrayIcon.MessageType;

import athi.reminderapp.controller.MyApp;
import athi.reminderapp.model.Event;
import athi.reminderapp.model.GenericEvent;

public class NotifyReminder extends Thread {
	private GenericEvent eventObj;
	private static String welcomeMessage = "Hi, App Activated";

	@SuppressWarnings("unused")
	private NotifyReminder() {
	}

	public NotifyReminder(GenericEvent reminderObj) {
		this.eventObj = reminderObj;
	}

	public void run() {
		MyApp.getInstance()
				.getTrayIcon()
				.displayMessage("Alert", eventObj.getEventDesc(),
						MessageType.WARNING);
		System.out.println(welcomeMessage);
	}
}
