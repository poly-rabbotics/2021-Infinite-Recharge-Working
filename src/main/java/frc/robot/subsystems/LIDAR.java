// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.TimerTask;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Mostly not used, but could be useful for autonomous later
public class LIDAR extends Subsystem{
	private I2C i2c;
	private static byte[] distance;
	private java.util.Timer updater;
	private LIDARUpdater task;
	
	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int LIDAR_DISTANCE_REGISTER = 0x8f;
	
	public LIDAR() {
		i2c = new I2C(Port.kMXP, LIDAR_ADDR);
		
		distance = new byte[2];
 
		task = new LIDARUpdater();
		updater = new java.util.Timer();
	}
	
	// Distance in cm
	public static int getDistance() {
		return (int)Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
	}
 
	public double pidGet() {
		return getDistance();
	}
	
	// Start 10Hz polling
	public void start() {
		updater.scheduleAtFixedRate(task, 0, 20);
	}
	
	// Start polling for period in milliseconds
	public void start(int period) {
		updater.scheduleAtFixedRate(task, 0, period);
	}
	
	public void stop() {
		updater.cancel();
	}
	
	// Update distance variable
	public void update() {
		i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
		Timer.delay(0.04); // Delay for measurement to be taken
		i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
		Timer.delay(0.01); // Delay to prevent over polling
	}
	
	// Timer task to keep distance updated
	private class LIDARUpdater extends TimerTask {
		public void run() {
			while(true) {
				update();
				double inches = getDistance() / 2.54;
				SmartDashboard.putNumber("LIDAR distance Inches", inches);
				if (inches < 50 && inches > 40) {
					SmartDashboard.putBoolean("In Range Close Shot", true);
				} else {
					SmartDashboard.putBoolean("In Range Close Shot", false);
				}

				if (inches < 150 && inches > 130) {
					SmartDashboard.putBoolean("In Range Mid Shot", true);
				} else {
					SmartDashboard.putBoolean("In Range Mid Shot", false);
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void initDefaultCommand() {
		// Auto-generated method stub

	}
}