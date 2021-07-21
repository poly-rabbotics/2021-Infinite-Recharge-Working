// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
//MOSTLY UNUSED AND EXPERIMENTAL
/** Add your docs here. */
public class PixyServo extends Subsystem {
  
  Servo pixyServo = RobotMap.pixyServo;
  public static double position=45;
  static boolean direction=false;

  public void search()
  {
    if(direction)
    {
      position += .1;
      if(position>90)
        direction = !direction;
    }
    else 
    {
      position -= .1;
      if(position<0)
      direction = !direction;
    }
  }
  double valueToDeg(double angle)
  {
    return (angle/180);
  }
  public void run()
  {
    SmartDashboard.putNumber("Servo position",position);
    search();
    pixyServo.set(valueToDeg(position));
    //pixyServo.set(1);


  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
