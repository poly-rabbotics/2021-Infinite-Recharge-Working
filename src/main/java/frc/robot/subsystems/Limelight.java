package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//upload test

public class Limelight {
//The "eyes" of the robot
boolean isTracking;
static double x, y, area;


private NetworkTableEntry ledMode;
private NetworkTableEntry camMode;

static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
static NetworkTableEntry tx = table.getEntry("tx");
  static  NetworkTableEntry ty = table.getEntry("ty");
  static  NetworkTableEntry ta = table.getEntry("ta");
  static  NetworkTableEntry tv = table.getEntry("tv");


 

public Limelight(){   
    
    
    isTracking = false;
}
public static double getX() {
    return tx.getDouble(0);
}
public double getY() {
    return y;
}
public boolean getTargetFound() {
    SmartDashboard.putBoolean("Tv?",table.containsKey("tv"));
    double v = tv.getDouble(0);
    if (v == 0.0){
        return false;
    }else {
        return true;
    }
}
public void trackingMode(){
    camMode.setDouble(0);
    ledMode.setDouble(0);
    isTracking = true;


}



public void calibrateLimelight(){
    //trackingMode();
    //read values periodically
    isTracking = true;
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    ledMode = table.getEntry("ledMode");
    camMode = table.getEntry("camMode");    

    
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    if(getTargetFound() && isTracking == true){

        if(x < -1.5 || x > 1.5){
            SmartDashboard.putBoolean("is centered", false);
        }
        else{
            SmartDashboard.putBoolean("is centered", true);

        }
        
        

    }
    else{
        trackingMode();
        isTracking = false;
        SmartDashboard.putBoolean("is centered", false);

    }
    
    
        
    }
    
   

}
