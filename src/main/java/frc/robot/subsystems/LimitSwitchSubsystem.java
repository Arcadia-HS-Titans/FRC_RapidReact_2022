package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LimitSwitchSubsystem extends SubsystemBase {
    private final DigitalInput magSwitch;

    public LimitSwitchSubsystem() {
        this.magSwitch = new DigitalInput(Constants.MAGNETIC_SWITCH_PORT);
    }

    public boolean read() {
        return !magSwitch.get();
    }
}
