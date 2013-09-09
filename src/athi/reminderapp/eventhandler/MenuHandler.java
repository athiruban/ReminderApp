package athi.reminderapp.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import athi.reminderapp.view.AddReminderView;
import athi.reminderapp.view.ViewReminders;

public class MenuHandler implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent actionevent) {
		
		if(actionevent.getActionCommand().equals("Exit")){
			//Write all the events to file.
			System.exit(0);
		}
		else if(actionevent.getActionCommand().equals("Add Reminder")){
			// Inovke the gui and access the reminderlist using the singleton object
			AddReminderView addreminderview = new AddReminderView();
		}
		else if(actionevent.getActionCommand().equals("View All Reminders")){
			// Invoke the gui and access the reminderlist using the singleton object
			ViewReminders viewremindersview = new ViewReminders();
		}
	}
	
}