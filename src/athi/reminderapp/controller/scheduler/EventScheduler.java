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
import athi.reminderapp.model.IEvent;
import athi.reminderapp.model.Event;
import athi.reminderapp.model.ReminderList;

interface IScheduler {	
	/*
	 * the ideal add function should accept the parameters and it should create the reminder event
	 */
	public void addEvent(String reminderDesc, String reminderDate, String reminderTime);
	
	public List<Event> pullEvents();
	
	/*
	 * How to remove an event does it has any attributes to identify??????????
	 */
	public void removeEvent(Event reminderObj);
}

public class EventScheduler implements IScheduler {

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

	private long getTriggerSeconds(IEvent reminderObj) {
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
	public List<Event> pullEvents() {
		ScheduledFuture<Event> schedRemVar;
		List<Event> currRemList = new ArrayList<Event>();
		
		for(int i=0; i< beeperHandleList.size(); i++){
			schedRemVar = beeperHandleList.get(i);
			try {
				Event rTemp;
				rTemp = (Event) schedRemVar.get().clone();
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
		
		Event newReminderObj = new Event();
		newReminderObj.setEventDesc(reminderDesc);
		newReminderObj.setEventDate(reminderDate);
		newReminderObj.setEventTime(reminderTime);
		scheduleEvent(newReminderObj);
	}
	
	private void scheduleEvent(Event reminderObj){
		long triggerSecs = getTriggerSeconds(reminderObj);
		
		NotifyReminder notifyReminderThread = new NotifyReminder(reminderObj);
		scheduleEvent0(notifyReminderThread, triggerSecs);
	}
	
	private void scheduleEvent0(NotifyReminder notifyReminderThread, long  triggerSecs){
		ScheduledFuture<Event> scheduledFuture = 
				(ScheduledFuture<Event>) scheduler
				.schedule(notifyReminderThread, triggerSecs, TimeUnit.SECONDS);
		
		beeperHandleList.add(scheduledFuture);
	}
}
