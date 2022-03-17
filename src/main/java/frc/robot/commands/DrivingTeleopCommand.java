package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.*;

import java.util.List;

/**
 * https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html#simple-command-example
 * https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/hatchbottraditional/commands/DefaultDrive.java
 */
public class DrivingTeleopCommand extends CommandBase {

    private final Joystick joystick;
    private final Joystick gamepad;

    private final DrivingSubsystem drivingSubsystem;
    //private final ColorSensorSubsystem colorSensorSubSystem;
    private final ArduinoSubsystem arduinoSubsystem;
    private final BallShooterSubsystem ballShooterSubsystem;
    private final IntakeSubsystem intakeSubsystem;

    public DrivingTeleopCommand(
            DrivingSubsystem drivingSubsystem, Joystick joystick, /*ColorSensorSubsystem colorSensorSubSystem,*/
            ArduinoSubsystem arduinoSubsystem, BallShooterSubsystem ballShooterSubsystem,
            IntakeSubsystem intakeSubsystem) {
        this.drivingSubsystem = drivingSubsystem;
        //this.colorSensorSubSystem = colorSensorSubSystem;
        this.joystick = joystick;
        this.gamepad = new Joystick(1);
        this.arduinoSubsystem = arduinoSubsystem;
        this.ballShooterSubsystem = ballShooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(drivingSubsystem);
        //addRequirements(colorSensorSubSystem);
        addRequirements(arduinoSubsystem);
        addRequirements(ballShooterSubsystem);
        addRequirements(intakeSubsystem);
    }

    /**Logitech contro
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
        DriverStation.reportWarning(String.valueOf(ballShooterSubsystem.read()), false);
        drivingSubsystem.arcadeDrive(
                joystick.getZ() * Constants.MOTOR_POWER_PERCENT,
                joystick.getY() * Constants.MOTOR_POWER_PERCENT
        );

        double value = joystick.getRawAxis(3);
        if(value < 0) value *= -1;
        DriverStation.reportWarning(String.valueOf(value), false);
        if(gamepad.getRawButton(1)) {
            //B on the logitech controller is pressed, taken from GLFW
            ballShooterSubsystem.fire(value);
            intakeSubsystem.elevateBall(1);
        } else {
            ballShooterSubsystem.fire(0);
            intakeSubsystem.setSpeed(0);
        }

        if(gamepad.getRawAxis(2) > .5)
            intakeSubsystem.enterBall(1);
        else {
            intakeSubsystem.enterBall(0);
        }

        if(gamepad.getRawAxis(0) != 0) {
            ballShooterSubsystem.turnSusan(gamepad.getRawAxis(0));
        }
        //Log velocity
        ArduinoSubsystem.PixyPacket read = arduinoSubsystem.read();
        if(!(read.x == 0 && read.y == 0 && read.scale == 0))
            if (read.scale <= 150) {
                if (read.x >= 0.5) {
                    ballShooterSubsystem.turnSusan(-(read.x+1));
                } else {
                    ballShooterSubsystem.turnSusan(read.x+1);
                }
                System.out.println(new double[]{read.x, read.y, read.scale});
            } else {
                System.out.println(new double[]{read.x, read.y, read.scale});
            }
        System.out.println(ballShooterSubsystem.getVelocity());
    }
}
