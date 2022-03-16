package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class TestCommand extends CommandBase {
    private ArduinoSubsystem arduinoSubsystem;
    private ColorSensorSubsystem colorSensorSubsystem;
    private DrivingSubsystem drivingSubsystem;
    private EncoderSubsystem encoderSubsystem;
    private BallShooterSubsystem ballShooterSubsystem;
    private IntakeSubsystem intakeSubsystem;

    public TestCommand(ArduinoSubsystem arduinoSubsystem,
                       /*ColorSensorSubsystem colorSensorSubsystem,*/
                       EncoderSubsystem encoderSubsystem,
                       BallShooterSubsystem ballShooterSubsystem) {
        this.arduinoSubsystem = arduinoSubsystem;
        /*this.colorSensorSubsystem = colorSensorSubsystem;*/
        this.encoderSubsystem = encoderSubsystem;
        this.ballShooterSubsystem = ballShooterSubsystem;
        addRequirements(arduinoSubsystem);
        /*addRequirements(colorSensorSubsystem);*/
        addRequirements(encoderSubsystem);
        addRequirements(ballShooterSubsystem);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println(arduinoSubsystem.read());
        ballShooterSubsystem.turnSusanRaw(1);
        ballShooterSubsystem.turnSusanRaw(-1);
    }
}