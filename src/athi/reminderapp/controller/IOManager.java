package athi.reminderapp.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import athi.reminderapp.config.AppConfig;
import athi.reminderapp.model.AllEventList;
import athi.reminderapp.model.Event;

/* The purpose of this class is to carry out the functionality of saving and retrieving 
 * reminders from file.
 */
public class IOManager implements IIOManager{
	private ObjectInputStream  ois = null;
	private FileInputStream    fis = null;
	private FileOutputStream   fos = null;
	private ObjectOutputStream oos = null;
	private AllEventList allEventList;
	
	private void initReminders(){
	}

	@Override
	public void saveReminders() {	
		allEventList = AllEventList.getInstance();
		int count=0;
		System.out.println("Before writing into file, showing the event description");
		List<Event> templist = allEventList.getEventList();
		for(Event e: templist){
			System.out.println(e.getEventDesc());
			count++;
		}
		System.out.println("Total events in the current list: "+count);
		
		try {
			fos = new FileOutputStream(AppConfig.APP_DB_FILE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(allEventList.getEventList()); // save the object...
			oos.close();
			fos.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Save Error");
		}
	}

	@Override
	public boolean checkOldReminders() {
		try {
			fis = new FileInputStream(AppConfig.APP_DB_FILE);
			ois = new ObjectInputStream(fis);
		} 
		catch (FileNotFoundException e) {
			System.out.println("No previous load found ");
			return false;
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Serious Error Shutting down application: "
					+ "IO Exception!");
			System.exit(1);
		}
		return true;
	}

	@Override
	public void loadReminders() {
		Object listobject = null;
		
		if(true == checkOldReminders()) {
			try{
				listobject = ois.readObject();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Error in reading reminderlist from ObjectInputStream!"
						+ "Class Not-found Exception!");
				System.exit(1);
			} 
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error in reading reminderlist from ObjectInputStream"
						+ "IO Exception!");
				System.exit(1);
			}
			
			if (null != listobject && listobject instanceof ArrayList) {
				AllEventList.getInstance().setEventList((ArrayList<Event>)listobject);
				//AllEventList.getInstance().setAllEventList((AllEventList)listobject);
				
				System.out.println("OK, Reminders from file got loaded.");
				//System.out.println("No of objects added: "+AllEventList.getInstance().getEventList().size());
				try {
					ois.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error in closing ObjectInputStream or FileInputStream"
							+ "IO Exception!");
					System.exit(1);
				}				
			} 
			else {
				System.out.println("Serious error : Unidentified object "
						+ "present in the storage! Shutting down application");
				System.exit(1);
			}
		}
		else{
			System.out.println("OK, No previous file found."
					+ "So initializing an empty reminder list!");
		}
	}
}