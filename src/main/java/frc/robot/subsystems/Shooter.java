// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Controls.ConfigJoystick;
import frc.robot.Controls.MechanismsJoystick;

/** Add your docs here. */
public class Shooter extends Subsystem {

  Timer conveyorDelay = new Timer();
  Conveyor conveyor;
  ConfigJoystick stick = new ConfigJoystick();
  //Change these to change shooter power
  public static double lowSetpointBottom = 0.5, medSetpointBottom = 0.325, highSetpointBottom = 0.5;
  public static double lowSetpointTop = 0.5, medSetpointTop = 0.325, highSetpointTop = 0.5;
  
  public void run()
  {
    SmartDashboard.putNumber("High Setpoint Top",highSetpointTop);
    SmartDashboard.putNumber("High Setpoint Bottom",highSetpointBottom);
    SmartDashboard.putNumber("Med Setpoint Top",medSetpointTop);
    SmartDashboard.putNumber("Med Setpoint Bottom",medSetpointBottom);
    SmartDashboard.putNumber("Low Setpoint Top",lowSetpointTop);
    SmartDashboard.putNumber("Low Setpoint Bottom",lowSetpointBottom);
    SmartDashboard.putBoolean("UpperShooterSpeedUp",ConfigJoystick.configUpperShooterSpeedUp());

    if(MechanismsJoystick.farShot()) //if far shot pressed, set angle, spin up, run conveyor, and shoot
    {
      RobotMap.shooterAngle.set(Value.kReverse);
      conveyorDelay.start();
      RobotMap.top.set(TalonSRXControlMode.PercentOutput,-highSetpointTop);
      RobotMap.bottom.set(TalonSRXControlMode.PercentOutput,highSetpointBottom);

      if(conveyorDelay.get()>1.5)
        Conveyor.customsetpoint=0.8;

      if(ConfigJoystick.configUpperShooterSpeedUp())
        highSetpointTop+=0.001;
      else if(ConfigJoystick.configUpperShooterSpeedDown())
        highSetpointTop-=0.001;

      if(ConfigJoystick.configLowerShooterSpeedUp())
        highSetpointBottom+=0.001;
      else if(ConfigJoystick.configLowerShooterSpeedDown())
        highSetpointBottom-=0.001;
    }
    else if(MechanismsJoystick.midShot()) //if mid shot pressed, set angle, spin up, run conveyor, and shoot
    {
      RobotMap.shooterAngle.set(Value.kForward);
      conveyorDelay.start();
      RobotMap.top.set(TalonSRXControlMode.PercentOutput,-medSetpointTop);
      RobotMap.bottom.set(TalonSRXControlMode.PercentOutput,medSetpointBottom);

      if(conveyorDelay.get()>1.5)
      Conveyor.customsetpoint=0.8;

      if(ConfigJoystick.configUpperShooterSpeedUp())
        medSetpointTop+=0.001;
      else if(ConfigJoystick.configUpperShooterSpeedDown())
        medSetpointTop-=0.001;

      if(ConfigJoystick.configLowerShooterSpeedUp())
        medSetpointBottom+=0.001;
      else if(ConfigJoystick.configLowerShooterSpeedDown())
        medSetpointBottom-=0.001;
    }
    else if(MechanismsJoystick.closeShot()) //if far shot pressed, set angle, spin up, run conveyor, and shoot
    {
      RobotMap.shooterAngle.set(Value.kForward);
      conveyorDelay.start();
      RobotMap.top.set(TalonSRXControlMode.PercentOutput,-lowSetpointTop);
      RobotMap.bottom.set(TalonSRXControlMode.PercentOutput,lowSetpointBottom);

      if(conveyorDelay.get()>1.5)
      Conveyor.customsetpoint=0.8;

      if(ConfigJoystick.configUpperShooterSpeedUp())
      lowSetpointTop+=0.001;
    else if(ConfigJoystick.configUpperShooterSpeedDown())
      lowSetpointTop-=0.001;

    if(ConfigJoystick.configLowerShooterSpeedUp())
      lowSetpointBottom+=0.001;
    else if(ConfigJoystick.configLowerShooterSpeedDown())
      lowSetpointBottom-=0.001;
    }
    else{
      RobotMap.top.set(TalonSRXControlMode.PercentOutput,0);
      RobotMap.bottom.set(TalonSRXControlMode.PercentOutput,0);
      conveyorDelay.reset();
      Conveyor.customsetpoint=0;
    }

    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
