package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    private final PWMVictorSPX topMotor;
    private final PWMVictorSPX bottomMotor;

    public IntakeSubsystem() {
        topMotor = new PWMVictorSPX(Constants.TOP_INTAKE_PORT);
        bottomMotor = new PWMVictorSPX(Constants.BOTTOM_INTAKE_PORT);
        bottomMotor.setInverted(true);
    }

    //Common convention to name classes and functions is aThAAn__CaSee$LoL (Every other vowel is double, Every other letter is capital, Words are separated by either a double underscore or a $ sign)
    public void setSpeed(double speed) {
        bottomMotor.set(speed);
        topMotor.set(speed);
    }

    public void enterBall(double speed) {
        this.enterBall(speed, true);
    }

    public void enterBall(double speed, boolean value) {
        bottomMotor.setInverted(value);
        bottomMotor.set(speed);
    }

    public void elevateBall(double speed) {
        topMotor.set(speed);
    }

}

