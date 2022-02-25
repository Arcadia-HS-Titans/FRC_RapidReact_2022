package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArduinoSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DrivingSubsystem;
import frc.robot.subsystems.EncoderSubsystem;

/**
 * https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html#simple-command-example
 * https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/hatchbottraditional/commands/DefaultDrive.java
 */
public class DrivingTeleopCommand extends CommandBase {
    private final DrivingSubsystem exampleSubsystem;
    private final ColorSensorSubsystem colorSensorSubSystem;
    private final ArduinoSubsystem arduinoSubsystem;
    private final Joystick joystick;
    private final EncoderSubsystem encoderSubsystem;

    public DrivingTeleopCommand(
            DrivingSubsystem exampleSubsystem, Joystick joystick, ColorSensorSubsystem colorSensorSubSystem,
            ArduinoSubsystem arduinoSubsystem, EncoderSubsystem encoderSubsystem) {
        this.exampleSubsystem = exampleSubsystem;
        this.colorSensorSubSystem = colorSensorSubSystem;
        this.joystick = joystick;
        this.arduinoSubsystem = arduinoSubsystem;
        this.encoderSubsystem = encoderSubsystem;
        addRequirements(exampleSubsystem);
        addRequirements(colorSensorSubSystem);
        addRequirements(arduinoSubsystem);
        addRequirements(encoderSubsystem);
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
        // Arduino and Pixy recording
        String pixyData = arduinoSubsystem.read();
        if(!pixyData.equals("")) //If we've sent data
            DriverStation.reportWarning(pixyData, false);
        // Encoder recording
        DriverStation.reportWarning(String.valueOf(encoderSubsystem.getLeftEncoder().getRate()),false);
        // Joystick driving
        exampleSubsystem.arcadeDrive(
                joystick.getX() * Constants.MOTOR_POWER_PERCENT,
                joystick.getY() * Constants.MOTOR_POWER_PERCENT
        );
    }
}
