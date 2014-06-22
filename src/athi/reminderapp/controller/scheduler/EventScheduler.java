package athi.reminderapp.controller.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.TrayIcon.MessageType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import athi.reminderapp.controller.MyApp;
import athi.reminderapp.model.IReminder;
import athi.reminderapp.model.Reminder;
import athi.reminderapp.model.ReminderList;

interface IScheduler {	
	/*
	 * the ideal add function should accept the parameters and it should create the reminder event
	 */
	public void addEvent(String reminderDesc, String reminderDate, String reminderTime);
	
	public List<Reminder> pullEvents();
	
	/*
	 * How to remove an event does it has any attributes to identify??????????
	 */
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
	public void removeEvent(Reminder reminderObj) {
	}

	private long getTriggerSeconds(IReminder reminderObj) {
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

	@Override
	public List<Reminder> pullEvents() {
		ScheduledFuture<Reminder> schedRemVar;
		List<Reminder> currRemList = new ArrayList<Reminder>();
		
		for(int i=0; i< beeperHandleList.size(); i++){
			schedRemVar = beeperHandleList.get(i);
			try {
				Reminder rTemp;
				rTemp = (Reminder) schedRemVar.get().clone();
				currRemList.add(rTemp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return currRemList;
	}

	@Override
	public void addEvent(String reminderDesc, String reminderDate,
			String reminderTime) {
		
		Reminder newReminderObj = new Reminder();
		newReminderObj.setReminderTitle(reminderDesc);
		newReminderObj.setActivationTime(reminderDate, reminderTime);

		scheduleEvent(newReminderObj);
	}
	
	private void scheduleEvent(Reminder reminderObj){
		long triggerSecs = getTriggerSeconds(reminderObj);
		
		NotifyReminder notifyReminderThread = new NotifyReminder(reminderObj);
		scheduleEvent0(notifyReminderThread, triggerSecs);
	}
	
	private void scheduleEvent0(NotifyReminder notifyReminderThread, long  triggerSecs){
		ScheduledFuture<Reminder> scheduledFuture = 
				(ScheduledFuture<Reminder>) scheduler
				.schedule(notifyReminderThread, triggerSecs, TimeUnit.SECONDS);
		
		beeperHandleList.add(scheduledFuture);
	}
}
