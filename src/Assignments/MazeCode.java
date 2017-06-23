package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class MazeCode extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public MazeCode(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		MazeCode rob = new MazeCode(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
		
	}

	
	
	private void setup() throws Exception {
		//SETUP CODE GOES HERE!!!!!
		
	}
	
	private boolean loop() throws Exception{
		//LOOP CODE GOES HERE!!!!!
		readSensors(100);
		int[] lightBumpReadings = getLightBumps();
		
		driveDirect(450,400);
		
		if (isBumpLeft() && isBumpRight()) {
			driveDirect(-400,-400);
			sleep(10);
			driveDirect(-400,400);
			sleep(10);
		}
		
		if(lightBumpReadings[2]>0 || lightBumpReadings[3]>0){
			driveDirect(-400,-400);
			sleep(10);
			driveDirect(-400,400);
			sleep(10);
		}
		
		if (lightBumpReadings[5]>0 || lightBumpReadings[4]>0 || lightBumpReadings[0]>0 || lightBumpReadings[1]>0) {
			driveDirect(-400,400);
			sleep(10);
			
		}
	

		
		return true;
	}
	
	private void sleep(int amt){
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
