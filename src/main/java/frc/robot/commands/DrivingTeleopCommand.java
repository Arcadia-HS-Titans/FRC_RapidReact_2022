package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.*;

/**
 * https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html#simple-command-example
 * https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/hatchbottraditional/commands/DefaultDrive.java
 */
public class DrivingTeleopCommand extends CommandBase {

    private final Joystick joystick;

    private final DrivingSubsystem drivingSubsystem;
    private final ColorSensorSubsystem colorSensorSubSystem;
    private final ArduinoSubsystem arduinoSubsystem;
    private final EncoderSubsystem encoderSubsystem;
    private final LimitSwitchSubsystem limitSwitchSubsystem;
    private final BallShooterSubsystem ballShooterSubsystem;

    public DrivingTeleopCommand(
            DrivingSubsystem drivingSubsystem, Joystick joystick, ColorSensorSubsystem colorSensorSubSystem,
            ArduinoSubsystem arduinoSubsystem, EncoderSubsystem encoderSubsystem,
            LimitSwitchSubsystem limitSwitchSubsystem, BallShooterSubsystem ballShooterSubsystem) {
        this.drivingSubsystem = drivingSubsystem;
        this.colorSensorSubSystem = colorSensorSubSystem;
        this.joystick = joystick;
        this.arduinoSubsystem = arduinoSubsystem;
        this.encoderSubsystem = encoderSubsystem;
        this.limitSwitchSubsystem = limitSwitchSubsystem;
        this.ballShooterSubsystem = ballShooterSubsystem;
        addRequirements(drivingSubsystem);
        addRequirements(colorSensorSubSystem);
        addRequirements(arduinoSubsystem);
        addRequirements(encoderSubsystem);
        addRequirements(limitSwitchSubsystem);
        addRequirements(ballShooterSubsystem);
        addRequirements(intakeSubsystem);
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
        //ballShooterSubsystem.fire(1);
        // Arduino and Pixy recording
/*
        String pixyData = arduinoSubsystem.read();
        if(!pixyData.equals("")) //If we've sent data
            DriverStation.reportWarning(pixyData, false);
*/
        //TODO: RPM scale seems to be 1.5 ft -> 100 Rotations
        // 180 Rotations -> 1 Wheel Cycle
        //DriverStation.reportWarning(String.valueOf(limitSwitchSubsystem.get()), false);
        drivingSubsystem.arcadeDrive(
                joystick.getX() * Constants.MOTOR_POWER_PERCENT,
                joystick.getY() * Constants.MOTOR_POWER_PERCENT
        );
        if(joystick.getRawButton(1)) {
            //B on the logitech controller is pressed, taken from GLFW
            //TODO: Shoot
            ballShooterSubsystem.fire(0.5);
        }
        if(joystick.getRawAxis(4) > 0.5) {
            // The left trigger is pressed on the logitech gamepad, taken from GLFW again
            //TODO: Intake
            intakeSubsystem.setSpeed(1);
        }

        if(joystick.getRawAxis(0) > .1) {
            ballShooterSubsystem.turnSusan(.7);
        } else if(joystick.getRawAxis(0) < -.1) {
            ballShooterSubsystem.turnSusan(-.7);

        }
        // Listen for gamepad inputs and shoot ball depending on state/start intake
        // Use the mechanical switch and Color Sensor to detect color of a ball
    }
}
