package com.buzzer.view;

import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.buzzer.controller.scheduler.EventScheduler;
import com.buzzer.model.AllEventList;
import com.buzzer.model.Event;

/* In the new version of the application, support for table will be added.
 * The JTable class provides a good look for the old expired and future reminders
 * This class will be modified to accommodate the new changes.
 */

public class ViewReminders extends JFrame {
	private static final long serialVersionUID = 2668528072271027208L;
	Container viewContainer;
	private List<Event> remlist;
	private EventScheduler eventScheduler;
	private int remcount;

	private void init() {
		eventScheduler = EventScheduler.getInstance();
		/*
		 * When we call the method for the first time we will have this
		 * eventScheduler list empty. It is important to get the archived
		 * reminders plus the current (not yet saved) reminders also.
		 */
		if (null == eventScheduler) {
			System.out.println("No Event Scheduler Object available!");
			return;
		}
		remlist = eventScheduler.pullEvents();
		/*
		 * Now get from the file
		 */
		System.out.println("Event list size from eventScheduler (live): "
				+ remlist.size());
		remlist.addAll(AllEventList.getInstance().getEventList());
		System.out
				.println("Event list size from eventScheduler (live) + archived ones: "
						+ remlist.size());
		remcount = remlist.size();
	}

	public void populateEmptyView() {
		this.setSize(200, 200);
		this.setTitle("View Reminders Panel:No active reminders!");
		this.setVisible(true);
		/*
		 * Empty View
		 */
		JLabel l1 = new JLabel("No active reminders");
		l1.setBounds(50, 100, 100, 20);
		viewContainer.add(l1);
	}

	public void populateNormalView() {
		/*
		 * Based on the reminder size the height of the window will be
		 * determined
		 */
		this.setSize(500, (remcount * 50) + 100);
		this.setTitle("(:View Reminders Panel:)");
		this.setVisible(true);
		/*
		 * Normal view
		 */
		JButton[] titlelist = new JButton[remcount];
		System.out.println(remcount);

		int i = 0;
		for (; i < remcount; i++) {
			String buttontext = remlist.get(i).getEventDesc() + " On "
					+ remlist.get(i).getEventDate() + " @  "
					+ remlist.get(i).getEventTime();
			titlelist[i] = new JButton(buttontext);
			titlelist[i].setBounds(50, 50 + (i * 50), 400, 30);
			viewContainer.add(titlelist[i]);
			i++;
		}
	}

	public ViewReminders() {
		viewContainer = this.getContentPane();
		setLayout(null);
		remcount = 0;

		init();

		if (remcount == 0)
			populateEmptyView();
		else
			populateNormalView();
	}
}