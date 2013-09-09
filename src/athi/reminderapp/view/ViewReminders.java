
package athi.reminderapp.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import athi.reminderapp.eventhandler.ViewRemindersHandler;
import athi.reminderapp.model.Reminder;
import athi.reminderapp.model.ReminderList;

public class ViewReminders extends JFrame{
	Container c = getContentPane();
	
	public ViewReminders(){
		List<Reminder> remlist = ReminderList.getInstance().getReminderList();
		int remcount = 0;
		for(Reminder r: remlist){
			remcount++;
		}
		if(remcount == 0){
			setLayout(null);
			this.setSize(200, 200);
			this.setTitle("View Reminders Panel");
			this.setVisible(true);
			JLabel l1 = new JLabel("No active reminders");
			l1.setBounds(50, 100, 100, 20);
			c.add(l1);
			return;
		}
		setLayout(null);
		this.setSize(500, (remcount*50)+100);
		this.setTitle("View Reminders Panel");
		this.setVisible(true);
		JButton[] titlelist = new JButton[remcount];
		 
		int i=0;
		for(Reminder r: remlist){

				String buttontext = r.getReminderTitle()+" @ "+r.getActivationTime();
				titlelist[i] = new JButton(buttontext);
				titlelist[i].setBounds(50, 50+(i*50), 400, 30);
				titlelist[i].addActionListener(new ViewRemindersHandler());
				c.add(titlelist[i]);
				i++;

		}
	}
}