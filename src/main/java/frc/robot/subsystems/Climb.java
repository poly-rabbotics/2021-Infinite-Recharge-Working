// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Controls.MechanismsJoystick;



public class Climb extends Subsystem {
  
  public void run() {
    //Activates climb if armed and climb button pressed
    //Deactivates climb if not armed
    if (MechanismsJoystick.arm() && MechanismsJoystick.climbPressed() && MechanismsJoystick.climb()) {
      RobotMap.climber.set(Value.kReverse);
    } else if (!MechanismsJoystick.arm()) {
      RobotMap.climber.set(Value.kForward);
    } 
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
