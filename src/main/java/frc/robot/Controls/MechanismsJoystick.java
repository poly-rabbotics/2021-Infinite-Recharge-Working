package frc.robot.Controls;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;

public class MechanismsJoystick {
    private static Joystick joystick = RobotMap.mechanismsJoystick;


    public static boolean startGalacticSearch(){
    return  joystick.getRawButton(4); //needs to be given an actual number
      }

     public static boolean farShot(){
        return  joystick.getRawButton(7); //needs to be given an actual number
          }

      public static boolean midShot(){
          return  joystick.getRawButton(6); //needs to be given an actual number
        }
        public static boolean closeShot(){
          return  joystick.getRawButton(5); //needs to be given an actual number
        }

        public static boolean conveyor(){
          return joystick.getRawButton(8);
        }

        public static boolean reverse(){
          return joystick.getRawButton(11);
        }

        public static boolean intake(){
          return joystick.getRawButton(9);
        }
        public static boolean climbPressed(){
          return joystick.getRawButtonPressed(10);
        }
        public static boolean climb(){
          return joystick.getRawButton(10);
        }
        public static boolean arm(){
          return joystick.getRawButton(4);
        }

        


}