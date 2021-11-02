// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Controls.MechanismsJoystick;
import frc.robot.Robot;

/** Add your docs here. */
public class Intake extends Subsystem {
  static PWMVictorSPX intakeWinch;
  static CANSparkMax intake;
  static double intakeSpeed;
  static boolean intakeUp;
  static double time1 = 0, time2 = 0;
  static double intakeWinchPower = 0;

  public Intake() {
    intakeWinch = RobotMap.intakeWinch;
    intake = RobotMap.intake;
    intakeSpeed = 0.8;
    intakeUp = true;
  }

  public static void run() {
    if (MechanismsJoystick.reverse()) {
      intakeSpeed = -intakeSpeed;
    }

    if (MechanismsJoystick.intake()) {
      intake.set(intakeSpeed);
    } else intake.set(0);

    if (MechanismsJoystick.intakeWinch()) {
      intakeWinch.set(0.5);
    } else {
      intakeWinch.set(0);
    }
    runWinch();
  }

  public static void runWinch(){ //lowers winch if down is selected, raises if up is selected
    if (MechanismsJoystick.intakeWinch() && intakeUp) {
       if (time1 == 0) {
         time1 = Robot.timer.get();
       }
      if (Robot.timer.get() < time1 + 3) {
        intakeWinchPower = 0.5;
      } else {
        intakeWinchPower = 0;
        intakeUp = false;
        time1 = 0;
      }
    }
    if (!MechanismsJoystick.intakeWinch() && !intakeUp) {
      if (time2 == 0) {
        time2 = Robot.timer.get();
      }
     if (Robot.timer.get() < time2 + 3) {
       intakeWinchPower = 0.5;
     } else {
       intakeWinchPower = 0;
       intakeUp = true;
       time2 = 0;
     }
   }
   intakeWinch.set(intakeWinchPower);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
