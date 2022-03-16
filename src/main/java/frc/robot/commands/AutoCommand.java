package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class AutoCommand extends CommandBase {
    private ArduinoSubsystem arduinoSubsystem;
    private ColorSensorSubsystem colorSensorSubsystem;
    private DrivingSubsystem drivingSubsystem;
    private BallShooterSubsystem ballShooterSubsystem;
    private IntakeSubsystem intakeSubsystem;

    private int rpm = 0; // Counts of encoder pulses

    public AutoCommand(/*ArduinoSubsystem arduinoSubsystem*/ /*ColorSensorSubsystem colorSensorSubsystem,*/
                       DrivingSubsystem drivingSubsystem, /*EncoderSubsystem encoderSubsystem,*/
                       BallShooterSubsystem ballShooterSubsystem, IntakeSubsystem intakeSubsystem) {
        //this.arduinoSubsystem = arduinoSubsystem;
        //this.colorSensorSubsystem = colorSensorSubsystem;
        this.drivingSubsystem = drivingSubsystem;
        //this.encoderSubsystem = encoderSubsystem;
        this.ballShooterSubsystem = ballShooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(drivingSubsystem);
        //addRequirements(colorSensorSubsystem);
        //addRequirements(arduinoSubsystem);
        //addRequirements(encoderSubsystem);
        addRequirements(ballShooterSubsystem);
        addRequirements(intakeSubsystem);
    }

    /**
     * This is called once for each time the command starts
     */
    @Override
    public void initialize() {
        rpm = 0;
        timer = new Timer();
        timer.reset();;
        timer.start();
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

    private Timer timer;

    @Override
    public void execute() {
        //intakeSubsystem.setSpeed(1);
        if(timer.get() < 5) {
            ballShooterSubsystem.fire(1);
            return;
        } else {
            ballShooterSubsystem.fire(0);
        }
        if(timer.get() < 6.5) {
            drivingSubsystem.arcadeDrive(0f, -.7f);
        }
    }
}
