// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems
    private final DrivingSubsystem drivingSubsystem; // 4 PWM motors
    private ArduinoSubsystem arduinoSubsystem; // USB on RoboRIO
    //private ColorSensorSubsystem colorSubsystem; // I2C port on RIO
    private final BallShooterSubsystem ballShooterSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private ClimbingSubsystem climbingSubsystem;

    // Devices
    public final Joystick joystick;

    // The main commands of the robot
    private final Command teleopCommand;
    private final Command autoCommand;


    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {

        // Starts up a camera
        CameraServer.startAutomaticCapture();
        CameraServer.startAutomaticCapture(1);

        Shuffleboard.addEventMarker("A", "a", EventImportance.kNormal);
        this.joystick = new Joystick(0);
        this.drivingSubsystem = new DrivingSubsystem();
        //this.colorSubsystem = new ColorSensorSubsystem();
        this.ballShooterSubsystem = new BallShooterSubsystem();
        this.intakeSubsystem = new IntakeSubsystem();
        //this.arduinoSubsystem = new ArduinoSubsystem();
        this.climbingSubsystem = new ClimbingSubsystem();
        this.teleopCommand = new DrivingTeleopCommand(
                drivingSubsystem, joystick /*colorSubsystem,*//*arduinoSubsystem*/, ballShooterSubsystem,
                intakeSubsystem, climbingSubsystem);
        this.autoCommand = new AutoCommand(/*arduinoSubsystem*/ /*colorSubsystem,*/ drivingSubsystem, ballShooterSubsystem, intakeSubsystem);
        // Configure default commands
        // Set the default drive command to split-stick arcade drive
        drivingSubsystem.setDefaultCommand(teleopCommand);

        // Add commands to the autonomous command chooser
        m_chooser.setDefaultOption("TeleOperated", teleopCommand);
        m_chooser.addOption("Autonomous", autoCommand);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add(m_chooser);
        Shuffleboard.getTab("TeleOperated").add(m_chooser);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return autoCommand;
    }

    public Command getTeleopCommand() {
        return teleopCommand;
    }
}