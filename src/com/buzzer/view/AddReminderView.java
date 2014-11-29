package com.buzzer.view;

import java.awt.Container;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.buzzer.controller.eventhandler.AddEventActionListener;
import com.buzzer.controller.eventhandler.AddEventKeyListener;
import com.buzzer.controller.eventhandler.AddEventWindowListener;

public class AddReminderView extends JFrame implements AddEventOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5055538919263830339L;
	private JLabel labelReminderDesc, labelReminderTime, labelReminderDate,
			labelReminderFreq;
	private Container addViewContainer = getContentPane();
	private JTextField textReminderDesc, textReminderTime, textReminderDate;
	private JButton buttonAddReminder;
	private JRadioButton radioReminderFreqO, radioReminderFreqR;
	private ButtonGroup freqButtonGroup;
	private final int WIN_WIDTH = 550, WIN_HEIGHT = 300;

	public int getWIN_WIDTH() {
		return WIN_WIDTH;
	}

	public int getWIN_HEIGHT() {
		return WIN_HEIGHT;
	}

	private void createLabels() {
		labelReminderDesc = new JLabel("What's up to remind!");
		labelReminderTime = new JLabel("Time (hh:mm:ss)");
		labelReminderDate = new JLabel("Date (yyyy-mm-dd)");
		labelReminderFreq = new JLabel("Frequency");

		labelReminderDesc.setBounds(60, 60, 150, 20);
		labelReminderTime.setBounds(60, 100, 100, 20);
		labelReminderDate.setBounds(60, 140, 150, 20);
		labelReminderFreq.setBounds(60, 180, 100, 20);
	}

	private void createInputFields() {
		textReminderDesc = new JTextField(20);
		textReminderTime = new JTextField(20);
		textReminderDate = new JTextField(20);
		radioReminderFreqO = new JRadioButton("Once", true);
		radioReminderFreqR = new JRadioButton("Repeat");
		freqButtonGroup = new ButtonGroup();

		freqButtonGroup.add(radioReminderFreqO);
		freqButtonGroup.add(radioReminderFreqR);

		textReminderDesc.setBounds(240, 60, 200, 30);
		textReminderTime.setBounds(240, 100, 100, 20);
		textReminderDate.setBounds(240, 140, 100, 20);
		radioReminderFreqO.setBounds(240, 180, 70, 20);
		radioReminderFreqR.setBounds(320, 180, 100, 20);

		textReminderDesc.setFont(new Font("Calibri", Font.PLAIN, 14));
	}

	private void addUIToContainer() {
		addViewContainer.add(labelReminderDesc);
		addViewContainer.add(labelReminderTime);
		addViewContainer.add(labelReminderDate);
		addViewContainer.add(textReminderDesc);
		addViewContainer.add(textReminderTime);
		addViewContainer.add(textReminderDate);
		addViewContainer.add(buttonAddReminder);
		addViewContainer.add(labelReminderFreq);
		addViewContainer.add(radioReminderFreqO);
		addViewContainer.add(radioReminderFreqR);
	}

	public AddReminderView() {
		setLayout(null);

		this.setSize(WIN_WIDTH, WIN_HEIGHT);
		this.setTitle("Add Event Panel");
		this.setVisible(true);
		this.setResizable(true);

		createLabels();

		createInputFields();
		// Add default text

		buttonAddReminder = new JButton("Add Event");
		buttonAddReminder.setBounds(100, 210, 150, 30);

		buttonAddReminder.addActionListener(new AddEventActionListener(this));
		textReminderTime.addKeyListener(new AddEventKeyListener(this));
		addWindowListener(new AddEventWindowListener());

		addUIToContainer();
	}

	public String getReminderDesc() {
		return textReminderDesc.getText();
	}

	public String getReminderTime() {
		return textReminderTime.getText();
	}

	public String getReminderDate() {
		return textReminderDate.getText();
	}

	public void setReminderTime(String str) {
		this.textReminderTime.setText(str);
	}

	public void setReminderDesc(String str) {
		this.textReminderDesc.setText(str);
	}

	public void setReminderDate(String str) {
		this.textReminderDate.setText(str);
	}
}