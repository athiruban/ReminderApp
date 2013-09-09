package athi.reminderapp.eventhandler;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import athi.reminderapp.config.AppConfig;
import athi.reminderapp.model.ReminderList;

public class AddReminderWindowListener implements WindowListener{
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