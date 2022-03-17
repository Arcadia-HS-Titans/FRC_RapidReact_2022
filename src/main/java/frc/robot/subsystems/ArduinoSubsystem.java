package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArduinoSubsystem extends SubsystemBase {
    private SerialPort arduino;

    public ArduinoSubsystem() {
        try {
            this.arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
        } catch (Exception e) {
            DriverStation.reportError("Arduino could not connect: ", e.getStackTrace());
        }
    }

    private StringBuffer stringBuilder = new StringBuffer();
    private static final String packetStop = "EOP"; // End Of Packet

    public String read() {
        // Get input from serial line and add to StringBuilder
        stringBuilder.append(arduino.readString());

        // Check if we should have stopped and sent a packet
        int index = stringBuilder.indexOf("EOP");
        if(index == -1)
            return ""; // We're still sending information, so keep on appending and return nothing
        String result = stringBuilder.substring(0, index);
        if(result.length() == 0) {
            stringBuilder.replace(0, index+packetStop.length(), "");
            return "";
        }
        stringBuilder.replace(0, index+packetStop.length(), "");
        return result;
    }
}
