// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Controls.MechanismsJoystick;

/** Add your docs here. */
public class Conveyor extends Subsystem {

  static PWMVictorSPX upperConveyor = RobotMap.upperConveyor;
  static PWMVictorSPX lowerConveyor = RobotMap.lowerConveyor;
  public static double customsetpoint = 0;
  public static boolean ballDetect = false;
  Timer ballSpacer = new Timer();

  public void run() {
    double conveyorSpeed;
    double setpoint = 0.8;
    SmartDashboard.putBoolean("Conveyor Reveresed", MechanismsJoystick.reverse());
    if (MechanismsJoystick.conveyor()) {
      boolean isReverse = MechanismsJoystick.reverse();

      if (isReverse) {
        conveyorSpeed = -1 * setpoint;
        
      } else
        conveyorSpeed = setpoint;

    } else {
      // Run if prox sensor detects ball

      if (!RobotMap.proxSensorLow.get() && RobotMap.proxSensorHigh.get()) {
        conveyorSpeed = setpoint;
        ballDetect = true;
        ballSpacer.reset();
        ballSpacer.start();
      } else if (RobotMap.proxSensorLow.get() && ballDetect) {
        SmartDashboard.putNumber("Ball Spacer", ballSpacer.get());
        conveyorSpeed = setpoint;
        if (ballSpacer.get() > 0.2) {
          ballDetect = false;
          conveyorSpeed = customsetpoint;
        }
      } else
        conveyorSpeed = customsetpoint;
    }

    lowerConveyor.set(-conveyorSpeed);
    upperConveyor.set(-conveyorSpeed);
  }

  public static void autoRun(double startTime, double endTime, double conveyorSpeed) {
    double time = Robot.timer.get();
    if (time > startTime && time < endTime) {
      lowerConveyor.set(-conveyorSpeed);
      upperConveyor.set(-conveyorSpeed);
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
