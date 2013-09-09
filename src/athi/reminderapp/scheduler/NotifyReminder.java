package athi.reminderapp.scheduler;

import java.awt.TrayIcon.MessageType;

import athi.reminderapp.model.Reminder;
import athi.reminderapp.view.TrayMain;

public class NotifyReminder extends Thread{
	private Reminder reminderObj;
	private NotifyReminder(){
	}
	public NotifyReminder(Reminder reminderObj){
		this.reminderObj = reminderObj;
	}
	public void run(){
		TrayMain.getTrayIcon().displayMessage("Alert", reminderObj.getReminderTitle(),
				MessageType.WARNING);
		System.out.println("Hi Reminder Activated");
	}
}
