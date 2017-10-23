
char input;

    int cont;
// the setup function runs once when you press reset or power the board
void setup() {
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(13, OUTPUT);
  Serial.begin(9600);
  cont=0;
}

// the loop function runs over and over again forever
void loop() {
  if(Serial.available()>0){
    int initialRepresentation=40+cont;
    Serial.print("modificando la salida:");
    Serial.println(initialRepresentation);
    input=Serial.read();
    Serial.print("Entrando el dato:");
    Serial.println(input);
    if(input=='1'){
      Serial.println("HIGH");
        digitalWrite(initialRepresentation, HIGH);  
    }
    else
    {
      
      Serial.println("LOW");
        digitalWrite(initialRepresentation, LOW);  
    }
    cont++;
    if(cont>11){
      cont=0;
    }
  }}
