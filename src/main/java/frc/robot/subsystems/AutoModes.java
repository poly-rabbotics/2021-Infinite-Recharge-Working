/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Controls.MechanismsJoystick;

/**
 * Add your docs here.
 */
public class AutoModes {
    public static double autoMode, one, two, three;
 
    public AutoModes(){
        autoMode = 0;
        one = 0;
        two = 0;
        three = 0;
    }
    public void setMode(){
        if(MechanismsJoystick.autoSwitchOne()){
            one = 1;
        }
        else{one = 0;}
        if(MechanismsJoystick.autoSwitchTwo()) {
            two = 2;
        }
        else{two = 0;}
        if(MechanismsJoystick.autoSwitchThree()){
            three = 4;
        }
        else{three = 0;}
        autoMode = one + two + three;
        SmartDashboard.putNumber("Autonomous Mode", autoMode);
    }

    // Mode Zero shoots three balls then drives past the initiation line
    public void modeZero(){
        Shooter.autoRun(0, 5, 2);
        Conveyor.autoRun(2, 5, 0.8);
        Drive.autoRun(5, 6, 0.5,0);
        Shooter.autoRun(5,15,0);
        Conveyor.autoRun(5, 15, 0);
    }

    public void modeOne(){
        Drive.autoRun(0,1, 0.5,0);
        Drive.autoRun(1,6, 0, 0);
        Shooter.autoRun(1, 6, 1);
        Conveyor.autoRun(3,6,0.8);
        Drive.autoRun(6, 7, -0.5, 0);
        Drive.autoRun(7, 15, 0, 0);
    }

    public void modeTwo(){
        Drive.autoRun(0, 2, 0.4, 0);
        Drive.autoRun(2, 4, 0, 0.5);
    }

    public void modeThree(){ //MOST COMMONLY USED: DRIVE FORWARD TO POWER PORT, SHOOT 3 TIMES
        Drive.autoRun(5, 6, 0.5,0);
        Drive.autoRun(6,15,-0.01,0);
        Shooter.autoRun(6, 11, 1);
        Conveyor.autoRun(8, 11,0.8);
        Conveyor.autoRun(11, 15, 0);
        Shooter.autoRun(11,15,0);
        
    }

    public void modeFour(){
        Shooter.autoRun(0, 5, 1);
        Conveyor.autoRun(2, 5,0.8);
        Drive.autoRun(5, 6, -0.5, 0);
    }

    public void modeFive(){
        Shooter.autoRun(3, 9, 2);
        Conveyor.autoRun(5, 9,0.8);
        Drive.autoRun(9, 10, 0.5, 0);
    }

    public void modeSix(){
        Drive.autoRun(0, 1, 0.5, 0);
        Shooter.autoRun(1, 6, 1);
        Conveyor.autoRun(3,6,0.8);
        
    }

    public void modeSeven(){
        Drive.autoRun(0,1,0.5,0);
        Drive.autoRun(1,15,0,0);
        Shooter.autoRun(8, 14, 1);
        Conveyor.autoRun(10, 14,0.8);
    }
    

    public void run(){
        
        if(autoMode == 0){
            modeZero();
        }
        else if(autoMode == 1){
            modeOne();
        }
        else if(autoMode == 2){
            modeTwo();
        }
        else if(autoMode == 3){
            modeThree();
        }
        else if(autoMode == 4){
            modeFour();
        }
        else if(autoMode == 5){
            modeFive();
        }
        else if(autoMode == 6){
            modeSix();
        }
        else if(autoMode == 7){
            modeSeven();
        }
    }
}
