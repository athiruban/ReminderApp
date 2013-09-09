package athi.reminderapp.view;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import athi.reminderapp.config.AppConfig;
import athi.reminderapp.eventhandler.MenuHandler;
import athi.reminderapp.model.Reminder;
import athi.reminderapp.model.ReminderList;

public class TrayMain {
	static SystemTray tray = null;
	static TrayIcon trayIcon = null;
	static PopupMenu popupmenu = null;

	public static void main(String args[]) throws IOException {
		// Get the system tray

		if (!SystemTray.isSupported()) {
			System.out.println("\n\tError: System Tray is not supported!");
			return;
		}

		initTray(); // default initialization
		TrayMain trayMain = new TrayMain();
		//trayMain.initReminders();
	}

	@SuppressWarnings("unchecked")
	private void initReminders() throws IOException {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		ReminderList reminderList;
		try {
			fis = new FileInputStream(AppConfig.APP_DB_FILE);
			ois = new ObjectInputStream(fis);

			Object listobject = ois.readObject();

			if (listobject instanceof List<?>) {
				reminderList = (ReminderList) listobject;
				ReminderList.getInstance().setReminderList(
						reminderList.getReminderList());
				System.out.println("Reminders from file is loaded ok");
				ois.close();
				fis.close();
			} else {
				System.out.println("Serious error : Unidentified object "
						+ "present in the storage! Shutting down application");
				System.exit(1);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No previous load found "
					+ "so creating an empty reminder list object");
			List newReminderList = new LinkedList<Reminder>();
			// assign the empty list to reminderList object
			ReminderList.getInstance().setReminderList(newReminderList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Serious Error Shutting down application: "
					+ "File exist but there is no object inside");
			System.exit(1);
		}
		// Connect with the global singleton ReminderList object

	}

	private static void initTray() throws IOException {

		tray = SystemTray.getSystemTray();

		BufferedImage img = null;
		img = ImageIO.read(new File(AppConfig.APP_NOTIFY_ICON_FILE));

		trayIcon = new TrayIcon(img);
		if (trayIcon == null) {
			System.out.println("Error in creating TrayIcon object!");
			System.exit(1);
		}

		// Add a popup menu to trayicon
		popupmenu = new PopupMenu();

		// Menu handler
		MenuHandler eventhander = new MenuHandler();

		// Exit application menu item
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(eventhander);

		// Add New Reminder menu item
		MenuItem addReminderItem = new MenuItem("Add Reminder");
		addReminderItem.addActionListener(eventhander);

		// View all reminder menu item
		MenuItem viewAllMenuItem = new MenuItem("View All Reminders");
		viewAllMenuItem.addActionListener(eventhander);

		popupmenu.add(addReminderItem);
		popupmenu.add(viewAllMenuItem);
		popupmenu.add(exitItem);

		trayIcon.setPopupMenu(popupmenu);
		trayIcon.setToolTip("My Reminder Application");
		trayIcon.addMouseListener(new CustomMouseAdapter(trayIcon));

		try {
			tray.add(trayIcon);
			trayIcon.displayMessage("Notification", "Application Started",
					MessageType.INFO);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Thread running error");
				e.printStackTrace();
			}
			trayIcon.displayMessage("Custom message",
					"Waiting for your request!", MessageType.INFO);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static TrayIcon getTrayIcon() {
		return trayIcon;
	}

}
