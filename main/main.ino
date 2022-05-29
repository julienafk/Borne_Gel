#include <HTTPClient.h>
#include <WiFi.h>
#include <ArduinoJson.h>
#include <Ultrasonic.h>

WiFiClient client;
HTTPClient http;
const int BATTERYPIN = 34; //pin de la batterie
const float TensionMin = 1.5; //tension min
const float TensionMax = 3.3; //tension max

const char* ssid = "Eleves";  // Mettre votre SSID Wifi
const char* password = "ml$@0931584S";

int getBattery() {
  float bat = analogRead(BATTERYPIN);
  int minValue = (4095 * TensionMin) / 3;
  int maxValue = (4095 * TensionMax) / 3;
  bat = ((bat - minValue) / (maxValue - minValue)) * 100; //mettre en pourcentage
  if (bat > 100) { //max is 100%
    bat = 100;
  }
  else if (bat < 0) { //min is 0%
    bat = 0;
  }
  int valeur = bat;
  return bat;
}

Ultrasonic ultrasonic(6);
void setup() {
  Serial.begin(115200);   // Initialisation du moniteur série à 115200
  delay(1000);
  Serial.println("\n");
  WiFi.begin(ssid, password); // Initialisation avec WiFi.begin / ssid et password
  Serial.print("Attente de connexion ...");  // Message d'attente de connexion
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");  // Affiche des points .... tant que connexion n'est pas OK
    delay(1000);
  }
  Serial.println("\n");
  Serial.println("Connexion etablie !");  // Affiche connexion établie
  Serial.print("Adresse IP: ");
  Serial.println(WiFi.localIP());  // Affiche l'adresse IP de l'ESP32 avec WiFi.localIP

}
void loop() {
  
   esp_sleep_enable_timer_wakeup(5000000);//sleep mode pendant 5 secondes
   //esp_light_sleep_start();
  
  //**********************************************************************************************
  int Distance = ultrasonic.read();
  int valGel = 0;

  if (Distance < 2) {
    Serial.print("**Rempli**");
    Serial.println(Distance);
    valGel = Distance;
  }
  else if (Distance > 2 && Distance < 10) {
    Serial.print("**Niveau Bien**");
    Serial.println(Distance);
    valGel = Distance;
  }
  else if (Distance > 10 && Distance < 15) {
    Serial.print("**Niveau Bas**");
    Serial.println(Distance);
    valGel = Distance;
  }
  else if (Distance > 15) {
    Serial.print("**Niveau Alerte**");
    Serial.println(Distance);
    valGel = Distance;
  }
  else {
    Serial.print("**LOIN**");
  }
  delay(1000);

  //******************************************************************************************************
  int valBat = getBattery();

  DynamicJsonDocument doc(204);
  doc["ESP"] = 1;
  doc["nivBatterie"] = valBat;
  doc["nivGel"] = valGel;
  String MonJson;
  serializeJson(doc, MonJson);

  http.begin(client, "**API-link**");//Connection à L'API
  int Statut = http.GET();
  Serial.print("Statut en attente ...");
  while (Statut != 200) {
    Serial.print(".");// Affiche des points . tant que statut n'est pas 200
    delay(50);
  }
  Serial.println("\n");
  Serial.println("**ENVOYER**");
  http.POST(MonJson);//Envoie du JSON
  DeserializationError error = deserializeJson(doc, http.getString());// Réponse de l'API sous JSON et on deserialize
  if (error) {
    Serial.print("deserializeJson() failed: ");
    Serial.println(error.c_str());// On affiche une erreur
    return;
  }
  if (doc["succes"] == true) {
    Serial.print("Parfait!! ");
  }
  delay(4000);
  http.end();
}

//bool succes = doc["succes"]; // true
//*******************************************************************************
/*http.begin("link");
  http.GET();
  DeserializationError error = deserializeJson(doc, http.getString());
  if (error) {
  Serial.print("deserializeJson() failed: ");
  Serial.println(error.c_str());
  return;
  }
  Serial.println(doc["heure"]);//heure API*/
//best site ptsv2.com//best site ptsv2.com
//best site ptsv2.com
