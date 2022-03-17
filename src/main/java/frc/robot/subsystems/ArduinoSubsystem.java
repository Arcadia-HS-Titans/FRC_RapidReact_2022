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

    public PixyPacket read() {
        if (!send) {
            return new PixyPacket();
        }
        // Get input from serial line and add to StringBuilder
        stringBuilder.append(arduino.readString());

        // Check if we should have stopped and sent a packet
        int index = stringBuilder.indexOf("EOP");
        if(index == -1)
            return new PixyPacket(); // We're still sending information, so keep on appending and return nothing
        String result = stringBuilder.substring(0, index);
        if(result.length() == 0) {
            stringBuilder.replace(0, index+packetStop.length(), "");
            return new PixyPacket();
        }
        String[] data_ = result.split("\\|");
        List<Double> data = new ArrayList<>();
        data.add(Double.parseDouble(data_[0]));
        data.add(Double.parseDouble(data_[1]));
        data.add(Double.parseDouble(data_[2]));

        System.out.println(data);
        return new PixyPacket(data);
    }
}
