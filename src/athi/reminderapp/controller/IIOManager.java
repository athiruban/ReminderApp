package athi.reminderapp.controller;

public interface IIOManager {
	public abstract boolean checkOldReminders();
	public void loadReminders();
	public abstract void saveReminders();
}
