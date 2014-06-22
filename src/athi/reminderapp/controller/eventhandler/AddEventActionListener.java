package athi.reminderapp.controller.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import athi.reminderapp.controller.scheduler.EventScheduler;
import athi.reminderapp.helper.AppHelper;
import athi.reminderapp.model.Event;
import athi.reminderapp.view.AddReminderView;

public class AddEventActionListener implements ActionListener {
	private AddReminderView addReminderViewObj;
	
	@SuppressWarnings("unused")
	private AddEventActionListener() {
	}

	public AddEventActionListener(AddReminderView addReminderViewObj) {
		this.addReminderViewObj = addReminderViewObj;
	}

	private boolean validateUserInput(String reminderDesc, String reminderDate,
			                       String reminderTime){
		if (null != reminderDesc && reminderDesc.equals("")) {
			JOptionPane.showMessageDialog(addReminderViewObj,
					"Description Required");
			return false;
		} 
		
		if (null != reminderTime && reminderTime.equals("")) {
			JOptionPane.showMessageDialog(addReminderViewObj,
					"Time Required");
			return false;
		}  else if(false == AppHelper.isValidTime(reminderTime)){
			JOptionPane.showMessageDialog(addReminderViewObj,
					"Time is not in proper format!");
			return false;
		}
		
		if (null != reminderDate && reminderDate.equals("")) {
			JOptionPane.showMessageDialog(addReminderViewObj,
					"Date Required");
			return false;
		} else if(false == AppHelper.isValidDate(reminderDate)){
			JOptionPane.showMessageDialog(addReminderViewObj,
					"Date is not in proper format!");
			return false;
		}
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Changed this controller method to just pass the arguments so that reminder object will be
	 * created in the EventScheduler class.
	 * In future even if we add/remove event attributes it will be easy to make change only in the 
	 * event scheduler class
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Add Event")) {
			String reminderDesc = addReminderViewObj.getTextReminderDesc()
					.getText();
			String reminderDate = addReminderViewObj.getTextReminderDate()
					.getText();
			String reminderTime = addReminderViewObj.getTextReminderTime()
					.getText();

			if(false==validateUserInput(reminderDesc, reminderDate, reminderTime))
				return;
			
			synchronized(this){
				EventScheduler eventScheduler = EventScheduler.getInstance();
				eventScheduler.addEvent(reminderDesc, reminderDate, reminderTime);
			}
		}
	}
}
