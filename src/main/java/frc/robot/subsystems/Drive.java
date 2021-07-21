// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Controls.DriveJoystick;
import frc.robot.Controls.MechanismsJoystick;
import frc.robot.RobotMap;

/** Add your docs here. */

  
public class Drive extends Subsystem { 
  Timer timer;
  double time, oldTime, cP, cD, cI, power, accumError;
  double x, oldX;
  double move, turn;
  Joystick calibrateJoy;

  public Drive()
  {
    calibrateJoy = new Joystick(2);
    timer = new Timer();
    timer.start();
    cP = 0.0425;  //Constants determined through testing, don't change these
    cD = 0.0173;  
    cI = 0.0014;
    move=0;
    turn=0;
    oldTime = 0;
    accumError = 0;
    time = timer.get();
    x=0;
  }



  public static boolean isAutoDrive = false;
  public boolean intakeforward = true;
  SpeedControllerGroup leftDrive = new SpeedControllerGroup(RobotMap.leftFront,RobotMap.leftBack);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(RobotMap.rightFront,RobotMap.rightBack);

  public DifferentialDrive drive = new DifferentialDrive(leftDrive,rightDrive);

  void joystickDrive()
  {
    move=DriveJoystick.getMove(); 
    turn=DriveJoystick.getTurn();

    move = Math.signum(move) * Math.pow(move,2);

    turn = Math.pow(turn,3);

    if(DriveJoystick.getCameraOrient())  //direction switch
      intakeforward = !intakeforward;
    
      if(!intakeforward)
      {
        move = -1*move;
      }
        

    
    double totalError = Math.abs(x);
    oldTime = time;
    time = timer.get();
    oldX = x;
    x = Limelight.getX() - 5;
    double deltaVelocity = (x - oldX) / (time - oldTime);
    power = cP * x + (cD * deltaVelocity) + cI * accumError;  //The PID-based power calculation for LL auto-aim

        
   SmartDashboard.putNumber("deltaVelocity", deltaVelocity);
   SmartDashboard.putNumber("PIDpower", power);
   SmartDashboard.putNumber("cP", cP);
   SmartDashboard.putNumber("x", x);

    if (DriveJoystick.aim()) drive.arcadeDrive(0, power); 
    else move();
    if (DriveJoystick.aim()) accumError = 0;

  }

  public void move(){
    drive.arcadeDrive(move, turn);
  }
  boolean autoDrive(double distance)
  {
    boolean done=false;

    if(Math.abs(RobotMap.leftFront.getEncoder().getPosition())>distance&&Math.abs(RobotMap.rightFront.getEncoder().getPosition())>distance)
    
      done=true;
    else done=false;
    drive.arcadeDrive(move,turn);

    if(done)
    {
      isAutoDrive=false;
      RobotMap.leftFront.getEncoder().setPosition(0);
      RobotMap.rightFront.getEncoder().setPosition(0);
    }

    return done;
  }

  public void run()
  {
    joystickDrive();
    SmartDashboard.putNumber("joy pos", DriveJoystick.getMove());
    adjustPIDS();

  }
  public void adjustPIDS() { //use for adjusting PID values
        if (calibrateJoy.getRawAxis(5) < -0.5) {
            cP = cP + 0.0001;
        } else if (calibrateJoy.getRawAxis(5) > 0.5) {
            cP = cP - 0.0001;
        }
        SmartDashboard.putNumber("cD", cD);
        if (calibrateJoy.getRawAxis(1) < -0.5) {
            cD = cD + 0.0001;
        } else if (calibrateJoy.getRawAxis(1) > 0.5) {
            cD = cD - 0.0001;
        }
        SmartDashboard.putNumber("cP", cP);

        if (calibrateJoy.getRawAxis(3) > 0.5) {
            cI = cI + 0.0001;
        } 
        if (calibrateJoy.getRawAxis(2) > 0.5) {
            cI = cI - 0.0001;
        }
        SmartDashboard.putNumber("cI", cI);
    }

  public void run(int type,double setpoint)
  {
    //PURELY EXPERIMENTAL, mostly ignore this
    if(DriveJoystick.getResetEncoder())
    {
      RobotMap.leftFront.getEncoder().setPosition(0);
      RobotMap.rightFront.getEncoder().setPosition(0);
    }

    switch (type){
      case 0:
        joystickDrive();
      break;

      case 1:

        if(!autoDrive(setpoint))
        {
          move=0.5;
          turn=0;
        }
        else
        {
          move=0;
          turn=0;
        }
        break;
      
      case 2:   
        if(!autoDrive(setpoint))
        {
          move=0;
          turn=0.5;
        }
        else
        {
          move=0;
          turn=0;
        }
        break;

        case 3:   
        if(!autoDrive(setpoint))
        {
          move=0;
          turn=-0.5;
        }
        else
        {
          move=0;
          turn=0;
        }
        break;
        default:
        break;
    }
      
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
