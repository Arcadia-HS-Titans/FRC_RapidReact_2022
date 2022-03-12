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
    private ArduinoSubsystem arduinoSubsystem; // USB on RoboRIO

    private final Command teleopCommand;

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {

        // Starts up a camera
        CameraServer.startAutomaticCapture();

        Shuffleboard.addEventMarker("A", "a", EventImportance.kNormal);
        this.arduinoSubsystem = new ArduinoSubsystem();
        this.teleopCommand = new DrivingTeleopCommand(arduinoSubsystem);

        // Add commands to the autonomous command chooser
        m_chooser.setDefaultOption("OpsTest", teleopCommand);

        // Put the chooser on the dashboard
        Shuffleboard.getTab("OpsTest").add(m_chooser);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Command getTeleopCommand() {
        return teleopCommand;
    }
}