package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensorSubsystem extends SubsystemBase {
    private final ColorSensorV3 colorSensor = new ColorSensorV3(Port.kOnboard);

    public ColorSensorSubsystem() {
        // How to initialize for later: Use an IC2 port: I2cPort.kOnboard
        // https://github.com/REVrobotics/Color-Sensor-v3-Examples/blob/master/Java/Read%20RGB%20Values/src/main/java/frc/robot/Robot.java
        //https://youtu.be/dQw4w9WgXcQ
        // https://www.revrobotics.com/rev-31-1557/ Color sensor page
    }

    public Color getColor() {
        return colorSensor.getColor();
    }

}
