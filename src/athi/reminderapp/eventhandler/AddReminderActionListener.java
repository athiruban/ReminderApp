package athi.reminderapp.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import athi.reminderapp.model.Reminder;
import athi.reminderapp.scheduler.EventScheduler;
import athi.reminderapp.view.AddReminderView;

public class AddReminderActionListener implements ActionListener{
	private AddReminderView addReminderViewObj;
	
	@SuppressWarnings("unused")
	private AddReminderActionListener(){
	}
	public AddReminderActionListener(AddReminderView addReminderViewObj){
		this.addReminderViewObj = addReminderViewObj;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Add Reminder")) {
			String reminderDesc = addReminderViewObj.getTextReminderDesc().getText();
			String reminderDate = addReminderViewObj.getTextReminderDate().getText();
			String reminderTime = addReminderViewObj.getTextReminderTime().getText();
			
			if (reminderDesc.equals("")) {
				JOptionPane.showMessageDialog(addReminderViewObj, "Description Required");
				return;
			}
			else if (reminderDate.equals("")){
				JOptionPane.showMessageDialog(addReminderViewObj, "Date Required");
				return;
			}
			else if(reminderTime.equals("")){
				JOptionPane.showMessageDialog(addReminderViewObj, "Time Required");
				return;
			}
			
			Reminder newReminderObj = new Reminder();
			newReminderObj.setReminderTitle(reminderDesc);
			newReminderObj.setActivationTime(reminderDate, reminderTime);
			
			EventScheduler eventScheduler = EventScheduler.getInstance();
			eventScheduler.addEvent(newReminderObj);
		}
	}
}
