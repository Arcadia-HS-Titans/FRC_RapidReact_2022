/*void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available()) {
    Serial.write("Hello you stupid bot please I beg you work");
  }
}*/

#include <Wire.h>

//built in class from arduino, strongly suggest looking at it on their website
//SETUP THE DEVICES

//plug sda on RoboRIO into A4
//plug scl on RoboRIO into A5
//connect the two grounds


String piOutput = "none";//string to be sent to the robot
String input = "blank";  //string received from the robot

void setup(){
  Serial.begin(9600);
  Serial.println("Started");
  Wire.begin(4);                // join i2c bus with address #4 as a slave device
  Wire.onReceive(receiveEvent); // Registers a function to be called when a slave device receives a transmission from a master
  Wire.onRequest(requestEvent); // Register a function to be called when a master requests data from this slave device
}

void loop(){
  piOutput = "HLELOEOEOEOEOEOE";
  Serial.println(piOutput);

  Wire.write(piOutput.c_str());

  delay(70); //gives time for everything to process
}

void requestEvent(){//called when RoboRIO request a message from this device
  Serial.println("Sent data");
  Wire.write(piOutput.c_str()); //writes data to the RoboRIO, converts it to string
}

void receiveEvent(int bytes){//called when RoboRIO "gives" this device a message
  Serial.println(":)");
}
