package athi.reminderapp.eventhandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import athi.reminderapp.model.Reminder;
import athi.reminderapp.model.ReminderList;

public class ViewRemindersHandler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent ae) {
		List<Reminder> remlist = ReminderList.getInstance().getReminderList();
		for(Reminder r: remlist){
			String buttontext = r.getReminderTitle()+" @ "+r.getActivationTime();
//			if(buttontext.equals(ae.getActionCommand())){
//				//delete the reminder from the active list
//				r.setStatus(false);
//				break;
//			}
		}
	}
}