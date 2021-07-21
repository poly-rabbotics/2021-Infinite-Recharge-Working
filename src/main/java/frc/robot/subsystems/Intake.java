// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Controls.MechanismsJoystick;

/** Add your docs here. */
public class Intake extends Subsystem {
  
  PWMVictorSPX intake = RobotMap.intake;

  public void run()
  {
    double intakeSpeed;
    double setpoint = 0.5; 
   

    if(MechanismsJoystick.intake())
    {
      boolean isReverse=MechanismsJoystick.reverse();

      if(isReverse)
      {
        intakeSpeed=-1*setpoint;
      }
      else intakeSpeed=setpoint;
    }
    else intakeSpeed = 0;

    intake.set(-intakeSpeed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
