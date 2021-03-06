package com.buzzer.controller.scheduler;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.buzzer.model.AllEventList;
import com.buzzer.model.Event;
import com.buzzer.model.GenericEvent;

public class EventScheduler implements IScheduler, Cloneable {
	private static EventScheduler eventScheduler;
	private final ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);
	private List<ScheduledFuture<Event>> beeperHandleList = new ArrayList<ScheduledFuture<Event>>();
	private boolean isRunning = false;

	private EventScheduler() {
		// Not allowed
	}

	public static EventScheduler getInstance() {
		if (null == eventScheduler) {
			eventScheduler = new EventScheduler();
		}
		return eventScheduler;
	}

	public void startScheduler() {
		if (true == isRunning) {
			System.out.println("Scheduler is running");
		} else {
			Date currdate = new Date();
			Timestamp currentTS = new Timestamp(currdate.getTime());
			// scheduler.schedule(workerthread2, 20, TimeUnit.SECONDS);
		}
	}

	public boolean stopScheduler() {
		// kill the workerThread
		return false;
	}

	public boolean isRunning() {
		if (true == isRunning)
			return true;
		else
			return false;
	}

	@Override
	public void removeEvent(Event reminderObj) {
	}

	private long getTriggerSeconds(GenericEvent reminderObj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date currDate = new Date();
		Date reminderDate = null;
		try {
			reminderDate = sdf.parse(reminderObj.getEventDate() + " "
					+ reminderObj.getEventTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diffdate = reminderDate.getTime() - currDate.getTime();
		if (diffdate > 0) {
			long diffsecs = diffdate / (1000);
			return diffsecs;
		} else
			return 0;
	}

	public List<Event> pullEvents() {
		ScheduledFuture<Event> schedRemVar;
		List<Event> currRemList = new ArrayList<Event>();

		for (int i = 0; i < beeperHandleList.size(); i++) {
			schedRemVar = beeperHandleList.get(i);
			try {
				Event rTemp;
				rTemp = (Event) schedRemVar.get().clone();
				currRemList.add(rTemp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return currRemList;
	}

	public void addEvent(String reminderDesc, String reminderDate,
			String reminderTime) {
		Event newReminderObj = AllEventList.getInstance().addNewEvent(
				reminderDesc, reminderDate, reminderTime);
		/*
		 * change this functionality to schedule an event only if it exists in
		 * the future.
		 */
		scheduleEvent(newReminderObj);
	}

	private void scheduleEvent(GenericEvent reminderObj) {
		long triggerSecs = getTriggerSeconds(reminderObj);

		if (triggerSecs > 0) {
			NotifyReminder notifyReminderThread = new NotifyReminder(
					reminderObj);
			scheduleEvent0(notifyReminderThread, triggerSecs);
		}
	}

	private void scheduleEvent0(NotifyReminder notifyReminderThread,
			long triggerSecs) {
		ScheduledFuture<Event> scheduledFuture = (ScheduledFuture<Event>) scheduler
				.schedule(notifyReminderThread, triggerSecs, TimeUnit.SECONDS);
		beeperHandleList.add(scheduledFuture);
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
