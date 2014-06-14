package athi.reminderapp.controller;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;

import athi.reminderapp.view.AppTray;

public class MyApp {
	private static MyApp myApp = null;
	private AppTray appTray = null;
	
	public static void main(String args[]) throws IOException {
		// Get the system tray
		if (!SystemTray.isSupported()) {
			System.out.println("\n\tError: System Tray is not supported!");
			return;
		}
		MyApp.getInstance().initTray();
	}

	public static MyApp getInstance() {
		if (null == myApp) {
			myApp = new MyApp();
		}
		return myApp;
	}

	private void initTray() throws IOException {
		appTray = new AppTray();
	}
	
	public TrayIcon getTrayIcon(){
		/*
		 * Delegate the work to appTray
		 */
		return appTray.getTrayIcon();
	}
}
