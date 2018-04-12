package org.millardps.PetProject;

public class StopWatch {
	//atrributes
	private long startTime;
	private long stopTime;
	private long pausedTime;
	private boolean running;
	
	//constructor
	public StopWatch(){
		startTime = 0;
		stopTime = 0;
		pausedTime = 0;
		running = false;
	}
	
	
	//methods
	/**
	 * Defines the start time in milliseconds on the current system time
	 */
	public void start(){
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}
	
	/**
	 * defines the stop time using the current system time in milliseconds
	 */
	public void stop(){
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}
	/**
	 * if the stopwatch is running this will stop it and save the time passed into a variable
	 */
	public void pause(){
		if(this.running){
			stop();
			this.pausedTime += (this.stopTime - this.startTime);
		}
	}
	/**
	 * gets if the watch is running
	 * @return running
	 */
	public boolean getRunning(){
		return this.running;
	}
	/**
	 * gets the amount of time the stopwatch has been running
	 * @return the amount of time in seconds
	 */
	public long getElapsedTime(){
		long elapsed;
		elapsed = System.currentTimeMillis() - this.startTime;
		return ((elapsed + this.pausedTime)/1000);
	}
	
}
