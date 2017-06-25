package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class GoldRushCode extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public GoldRushCode(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		GoldRushCode rob = new GoldRushCode(base);
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
		
		driveDirect(400,300);
		
		if (lightBumpReadings[5]>0 || lightBumpReadings[4]>0) {
			driveDirect(-400,400);
			sleep(200);
		}
		if (lightBumpReadings[0]>0 || lightBumpReadings[1]>0) {
			driveDirect(400,-400);
			sleep(200);
		}
		if (lightBumpReadings[3]>0 || lightBumpReadings[2]>0) {
			driveDirect(-400,-400);
			sleep(500);
		}
		
		if(getInfraredByteLeft() > 0){
			driveDirect(400, 500);
			sleep(1300);
		}else if(getInfraredByteRight() > 0){
			driveDirect(500, 400);
			sleep(1300);
		}else{
			driveDirect(400, 450);
		}	

		if(isHomeBaseChargerAvailable()){
			driveDirect(0, 0);
			
			return false;
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

