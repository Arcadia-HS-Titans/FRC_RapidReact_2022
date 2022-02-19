// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DrivingTeleopCommand;
import frc.robot.subsystems.ArduinoSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DrivingSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems
    private final DrivingSubsystem m_robotDrive;
    private final ArduinoSubsystem arduinoSubsystem;

    private final ColorSensorSubsystem m_colorSubsystem;

    public final Joystick joystick;

    // The autonomous routines

    // A simple auto routine that drives forward a specified distance, and then stops.
    private final Command m_simpleAuto;


    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        this.joystick = new Joystick(0);
        this.m_robotDrive = new DrivingSubsystem();
        this.m_colorSubsystem = new ColorSensorSubsystem();
        this.arduinoSubsystem = new ArduinoSubsystem();
        this.m_simpleAuto = new DrivingTeleopCommand(m_robotDrive, joystick, m_colorSubsystem, arduinoSubsystem);
        // Configure default commands
        // Set the default drive command to split-stick arcade drive
        m_robotDrive.setDefaultCommand(m_simpleAuto);

        // Add commands to the autonomous command chooser
        m_chooser.setDefaultOption("Simple Auto", m_simpleAuto);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("Autonomous").add(m_chooser);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }
}