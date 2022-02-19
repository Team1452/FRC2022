package frc.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.RobotMap;

public class Intake {
    // motors
    private final CANSparkMax lift, intake;
    private IntakeMode intakeMode = IntakeMode.INWARD;

    // intake state
    private LiftPosition position;
    private boolean active = false;

    public Intake() {
        lift = new CANSparkMax(RobotMap.INTAKE_MOTOR_LIFT, MotorType.kBrushless);
        intake = new CANSparkMax(RobotMap.INTAKE_MOTOR_INTAKE, MotorType.kBrushless);
    }

    /**
     * Sync motors with interal Intake state
     * to be called in periodic methods by Robot
     */
    public void update(XboxController controller) {
        // update mode
        if (controller.getLeftBumper())
            toggleDirection();

        // update intake motor
        double speed;
        
        switch (intakeMode) {
            case INWARD:
                speed = controller.getLeftTriggerAxis();
                break; 
            case OUTWARD: 
                speed = -controller.getLeftTriggerAxis();
                break;
            default:
                System.err.println("Unhandled IntakeMode in Intake: " + intakeMode);
                return;
        }
        
        intake.set(active ? speed : 0);
    }

    /**
     * Toggle direction from inward/outward
     */
    private void toggleDirection(){
        intakeMode = intakeMode == IntakeMode.INWARD 
            ? IntakeMode.OUTWARD 
            : IntakeMode.INWARD;
    }

    /**
     * Getters/setters
     */
    public IntakeMode getMode() { return intakeMode; }
    public void setMode(IntakeMode mode) { intakeMode = mode; }
    public void setActive(boolean active) { this.active = active; }
    public void setPosition(LiftPosition pos) { position = pos; }
}