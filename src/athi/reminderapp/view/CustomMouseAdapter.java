package athi.reminderapp.view;

import java.awt.Component;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomMouseAdapter extends MouseAdapter {
	private TrayIcon trayIcon;

	private CustomMouseAdapter() {

	}

	public CustomMouseAdapter(TrayIcon ti) {
		this.trayIcon = ti;
	}

	public void mousePressed(MouseEvent e) {
		// trayIcon.getPopupMenu().show(e.getSource(), 1, 1);
	}

	// public void mouseReleased(MouseEvent e){
	// trayIcon.getPopupMenu().show(e.getComponent(), 0, 0);
	// }
}
