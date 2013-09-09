package athi.reminderapp.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.TrayIcon.MessageType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import athi.reminderapp.model.Reminder;
import athi.reminderapp.model.ReminderList;
import athi.reminderapp.view.TrayMain;

interface IScheduler {
	// invoke the object at the given time
	public void addEvent(Reminder reminderObj);

	// remove the object from the pool
	public void removeEvent(Reminder reminderObj);
}

public class EventScheduler implements IScheduler {

	private static EventScheduler eventScheduler;
	private final ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);
	private List<ScheduledFuture<Reminder>> beeperHandleList = new ArrayList<ScheduledFuture<Reminder>>();
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
	public void addEvent(Reminder reminderObj) {
		long triggerSecs = getTriggerSeconds(reminderObj);
		NotifyReminder notifyReminderThread = new NotifyReminder(reminderObj);
		@SuppressWarnings("unchecked")
		ScheduledFuture<Reminder> scheduledFuture = (ScheduledFuture<Reminder>) scheduler
				.schedule(notifyReminderThread, triggerSecs, TimeUnit.SECONDS);
		beeperHandleList.add(scheduledFuture);
	}

	@Override
	public void removeEvent(Reminder reminderObj) {
	}

	private long getTriggerSeconds(Reminder reminderObj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date currDate = new Date();
		Date reminderDate = null;
		try {
			reminderDate = sdf.parse(reminderObj.getActivationTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diffdate = reminderDate.getTime() - currDate.getTime();
		long diffsecs = diffdate / (1000);
		return diffsecs;
	}
}
