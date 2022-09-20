package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingSubsystem extends SubsystemBase {

    private final PWMVictorSPX climbMotor;

    public ClimbingSubsystem() {
        climbMotor = new PWMVictorSPX(Constants.CLIMBING_PORT);
    }

    public void setSpeed(double power) {
        climbMotor.set(-power);
    }
}
