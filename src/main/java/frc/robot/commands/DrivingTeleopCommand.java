package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArduinoSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DrivingSubsystem;

import java.util.Arrays;

/**
 * https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html#simple-command-example
 * https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/hatchbottraditional/commands/DefaultDrive.java
 */
public class DrivingTeleopCommand extends CommandBase {
    private final DrivingSubsystem exampleSubsystem;
    private final ColorSensorSubsystem colorSensorSubSystem;
    private final ArduinoSubsystem arduinoSubsystem;
    private final Joystick joystick;

    public DrivingTeleopCommand(DrivingSubsystem exampleSubsystem, Joystick joystick, ColorSensorSubsystem colorSensorSubSystem, ArduinoSubsystem arduinoSubsystem) {
        this.exampleSubsystem = exampleSubsystem;
        this.colorSensorSubSystem = colorSensorSubSystem;
        this.joystick = joystick;
        this.arduinoSubsystem = arduinoSubsystem;
        addRequirements(exampleSubsystem);
        addRequirements(colorSensorSubSystem);
        addRequirements(arduinoSubsystem);
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
        String result = arduinoSubsystem.read();
        if(!result.equals("") && !result.equals(" "))
            DriverStation.reportWarning("THIS IS THE RESULT: " + result, false);
        exampleSubsystem.arcadeDrive(
                joystick.getX() * Constants.MOTOR_POWER_PERCENT,
                joystick.getY() * Constants.MOTOR_POWER_PERCENT
        );
    }
}
