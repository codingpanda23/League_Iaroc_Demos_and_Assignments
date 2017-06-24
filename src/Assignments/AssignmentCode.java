package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class AssignmentCode extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public AssignmentCode(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		AssignmentCode rob = new AssignmentCode(base);
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
		driveDirect(500,500);
		int[] lightBumpReadings = getLightBumps();
		if (lightBumpReadings[2]>0 || lightBumpReadings[3]>0 ) {
			driveDirect(500,-500);
			sleep(825);
		}
		if(lightBumpReadings[0]>0 || lightBumpReadings[1]>0){
			driveDirect(-500,-500);
			sleep(250);
			driveDirect(500,-500);
			sleep(150);
			}
		
		if (lightBumpReadings[4]>0 || lightBumpReadings[5]>0) {
			driveDirect(-500,-500);
			sleep(250);
			driveDirect(-500,500);
			sleep(150);
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