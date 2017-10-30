
char input;

    int cont;
    int initialDot;
    int endDot;
    int initialRepresentation;
    char representation [12];
    int contArray;
// the setup function runs once when you press reset or power the board
void setup() {
  initialDot=23;
  contArray = 0;
  endDot=53;
  // initialize digital pin LED_BUILTIN as an output.
  //primera representación
  pinMode(23, OUTPUT);
  pinMode(25, OUTPUT);
  pinMode(27, OUTPUT);
  pinMode(29, OUTPUT);
  pinMode(31, OUTPUT);
  pinMode(33, OUTPUT);
  //segunda representación
  pinMode(43, OUTPUT);
  pinMode(45, OUTPUT);
  pinMode(47, OUTPUT);
  pinMode(49, OUTPUT);
  pinMode(51, OUTPUT);
  pinMode(53, OUTPUT);
  //botones para pasar las letras
  pinMode(50, INPUT);
  pinMode(52, INPUT);
  Serial.begin(9600);
  cont=0;
}

// the loop function runs over and over again forever
void loop() {
  if(Serial.available()>0){
    
  
    
    if(initialRepresentation == 35){
      cont += 8;
    }
    
    initialRepresentation = initialDot+cont;
    input=Serial.read();
        if(input=='1'){
            digitalWrite(initialRepresentation, HIGH);  
        }
        else
        {
            digitalWrite(initialRepresentation, LOW);  
        }
        cont += 2;
    
        if(initialRepresentation > 32 && initialRepresentation < 36){
          cont += 8;
        }
    
        
        if(initialRepresentation >= 53){
          cont=0;
        }
    }
        if(digitalRead(50)== HIGH){
        Serial.println("Letra anterior"); 
        delay(1000);
        }
        
        if(digitalRead(52)== HIGH){
        Serial.println("Letra siguiente"); 
        delay(1000); 
        }

           
}
