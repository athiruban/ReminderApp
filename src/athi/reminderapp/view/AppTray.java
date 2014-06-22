package athi.reminderapp.view;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import athi.reminderapp.config.AppConfig;
import athi.reminderapp.controller.eventhandler.MenuHandler;

public class AppTray {
	private SystemTray tray     = null;
	private TrayIcon trayIcon   = null;
	private PopupMenu popupmenu = null;
	
	private void init(){
		tray = SystemTray.getSystemTray();
		/*
		 * Display Pop-up menu in the system tray
		 */
		popupmenu = new PopupMenu();
		loadTrayIcon();
	}
	
	private void loadTrayIcon(){
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(AppConfig.APP_NOTIFY_ICON_FILE));
		}
		catch(IOException e){
			System.out.println("Error in reading Icon File! System abnormal end!");
			System.exit(1);
		}
		
		trayIcon = new TrayIcon(img);
		if (trayIcon == null) {
			System.out.println("Error in creating TrayIcon object!");
			System.exit(1);
		}
		loadEventHandlers();
	}
	
	private void loadEventHandlers(){
		/*
		 * The menu items in the system tray are initialized,
		 * the corresponding handlers are also created.
		 */
		MenuHandler eventhander = new MenuHandler();

		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(eventhander);

		MenuItem addReminderItem = new MenuItem("Add Reminder");
		addReminderItem.addActionListener(eventhander);
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
			} 
			catch (InterruptedException e) {
				System.out.println("Thread running error");
				e.printStackTrace();
			}
			trayIcon.displayMessage("Custom message",
					"Waiting for your request!", MessageType.INFO);
		} 
		catch (AWTException e) {
			e.printStackTrace();
		}
		loadStoredReminders();
	}

	private void loadStoredReminders(){
		/*
		 * If this is the first time initialize an empty list otherwise load the reminder object.
		 * This method delegates the functionality to IOManager
		 */
	}
	
	public AppTray() throws IOException{
		/*
		 * If SystemTray is not supported, then the application will not be initialized! 		
		 */
		init();		
	}
	
	public TrayIcon getTrayIcon() {
		return trayIcon;
	}
}