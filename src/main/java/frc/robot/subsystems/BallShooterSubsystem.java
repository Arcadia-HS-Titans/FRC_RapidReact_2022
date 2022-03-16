package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallShooterSubsystem extends SubsystemBase {
    private final CANSparkMax canBus;
    private final PWMVictorSPX susanMotor;
    //private final DigitalInput magSwitch;

    private double rotationCounter = 0;

    public BallShooterSubsystem() {
        this.canBus = new CANSparkMax(12, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO: Change device ID to when it's set up
        this.susanMotor = new PWMVictorSPX(Constants.SUSAN_ROTATION_PORT);
        //this.magSwitch = new DigitalInput(Constants.MAGNETIC_SWITCH_PORT);
    }

    public void fire(double powerPercent) {
        canBus.set(powerPercent);
    }

    public void setInverted(boolean value) {
        susanMotor.setInverted(value);
    }

    public void turnSusan(double power) {
/*        rotationCounter+=power;
        if(read()) {
            if(rotationCounter > 0) {
                //TODO: Restrict movement/flip around
            } else {
                //TODO: Restrict movement/flip around
            }
            return;
        }
        susanMotor.set(power);*/
        susanMotor.set(power);
    }

    public boolean read() {
        return false;
    }
}
