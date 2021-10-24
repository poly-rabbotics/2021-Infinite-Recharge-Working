package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class RobotMap {
    public static final Joystick driveJoystick = new Joystick(0);
    public static final Joystick mechanismsJoystick = new Joystick(1);
    public static final Joystick configJoystick = new Joystick(2);

    // CAN Control
    public static final CANSparkMax leftFront = new CANSparkMax(1, MotorType.kBrushless);
    public static final CANSparkMax leftBack = new CANSparkMax(2, MotorType.kBrushless);
    public static final CANSparkMax rightFront = new CANSparkMax(3, MotorType.kBrushless);
    public static final CANSparkMax rightBack = new CANSparkMax(4, MotorType.kBrushless);
    public static final TalonSRX top = new TalonSRX(5);
    public static final TalonSRX bottom = new TalonSRX(6);

    // PWM Control
    public static final PWMVictorSPX colorWheel = new PWMVictorSPX(0);
    public static final PWMVictorSPX lowerConveyor = new PWMVictorSPX(1);
    public static final PWMVictorSPX upperConveyor = new PWMVictorSPX(2);
    public static final PWMVictorSPX intake = new PWMVictorSPX(3);
    public static final Servo pixyServo = new Servo(8);
    public static final AddressableLED led = new AddressableLED(9);


    // PCM
    public static final DoubleSolenoid shooterAngle = new DoubleSolenoid(0,1);
    public static final DoubleSolenoid climber = new DoubleSolenoid(2,3);
    
    // DIO
    public static final DigitalInput proxSensorLow = new DigitalInput(2);
    public static final DigitalInput proxSensorHigh = new DigitalInput(3);    
    public static final DigitalInput pixydetect = new DigitalInput(9);

    // Analog Input
    public static final AnalogInput sonar = new AnalogInput(0);
    public static final AnalogInput pixyposition = new AnalogInput(2);
    public static final AnalogInput pressureTransducer = new AnalogInput(3);
}