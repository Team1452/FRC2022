// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;


//import frc.Subsystems.Mechanism1;
//import edu.wpi.first.wpilibj.PowerDistribution;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.hal.FRCNetComm.tResourceType;
//import edu.wpi.first.hal.HAL;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    //private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
    private Joystick leftControl;
   // private Joystick rightControl;
    //private Mechanism1 mechanism1;
    private CANSparkMax leftDrive;
    private CANSparkMax rightDrive;
    private CANSparkMax leftDrive2;
    private CANSparkMax rightDrive2;
    //private XboxController driverXbox = new XboxController(1);
    //private PowerDistribution distro;

   // private DifferentialDrive robotDrive;
   private Joystick driverControl1;
    

    /**
     * This function is run when the robot is first started up and should be used
     * for any 
     * initialization code.
     */
    @Override
    public void robotInit() {
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
        leftControl = new Joystick(0);
       // rightControl = new Joystick(1);
        //mechanism1 = new Mechanism1(1);
        
        driverControl1 = new Joystick(1);
        leftDrive = new CANSparkMax(3, MotorType.kBrushless);
        leftDrive.restoreFactoryDefaults();
        leftDrive2 = new CANSparkMax(5, MotorType.kBrushless);

        rightDrive = new CANSparkMax(4 ,MotorType.kBrushless);
        rightDrive2 = new CANSparkMax(2 ,MotorType.kBrushless);
        rightDrive.restoreFactoryDefaults();
       // rightDrive.setInverted(true);

      // robotDrive = new DifferentialDrive(leftDrive, rightDrive);


        

        
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and
     * test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different
     * autonomous modes using the dashboard. The sendable chooser code works with
     * the Java
     * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the
     * chooser code and
     * uncomment the getString line to get the auto name from the text box below the
     * Gyro
     *
     * <p>
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure
     * below with additional strings. If using the SendableChooser make sure to add
     * them to the
     * chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        //m_autoSelected = m_chooser.getSelected();
        // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        //System.out.println("Auto selected: " + m_autoSelected);
    int milli = 10000000; // This should run 1000ms = 1 s.
       for(int i=milli;i>=0; i--){
            // Place your code here.
            Auton.move(2.1);

       }
       Auton.move(0);
            
        }
       
        //distro.clearStickyFaults();
        

   

    
    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        /*switch (m_autoSelected) {
            case kCustomAuto:
                // Put custom auto code here
                break;
            case kDefaultAuto:
            default:
                // Put default auto code here
                break;
        }*/
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        //double mechanismSpeed = 0.65 * Util.state(leftControl.getRawButton(1), leftControl.getRawButton(0));
        //mechanism1.turn(mechanismSpeed);
        double turn = driverControl1.getRawAxis(0)*0.25;
        double speed = driverControl1.getRawAxis(5)*-0.5;

        double leftPower = turn+speed;
        double rightPower = turn -speed;

        leftDrive.set(leftPower);
        leftDrive2.set(leftPower);

        rightDrive.set(rightPower);
        rightDrive2.set(rightPower);


    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {
    }

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
        //distro.clearStickyFaults();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
        leftDrive.set(leftControl.getY() + leftControl.getX());
        rightDrive.set(leftControl.getY() - leftControl.getX());
        System.out.println("Hello");
    }
}
