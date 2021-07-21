// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Controls;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;


public class ConfigJoystick extends Command {

  public static Joystick joystick = RobotMap.configJoystick;

  public ConfigJoystick() {
    
  }

  public static boolean configUpperShooterSpeedUp()
  {
    return joystick.getRawButton(4);
  }
  public static boolean configUpperShooterSpeedDown()
  {
    return joystick.getRawButton(1);
  }

  public static boolean configLowerShooterSpeedUp()
  {
    return joystick.getRawButton(2);
  }
  public static boolean configLowerShooterSpeedDown()
  {
    return joystick.getRawButton(3);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
