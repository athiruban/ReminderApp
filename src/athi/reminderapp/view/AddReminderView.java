// This class holds GUI for the reminder application

package athi.reminderapp.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import athi.reminderapp.config.AppConfig;
import athi.reminderapp.model.Reminder;
import athi.reminderapp.model.ReminderList;
import athi.reminderapp.scheduler.EventScheduler;

public class AddReminderView extends JFrame implements ActionListener,
		KeyListener, WindowListener {
	
	private JLabel labelReminderDesc, labelReminderTime, labelReminderDate;
	private Container addViewContainer = getContentPane();
	private JTextField textReminderDesc, textReminderTime, textReminderDate;
	private JButton buttonAddReminder;

	public AddReminderView() {
		setLayout(null);

		this.setSize(310, 250);
		this.setTitle("Add Reminder Panel");
		this.setVisible(true);
		this.setResizable(false);

		labelReminderDesc = new JLabel("About");
		labelReminderTime = new JLabel("Time(hh:mm)");
		labelReminderDate = new JLabel("Date(yyyy-mm-dd)");

		labelReminderDesc.setBounds(20, 10, 100, 20);
		labelReminderTime.setBounds(20, 50, 100, 20);
		labelReminderDate.setBounds(20, 90, 150, 20);

		textReminderDesc = new JTextField(10);
		textReminderTime = new JTextField(8);
		textReminderDate = new JTextField(10);

		textReminderDesc.setBounds(150, 10, 100, 20);
		textReminderTime.setBounds(150, 50, 100, 20);
		textReminderDate.setBounds(150, 90, 100, 20);

		buttonAddReminder = new JButton("Add Reminder");
		buttonAddReminder.setBounds(60, 160, 150, 30);

		buttonAddReminder.addActionListener(this);
		//textReminderTime.addKeyListener(this);
		addWindowListener(this);
		
		addViewContainer.add(labelReminderDesc);
		addViewContainer.add(labelReminderTime);
		addViewContainer.add(labelReminderDate);
		addViewContainer.add(textReminderDesc);
		addViewContainer.add(textReminderTime);
		addViewContainer.add(textReminderDate);
		addViewContainer.add(buttonAddReminder);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Add Reminder")) {
			String reminderDesc = textReminderDesc.getText();
			String reminderDate = textReminderDate.getText();
			String reminderTime = textReminderTime.getText();
			if (reminderDesc.equals("")) {
				JOptionPane.showMessageDialog(this, "Description Required");
				return;
			}
			else if (reminderDate.equals("")){
				JOptionPane.showMessageDialog(this, "Date Required");
				return;
			}
			else if(reminderTime.equals("")){
				JOptionPane.showMessageDialog(this, "Time Required");
				return;
			}
			Reminder newReminderObj = new Reminder();
			newReminderObj.setReminderTitle(reminderDesc);
			newReminderObj.setActivationTime(reminderDate, reminderTime);
			
			EventScheduler eventScheduler = EventScheduler.getInstance();
			eventScheduler.addEvent(newReminderObj);
			
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		String strTime = textReminderTime.getText();
		int strLen = strTime.length();
		if (strLen > 5) {
			textReminderTime.setText(strTime.substring(0, 5));
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// save the current reminder list to the file
		FileOutputStream fos;
		ObjectOutputStream oos;
		ReminderList reminderList = null;
		reminderList = ReminderList.getInstance();
		try {
			fos = new FileOutputStream(AppConfig.APP_DB_FILE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(reminderList); // save the object...
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Save Error");
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}