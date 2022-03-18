package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.ArrayList;
import java.util.List;

public class ArduinoSubsystem extends SubsystemBase {
    private SerialPort arduino;
    private Boolean send = true;

    public ArduinoSubsystem() {
        try {
            this.arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
        } catch (Exception e) {
            DriverStation.reportError("Arduino could not connect: ", e.getStackTrace());
        }
    }

    private StringBuffer stringBuilder = new StringBuffer();
    private static final String packetStop = "EOP"; // End Of Packet

    public final class PixyPacket {
        public double x;
        public double y;
        public double scale;

        public PixyPacket(double x, double y, double scale) {
            this.x = x;
            this.y = y;
            this.scale = scale;
        }

        public PixyPacket(List<Double> list) {
            this.x = list.get(0);
            this.y = list.get(1);
            this.scale = list.get(2);
        }

        public PixyPacket() {
            this.x = 0;
            this.y = 0;
            this.scale = 0;
        }
    }

    public void sendPackets(Boolean s) {
        send = false;
    }

    public String read() {
/*        // Get input from serial line and add to StringBuilder
        stringBuilder.append(arduino.readString());

        // Check if we should have stopped and sent a packet
        int index = stringBuilder.indexOf("EOP");
        if(index == -1) {
            return ""; // We're still sending information, so keep on appending and return nothing
        }
        String result = stringBuilder.substring(0, index);
        if(result.length() == 0) {
            stringBuilder.replace(0, index+packetStop.length(), "");
            return "";
        }
        return result;*/
        // Get input from serial line and add to StringBuilder
        stringBuilder.append(arduino.readString());

        // Check if we should have stopped and sent a packet
        int index = stringBuilder.indexOf(".");
        if(index == -1) {
            //We don't have 1 full packet, do we have EOPs?
            index = stringBuilder.indexOf("EOP");
            if(index == -1) {
                return ""; // We're still sending information, so keep on appending and return nothing
            }
            //There are EOPs, clear them from the list
            stringBuilder.replace(index, index+packetStop.length(), "");
            return "";
        }
        //If we have at least 1 full packet
        //stringBuilder.

        String result = stringBuilder.substring(0, index);
        if(result.length() == 0) {
            stringBuilder.replace(0, index+packetStop.length(), "");
            return "";
        }
        return result;
    }
}
