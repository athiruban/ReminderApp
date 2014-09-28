package athi.reminderapp.controller.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import athi.reminderapp.controller.IOManager;
import athi.reminderapp.view.AddReminderView;
import athi.reminderapp.view.ViewReminders;

public class MenuHandler implements ActionListener{
		
	@Override
	public void actionPerformed(ActionEvent actionevent) {
		if(actionevent.getActionCommand().equals("Exit")){
			synchronized(this){
				new IOManager().saveReminders();
			}
			System.exit(0);
		}
		else if(actionevent.getActionCommand().equals("Add Event")){
			AddReminderView addreminderview = new AddReminderView();
		}
		else if(actionevent.getActionCommand().equals("View All Reminders")){
			new IOManager().loadReminders();
			ViewReminders viewremindersview = new ViewReminders();
		}
	}
}