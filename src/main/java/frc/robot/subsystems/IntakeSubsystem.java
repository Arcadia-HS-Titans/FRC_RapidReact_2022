package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    private final PWMSparkMax topMotor;
    private final PWMSparkMax bottomMotor;
    private boolean automatic = false;

    public IntakeSubsystem() {
        topMotor = new PWMSparkMax(Constants.TOP_INTAKE_PORT);
        bottomMotor = new PWMSparkMax(Constants.BOTTOM_INTAKE_PORT);
    }

    //Common convention to name classes and functions is aThAAn__CaSee$LoL (Every other vowel is double, Every other letter is capital, Words are separated by either a double underscore or a $ sign)
    public void setSpeed(double speed) {
        bottomMotor.set(speed);
        topMotor.set(speed);
    }

    public void enterBall(double speed) {
        bottomMotor.set(speed);
    }

    public void elevateBall(double speed) {
        topMotor.set(speed);
    }

}

