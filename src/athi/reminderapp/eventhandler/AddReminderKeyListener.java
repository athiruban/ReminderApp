package athi.reminderapp.eventhandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import athi.reminderapp.view.AddReminderView;

public class AddReminderKeyListener implements KeyListener{
	private AddReminderView addReminderViewObj;
	
	@SuppressWarnings("unused")
	private AddReminderKeyListener(){
		
	}
	
	public AddReminderKeyListener(AddReminderView addReminderViewObj){
		this.addReminderViewObj = addReminderViewObj;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String strTime = addReminderViewObj.getTextReminderTime().getText();
		int strLen = strTime.length();
		if (strLen > 8) {
			addReminderViewObj.getTextReminderTime().setText(strTime.substring(0, 8));
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}