#include <Ultrasonic.h>
#include <ArduinoJson.h>
#include <HTTPClient.h>
#include <WiFi.h>

WiFiClient client;
HTTPClient http;
const char* ssid = "Eleves";  // Mettre votre SSID Wifi
const char* password = "ml$@0931584S";


Ultrasonic ultrasonic(2);
int Distance;
void setup() {
  Serial.begin(115200);   // Initialisation du moniteur série à 115200
  delay(1000);
  Serial.println("\n");
  WiFi.begin(ssid,password);  // Initialisation avec WiFi.begin / ssid et password
  Serial.print("Attente de connexion ...");  // Message d'attente de connexion
  while(WiFi.status() != WL_CONNECTED) 
{
  Serial.print(".");  // Affiche des points .... tant que connexion n'est pas OK
  delay(1000);
}
 Serial.println("\n");
 Serial.println("Connexion etablie !");  // Affiche connexion établie
 Serial.print("Adresse IP: ");
 Serial.println(WiFi.localIP());  // Affiche l'adresse IP de l'ESP32 avec WiFi.localIP
}

void loop() {
  
}
//**********************************************************************************************
int Distance = ultrasonic.read();
/*
  if(Distance < 10){//Capteur 1
    Serial.println("****Distribution du gel****");//action de distribuer gel
    delay(3500);// temps d'attente avant redistrubution
  }
  delay(1000);*/

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

//******************************************************************************************************

DynamicJsonDocument doc(204);
  doc["ESP"] = 1;
  doc["nivBatterie"] = 90;
  doc["nivGel"] = "75%";
String MonJson;
serializeJson(doc, MonJson);

http.begin(client, "**API-link**");//best site ptsv2.com
int Statut = http.GET();
Serial.print("Statut en attente ...");
while(Statut != 200) 
{
  Serial.print("_._");// Affiche des points _._ tant que statut n'est pas 200
  delay(50);
}
Serial.println("\n");
Serial.println("**ENVOYER**");
http.POST(MonJson);
DeserializationError error = deserializeJson(doc, http.getString());
if (error) {
  Serial.print("deserializeJson() failed: ");
  Serial.println(error.c_str());
  return;
}
if(doc["succes"]==true){
    Serial.print("Parfait : ");
}
delay(4000);
//bool succes = doc["succes"]; // true
http.end();
}
