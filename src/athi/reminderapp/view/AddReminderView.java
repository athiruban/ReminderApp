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
import athi.reminderapp.eventhandler.AddReminderActionListener;
import athi.reminderapp.eventhandler.AddReminderKeyListener;
import athi.reminderapp.eventhandler.AddReminderWindowListener;
import athi.reminderapp.model.Reminder;
import athi.reminderapp.model.ReminderList;
import athi.reminderapp.scheduler.EventScheduler;

public class AddReminderView extends JFrame  {
	
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
		labelReminderTime = new JLabel("Time(hh:mm:ss)");
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

		buttonAddReminder.addActionListener(new AddReminderActionListener(this));
		textReminderTime.addKeyListener(new AddReminderKeyListener(this));
		addWindowListener(new AddReminderWindowListener());
		
		addViewContainer.add(labelReminderDesc);
		addViewContainer.add(labelReminderTime);
		addViewContainer.add(labelReminderDate);
		addViewContainer.add(textReminderDesc);
		addViewContainer.add(textReminderTime);
		addViewContainer.add(textReminderDate);
		addViewContainer.add(buttonAddReminder);
	}
	
	public JTextField getTextReminderDesc(){
		return textReminderDesc;
	}

	public JTextField getTextReminderTime(){
		return textReminderTime;
	}

	public JTextField getTextReminderDate(){
		return textReminderDate;
	}
}