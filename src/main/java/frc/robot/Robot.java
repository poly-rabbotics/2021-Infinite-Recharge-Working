/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.AutonomousDrive;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDLights;
import frc.robot.subsystems.LIDAR;
import frc.robot.subsystems.PixyServo;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.AutoModes;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static AutonomousDrive AutoDrive;
  public static Drive drive;
  public static Shooter shooter;
  public static Conveyor conveyor;
  public static PixyServo pixyServo;
  public static LEDLights led;
  public static Intake intake;
  public static Climb climb;
  public static Timer timer;
  public static AutoModes autoModes;
  Compressor comp;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    AutoDrive = new AutonomousDrive();
    LIDAR lidar = new LIDAR();
    comp = new Compressor();
    lidar.start();
    drive = new Drive();
    shooter = new Shooter();
    conveyor = new Conveyor();
    led = new LEDLights();
    pixyServo = new PixyServo();
    intake = new Intake();
    climb = new Climb();
    timer = new Timer();
    autoModes = new AutoModes();
    timer.start();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("timer", timer.get());
    double robotPressure = 40.16 * (RobotMap.pressureTransducer.getVoltage() - 0.52);
    led.run();
    led.pattern=2;
    comp.enabled();
    autoModes.setMode();
    if(isDisabled()){
      LEDLights.pattern = 2;
    }
    else if(isAutonomous()){
      LEDLights.pattern = 1;
    }
    SmartDashboard.putNumber("Robot Pressure",robotPressure );
    boolean safeToClimb = robotPressure > 60;
    SmartDashboard.putBoolean("Safe To Climb", safeToClimb);
    SmartDashboard.putNumber("Sonar (inches)",RobotMap.sonar.getAverageVoltage()/.00977/2.53);
    SmartDashboard.putNumber("Left RPM", RobotMap.leftBack.getEncoder().getVelocity());
    SmartDashboard.putNumber("Right RPM", RobotMap.rightBack.getEncoder().getVelocity());
    SmartDashboard.putString("Drive Mode", Drive.driveMode);
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    //m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    
    timer.reset();
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Autonomous Time Remaining", 15 - timer.get());
    autoModes.run();
    
    //Drive.autoRun(0, 5, 0.4, 0);
    /*
    Conveyor.autoRun(0, 4, 0.5);
    Shooter.autoRun(0,5,2);
    Drive.autoRun(5,8,0,0.5);
    */
    //Conveyor.autoRun(2, 4, 0.8); o o                                                                          t
    //Shooter.autoRun(0,4,1);
  }

  /**
   * This function is called once when teleop is enabled.
   */
  @Override
  public void teleopInit() {
    RobotMap.leftFront.getEncoder().setPosition(0);
    RobotMap.rightFront.getEncoder().setPosition(0);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    AutoDrive.printState();

    SmartDashboard.putBoolean("Prox Sensor", !RobotMap.proxSensorLow.get());

    
    SmartDashboard.putBoolean("Pixy Detect", RobotMap.pixydetect.get());
    SmartDashboard.putNumber("Pixy Angle",RobotMap.pixyposition.getAverageVoltage());

    /*if(RobotMap.driveJoystick.getRawButtonPressed(8)||drive.isAutoDrive==true)
    {
      drive.isAutoDrive=true;
      drive.run(1,10);
    }
    else*/
    drive.run();
    shooter.run();
    conveyor.run();
    pixyServo.run();
    intake.run();
    climb.run();
  }

  /**
   * This function is called once when the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  /**
   * This function is called periodically when disabled.
   */
  @Override
  public void disabledPeriodic() {
  }

  /**
   * This function is called once when test mode is enabled.
   */
  @Override
  public void testInit() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
