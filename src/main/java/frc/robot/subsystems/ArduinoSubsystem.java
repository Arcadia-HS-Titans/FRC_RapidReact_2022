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

    private StringBuilder stringBuilder = new StringBuilder("");
    private final String packetStop = "STOPPACKETSENDING"; // TODO: Change this later to something like EOP, end of packet
    public String read() {
        // Get input from serial line and add to StringBuilder
        String read = arduino.readString();
        stringBuilder.append(read);
        // Check if we should have stopped and sent a packet
        int index = stringBuilder.indexOf(packetStop);
        if(index == -1)
            return ""; // We're still sending information, so keep on appending and return nothing
        // The 17 is amount of characters STOPPACKETSENDING
        String result = stringBuilder.substring(0, index+packetStop.length());
        // Start the stringbuilder from where it left off
        stringBuilder = new StringBuilder(stringBuilder.substring(index+packetStop.length()));
        return result;
    }
}
