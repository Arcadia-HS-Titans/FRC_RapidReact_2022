package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivingSubsystem;

/**
 * https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html#simple-command-example
 * https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/hatchbottraditional/commands/DefaultDrive.java
 */
public class DrivingTeleopCommand extends CommandBase {
    private final DrivingSubsystem exampleSubsystem;
    private final Joystick joystick;

    public DrivingTeleopCommand(DrivingSubsystem exampleSubsystem, Joystick joystick) {
        this.exampleSubsystem = exampleSubsystem;
        this.joystick = joystick;
        addRequirements(exampleSubsystem);
    }

    /**
     * This is called once for each time the command starts
     */
    @Override
    public void initialize() {}

    /**
     * Called when {@link #isFinished()} returns true
     * @param interrupted A boolean to say whether the command was interrupted by another command or explicitly cancelled
     */
    @Override
    public void end(boolean interrupted) {}

    /**
     * @return If the command is finished executing
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void execute() {
        exampleSubsystem.arcadeDrive(
                joystick.getX() * Constants.MOTOR_POWER_PERCENT,
                joystick.getY() * Constants.MOTOR_POWER_PERCENT
        );
    }
}
