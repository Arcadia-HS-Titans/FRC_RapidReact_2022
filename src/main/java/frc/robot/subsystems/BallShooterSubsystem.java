package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallShooterSubsystem extends SubsystemBase {
    private final CANSparkMax canBus;

    public BallShooterSubsystem() {
        this.canBus = new CANSparkMax(12, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO: Change device ID to when it's set up
    }

    public void fire(double powerPercent) {
        canBus.set(powerPercent);
    }
}
