package com.buzzer.controller;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;

import javax.swing.SwingUtilities;

import com.buzzer.view.AppTray;

public class MyApp {
	private static MyApp myApp = null;
	private AppTray appTray = null;

	public static void main(String args[]) {
		InstanceChecker instanceChecker = new InstanceChecker();
		instanceChecker.start();

		/* If system tray is not supported then stop the execution */
		if (!SystemTray.isSupported()) {
			System.out.println("\n\tError: System Tray is not supported!");
			return;
		}

		/* start the application thread in Event Dispatch Thread */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MyApp.getInstance().initTray();
				} catch (IOException e) {
					System.out.println("IO Exception occurred!");
					e.printStackTrace();
				}
			}
		});
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

	public TrayIcon getTrayIcon() {
		/*
		 * Delegate the work to appTray
		 */
		return appTray.getTrayIcon();
	}
}
