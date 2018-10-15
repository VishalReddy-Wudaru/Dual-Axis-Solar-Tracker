#include <Servo.h> 
 
Servo myservo1; 
Servo myservo2;
int pos1 = 90;   // initial position
int pos2=90;
int sens1 = A0; // LRD 1 pin
int sens2 = A4; // LDR 2 pin
int sens3 = A2; // LRD 3 pin
int sens4 = A3; // LRD 4 pin
int sens5 = A5; 
const int ledpin1=13;
const int ledpin2=12;
const int ledpin3=7;
const int ledpin4=6;
int tolerance = 50;
 
void setup() 
{ 
  myservo1.attach(9);  
  myservo2.attach(10);
  pinMode(sens1, INPUT);
  pinMode(sens2, INPUT);
  pinMode(sens3, INPUT);
  pinMode(sens4, INPUT);
  pinMode(sens5,INPUT);
  pinMode(ledpin1,OUTPUT);
  pinMode(ledpin2,OUTPUT);
  pinMode(ledpin3,OUTPUT);
  pinMode(ledpin4,OUTPUT);

  
  myservo1.write(pos1);
  myservo2.write(pos2);
      
  Serial.begin(9600);

  delay(2000); // a 2 seconds delay while we position the solar panel
  
}  
 
void loop() 
{ 
  int val1 = analogRead(sens1); // read the value of sensor 1
  int val2 = analogRead(sens2); // read the value of sensor 2
  int val3 = analogRead(sens3); // read the value of sensor 2
  int val4 = analogRead(sens4); // read the value of sensor 2
  int val5=analogRead(sens5);
    
    if(val1>=tolerance)
    {
      digitalWrite(ledpin1,HIGH);
      //Serial.println("ldr1 is in light,led1 is on");
    }
    else
    {
      digitalWrite(ledpin1,LOW);
      //Serial.println("led1 off");
    }
    if(val3>=tolerance)
    {
      digitalWrite(ledpin2,HIGH);
      //Serial.println("ldr3 is in light,led2 is on");
    }
    else
    {
      digitalWrite(ledpin2,LOW);
      //Serial.println("led2 off");
    }
    if(val4>=tolerance)
    {
      digitalWrite(ledpin3,HIGH);
      //Serial.println("ldr4 is in light,led3 is on");
    }
    else
    {
      digitalWrite(ledpin3,LOW);
      //Serial.println("led3 off");
    }
    
    int x=5;

    

  
   
  if( abs((val4 - val3)) <= tolerance && abs((val4 - val1)) <= tolerance) {
    //do nothing if the difference between values is within the tolerance limit
    
    Serial.print('#');
    Serial.print(val1);
    Serial.print("+");
    Serial.print(val2);
    Serial.print("+");
    Serial.print(val3);
    Serial.print("+");
    Serial.print(val4);
    Serial.print("+");
    //Serial.print(val5);
    //Serial.print("+");
    //Serial.print("~");
    
  }
  else 
   {  

    if(abs((val4 - val3)) > tolerance)
    {  
    if(val4 > val3)
    {
      pos1 = pos1-x;
    }
    else
    {
      pos1 = pos1+x;
      
    }
    }
    

    
    if(abs((val4 - val1)) > tolerance)
    {
    if(val1 > val4)
    {
      pos2 = pos2-x;
    }
    else
    {
      pos2 = pos2+x;
      
    }
    }
    
    Serial.print('#');
    Serial.print(val1);
    Serial.print("+");
    Serial.print(val2);
    Serial.print("+");
    Serial.print(val3);
    Serial.print("+");
    Serial.print(val4);
    Serial.print("+");
    
  }
   
  
 
  if(pos1 > 360) 
  { 
    pos1 = 360;
   
    } // reset to 360 if it goes higher
  if(pos1 < 0)
  { 
    pos1 = 0;
   
    } // reset to 0 if it goes lower


    if(pos2 > 270) 
  { 
    pos2 = 270;
   
    } // reset to 270 if it goes higher
  if(pos2 < 0)
  { 
    pos2 = 0;
   
    } // reset to 0 if it goes lower
  
  myservo1.write(pos1); // write the position to servo1
  myservo2.write(pos2); // write the position to servo2


  Serial.print(pos1);
  Serial.print('+');
  Serial.print(pos2);
  Serial.print('+');
  Serial.print(val5);
  Serial.print('+');
  Serial.print('~');
 
  delay(3000);
 
  
}

