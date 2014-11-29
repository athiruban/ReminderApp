package com.buzzer.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class InstanceChecker {
	public void start() {
		/* int port, int maxQueue, InetAddress localAddress */
		try {
			ServerSocket ssocket = new ServerSocket(65535, 1,
					InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			System.out.println("Application already running!");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Application already running!");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
