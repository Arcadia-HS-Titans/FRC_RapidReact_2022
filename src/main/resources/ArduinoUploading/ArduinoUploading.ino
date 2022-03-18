#include <SPI.h>
#include <Pixy2.h>
//this is provided by the pixy creators, you will have to go to the arduino sketch editor,
//click sketch, include library, and import the pixy .zip files
#include <Adafruit_NeoPixel.h>
const int neoPixelPin = 7;  // control pin
const int pixelCount = 24;    // number of pixels


String piOutput = "none";//string to be sent to the robot

String input = "blank";   //string received from the robot
Pixy2 pixy;

Adafruit_NeoPixel strip = Adafruit_NeoPixel(pixelCount, neoPixelPin, NEO_GRB + NEO_KHZ800);

void setup(){
  Serial.begin(9600);
  pixy.init();
  strip.begin();    // initialize pixel strip
  strip.clear();    // turn all LEDs off

    // loop over all the pixels:
    for (int pixel = 0; pixel < pixelCount; pixel++) {
      strip.setPixelColor(pixel, 0, 255, 0);
      delay(50);
      strip.show();    // refresh the strip
    }
    strip.clear();
}

void loop(){
  uint16_t blocks = pixy.ccc.getBlocks();//use this line to get every available object the pixy sees
  //^^^not sure what exactly this is for, honestly
  int biggest = -1;
  double area = 0, temp;
  for(int i=0;i<blocks;i++){
    //if(pixy.blocks[i].signature == 3) //if checking for an object and have more than one "type" or color to choose from
                         //use this line and choose the signature or "color" you want
      temp = pixy.ccc.blocks[i].m_width * pixy.ccc.blocks[i].m_height;
      if(temp > area){
        area = temp;
        biggest = i;
      }

      //that loops finds the biggest object since sometimes the object you are looking for becomes
      //broken up into multiple, smaller, objects
  }
  if(!blocks){
    piOutput = "EOP"; //if no blocks tell roborio there are none
  }else{
    piOutput = String(pixy.ccc.blocks[biggest].m_x / 319.0);  //turns into a percent of the screen
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.ccc.blocks[biggest].m_y / 199.0); //319 and 199 were, we found, the dimensions of the screen
    piOutput += "|";
    piOutput += String(area / 64000);
    piOutput += ".";
  }

  Serial.println(piOutput);

  delay(70); //gives time for everything to process
}
