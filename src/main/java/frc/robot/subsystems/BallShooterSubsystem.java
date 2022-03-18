package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BallShooterSubsystem extends SubsystemBase {
    private final CANSparkMax canBus;
    private final PWMVictorSPX susanMotor;
    private final RelativeEncoder encoder;
    private final DigitalInput magSwitch;

    public BallShooterSubsystem() {
        this.canBus = new CANSparkMax(12, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO: Change device ID to when it's set up
        this.susanMotor = new PWMVictorSPX(Constants.SUSAN_ROTATION_PORT);
        this.encoder = canBus.getEncoder();
        this.magSwitch = new DigitalInput(Constants.MAGNETIC_SWITCH_PORT);
    }

    public void fire(double powerPercent) {
        canBus.set(powerPercent);
    }

    public RelativeEncoder getEncoder() {
        return encoder;
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }

    public void setInverted(boolean value) {
        susanMotor.setInverted(value);
    }

    public CANSparkMax getCanBus() {
        return canBus;
    }

    private float rotation = 0;
    private Timer timer = new Timer();
    private boolean forcedTurning = false;
    private double value = 0;

    public void turnSusan(double power) {

        if(forcedTurning) {
            rotation += value;
            powerSusan(value);
            if(timer.get() > .3) {
                forcedTurning = false;
                value = 0;
            }
            return;
        }

        if(!this.magSwitch.get()) {
            if(rotation > 0) {
                forcedTurning = true;
                value = -.5;
                timer.reset();
                timer.start();
            } else {
                forcedTurning = true;
                timer.reset();
                timer.start();
                value = .5;
            }
            return;
        }
        rotation += power;
        powerSusan(power);
    }

    private void powerSusan(double power) {
        if(power >= 0) {
            susanMotor.setInverted(true);
            susanMotor.set(power);
        } else if(power < 0) {
            susanMotor.setInverted(false);
            susanMotor.set(-power);
        } else {
            susanMotor.set(0);
        }
    }

    public boolean read() {
        return !magSwitch.get();
    }
}