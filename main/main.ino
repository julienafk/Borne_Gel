#include <Ultrasonic.h>
#include <ArduinoJson.h>

//const int Port = 6;
Ultrasonic ultrasonic(6);
int Distance;
void setup() {
  Serial.begin(9600);
}

void loop() {
  Distance = ultrasonic.read();
  /*
  if(Distance < 10){//Capteur 1
    Serial.println("****Distribution du gel****");//action de distribuer gel
    delay(3500);// temps d'attente avant redistrubution
  }
  delay(1000);*/
  /*
  //if(Distance < 50){//Capteur 12
    if(Distance < 2){
      Serial.print("**Rempli**");
      Serial.println(Distance);
    }
     if(Distance > 2 && Distance < 10){
      Serial.print("**Bien**");
      Serial.println(Distance);
    }
     if(Distance > 10 && Distance < 15){
      Serial.print("**Niveau Bas**");
      Serial.println(Distance);
    }
     if(Distance > 15){
      Serial.print("**Attention**");
      Serial.println(Distance);
    }
  delay(1000);
*/
 StaticJsonDocument<200>doc;
  doc["ESP"] = 1;
  doc["nivBatterie"] = 100;
  doc["nivGel"] = "Distance";

  serializeJson(doc, Serial);
  //serializeJsonPretty(doc, Serial);

}
//}


/*
  //Serial.print("Distance in CM: ");
  //Serial.println(Distance);
  //delay(1000);
Genre quand je met ma main il detect (genre elle est a moin de 10 cm elle fais l'action de ditrubuer gel)
  -  le capteur detct si le liquide est au plus loiin 15cm (distance forte donc niv bah elle envoie l'alert)
  - envoie json
*/ 
