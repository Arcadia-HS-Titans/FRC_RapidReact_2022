package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArduinoSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DrivingSubsystem;
import frc.robot.subsystems.EncoderSubsystem;

public class AutoCommand extends CommandBase {
    private ArduinoSubsystem arduinoSubsystem;
    private ColorSensorSubsystem colorSensorSubsystem;
    private DrivingSubsystem drivingSubsystem;
    private EncoderSubsystem encoderSubsystem;

    private int rpm = 0; // Counts of encoder pulses

    public AutoCommand(ArduinoSubsystem arduinoSubsystem, ColorSensorSubsystem colorSensorSubsystem,
                       DrivingSubsystem drivingSubsystem, EncoderSubsystem encoderSubsystem) {
        this.arduinoSubsystem = arduinoSubsystem;
        this.colorSensorSubsystem = colorSensorSubsystem;
        this.drivingSubsystem = drivingSubsystem;
        this.encoderSubsystem = encoderSubsystem;
        addRequirements(drivingSubsystem);
        addRequirements(colorSensorSubsystem);
        addRequirements(arduinoSubsystem);
        addRequirements(encoderSubsystem);
    }

    /**
     * This is called once for each time the command starts
     */
    @Override
    public void initialize() {
        rpm = 0;
    }

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
        rpm += encoderSubsystem.getLeftEncoder().getDistancePerPulse();
        if(rpm < 150) {
            DriverStation.reportWarning(String.valueOf(rpm), false);
            drivingSubsystem.arcadeDrive(0, -0.7);
            return;
        }

        // TODO: Check ball and shoot mechanism
    }
}
