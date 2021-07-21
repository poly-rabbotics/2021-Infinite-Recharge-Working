//PURELY EXPERIMENTAL, IGNORE THIS CLASS
package frc.robot.subsystems;

import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDrive {
    public CANSparkMax leftFront, rightFront, leftBack, rightBack;
    public double inchesToTravel, encoderCountsPerInch, encoderCountsPer360;
    
    public AutonomousDrive() {
        leftFront = RobotMap.leftFront;
        rightFront = RobotMap.rightFront;
        leftBack = RobotMap.leftBack;
        rightBack = RobotMap.rightBack;
        encoderCountsPerInch = 2; //insert real number here
        encoderCountsPer360 = 10; //insert real number here
        
    }
    public void printState() {
        SmartDashboard.putNumber("leftFront Encoder Counts", leftFront.getEncoder().getPosition());
        SmartDashboard.putNumber("rightFront Encoder Counts", rightFront.getEncoder().getPosition());
        SmartDashboard.putNumber("leftBack Encoder Counts", leftBack.getEncoder().getPosition());
        SmartDashboard.putNumber("rightBack Encoder Counts", rightBack.getEncoder().getPosition());

        
      }

    /*public  void DriveByDistance(double inchesToTravel) {
        leftFront.setPosition(leftFront.getEncoder().getPosition()+(inchesToTravel * encoderCountsPerInch));
        rightFront.setPosition(-(rightFront.getEncoder().getPosition()+(inchesToTravel * encoderCountsPerInch)));
        leftBack.setPosition(leftBack.getEncoder().getPosition()+(inchesToTravel * encoderCountsPerInch));
        rightBack.setPosition(-(rightBack.getEncoder().getPosition()+(inchesToTravel * encoderCountsPerInch)));
    }
    
    public void Turn(double degrees) {
        leftFront.setPosition(leftFront.getEncoder().getPosition()+(degrees * (encoderCountsPer360/360) ) );
        rightFront.setPosition(-(rightFront.getEncoder().getPosition()+(degrees * (encoderCountsPer360/360) ) ) );
        leftBack.setPosition(leftBack.getEncoder().getPosition()+(degrees * (encoderCountsPer360/360) ) );
        rightBack.setPosition(-(rightBack.getEncoder().getPosition()+(degrees * (encoderCountsPer360/360) ) ) );
    }*/

}
