package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArduinoSubsystem extends SubsystemBase {
    private SerialPort arduino;

    private static final int MAX_BYTES = 32;

    public ArduinoSubsystem() {
        try {
            this.arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
        } catch (Exception e) {
            DriverStation.reportError("Arduino could not connect: ", e.getStackTrace());
        }

    }

    public void write(String input){//writes to the arduino
        char[] CharArray = input.toCharArray();//creates a char array from the input string
        byte[] WriteData = new byte[CharArray.length];//creates a byte array from the char array
        for (int i = 0; i < CharArray.length; i++) {//writes each byte to the arduino
            WriteData[i] = (byte) CharArray[i];//adds the char elements to the byte array
        }
        //arduino.transaction(WriteData, WriteData.length, null, 0);//sends each byte to arduino
    }

/*
    public PixyPacket getPixy(){//reads the data from arduino and saves it
        String info[] = read().split("\\|");//everytime a "|" is used it splits the data,
        //and adds it as a new element in the array
        PixyPacket pkt = new PixyPacket();  //creates a new packet to hold the data
        if(info[0].equals("none") || info[0].equals("")){//checks to make sure there is data
            pkt.x = -1;//the x val will never be -1 so we can text later in code to make sure
            //there is data
            pkt.y = -1;
            pkt.area = -1;
        }else if(info.length == 3){//if there is an x, y, and area value the length equals 3
            pkt.x = Double.parseDouble(info[0]);//set x
            pkt.y = Double.parseDouble(info[1]);//set y
            pkt.area = Double.parseDouble(info[2]);//set area
        }
        return pkt;
    }
*/

    public String read(){//function to read the data from arduino
        //byte[] data = new byte[MAX_BYTES];//create a byte array to hold the incoming data
        //arduino.read(4, MAX_BYTES, data);//use address 4 on i2c and store it in data
        //String output = new String(data);//create a string from the byte array
        //int[] information = new int[]{1, 3, 65,2,5,2,8,3,8,876,4,3,2,46,7,89,876543,2,22,34,43,};

        return "";//return output;
/*        int pt = output.indexOf((char)255);
        return (String) output.subSequence(0, Math.max(pt, 0));//im not sure what these last two lines do*/
        //sorry :(
    }
}
