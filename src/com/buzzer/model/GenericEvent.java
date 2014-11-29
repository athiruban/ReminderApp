package com.buzzer.model;

/*
 * This is my application core bean interface
 */

public interface GenericEvent extends java.io.Serializable {
	/*
	 * answer to What is the event all about?
	 */
	public String getEventDesc();

	/*
	 * answer to When the event is going to take place?
	 */
	public String getEventDate();

	public String getEventTime();

	// public String getActivationTime();
}

/*
 * The event attributes are grouped under various interfaces as given below What
 * && When are two common questions attached to an event. In future Where is the
 * event is going to happen? Who is going to host the event? Whether this is a
 * recurring event? How can I schedule this event?
 */
