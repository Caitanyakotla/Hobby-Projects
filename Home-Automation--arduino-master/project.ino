#include <SPI.h>
#include <Ethernet.h> // Initialize the libraries.
#include <Servo.h>
Servo myservo;
byte mac[] = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 }; //I left the MAC address and IP address
blank.
byte ip[] = { 192, 168, 0, 177 }; // You will want to fill these in with your MAC and
IP address.
EthernetServer server(80); // Assigning the port forwarded number. It's almost always 80.
String readString; // We will be using strings to keep track of things.
int val; // Using val as a variable for the PIR status.
int pir=3;
int LDR = 0; //analog pin to which LDR is connected, here we set it to 0 so it means A0
int LDRValue = 0; //that’s a variable to store LDR values
int light_sensitivity = 500; //This is the approx value of light surrounding your LDR
const int analogInPin = A1; // Analog input pin that the potentiometer is attached to
const int ledPin = 12; // LED connected to digital pin 12
int sensorValue = 0;
//////////////////////
void setup(){
pinMode(2, OUTPUT);
pinMode(6, OUTPUT);
pinMode(7, OUTPUT);
pinMode(pir, INPUT);
pinMode(13, INPUT);
Ethernet.begin(mac, ip);
myservo.attach(9);
myservo.write(45);
pinMode(ledPin, OUTPUT);
}
void loop(){
EthernetClient client = server.available();
if (client) {
while (client.connected()) {
if (client.available()) { // This is all where we start up the server and strings.
char c = client.read();
if (readString.length() < 100) {
readString += c;
}
if (c == '\n') {
Serial.println(readString); // And here we begin including the HTML
client.println("HTTP/1.1 200 OK");
client.println("Content-Type: text/html");
client.println();
client.println("<hmtl>");
client.println("<head>");
client.println("ARDUINO PAGE");
client.println("</head>");
client.println("<title>");
client.println("home automation using ethernet");
client.println("</title>");
client.println("<body bgcolor=black>");
client.println("<font color=white>");
client.println("<meta http-equiv=\"refresh\" content=\"4\">"); // This is used to refresh the page
so
client.println("<center>"); // we can see if there is Motion or not.
client.println("<b>");
client.println("Greetings! Here you will find a somewhat interactive page served up by my
Arduino!");
client.println("</br>");
client.println("When you press a button, you turn on or off an LED or move a Servo! Have fun!
");
client.println("</br>");
client.println("ECE IIMDP FINAL YEAR PROJECT");
client.println("</b>");
client.println("<p>");
client.println("<table border=0 width=200>");
client.println("<tr>");
client.println("<td align=center>");
client.println("<font color=white>");
client.println("The Temperature is:");
client.println("</td>");
client.println("</tr>");
client.println("<tr>");
client.println("<td align=center>");
client.println("<font color = turquoise size=10>");
int temp = (((5*analogRead(5)*100/1024))); // This replaces the 00 with a temperature in F.
client.println(temp);
client.println("* C");
client.println("</td>");
client.println("</tr>");
client.println("</table>");
client.println("<p>");
client.println("<FORM>");
client.println("<INPUT type=button value=LED1-ON onClick=window.location='/?
lighton1\'>");
client.println("<INPUT type=button value=LED1-OFF onClick=window.location='/?
lightoff1\'>");
client.println("</FORM>"); // Above and below you'll see that when we click on a button, it
adds a little snippet
client.println("<FORM>");
client.println("<INPUT type=button value=LED2-ON onClick=window.location='/?
lighton2\'>");
client.println("<INPUT type=button value=LED2-OFF onClick=window.location='/?
lightoff2\'>");
client.println("</FORM>"); // Above and below you'll see that when we click on a button, it
adds a little snippet
client.println("<FORM>"); // to the end of the URL. The Arduino watches for this and acts
accordingly.
client.println("<INPUT type=button value=Servo-0 onClick=window.location='/?serv0\'>");
client.println("<INPUT type=button value=Servo-45 onClick=window.location='/?serv45\'>");
client.println("<INPUT type=button value=Servo-90 onClick=window.location='/?serv90\'>");
client.println("</FORM>");
client.print("<table border=1 width=200>");
client.print("<tr>");
client.print("<td align=center>");
client.print("<font color=white size=5>");
client.print("There is currently");
client.print("</td>");
client.print("</tr>");
client.print("<tr>");
client.print("<td align=center>");
client.print("<font color=white size=3>"); // And below we will print Motion if there is, and No
Motion if there's not.
val = digitalRead(pir);
if (val == HIGH){
client.print("MOTION");
digitalWrite(6, HIGH); // turn LED ON
}
else {
client.print("NO MOTION");
digitalWrite(6, LOW); // turn LED OFF
}
client.print("</td>");
client.print("</tr>");
client.print("</table>");
client.print("<table border=1 width=200>");
client.print("<tr>");
client.print("</td>");
client.print("</tr>");
client.print("<tr>");
client.print("<td align=center>");
client.print("<font color=white size=3>"); // And below we will print light if there is, and No
light if there's not.
LDRValue = analogRead(LDR); //reads the ldr’s value through LDR
Serial.println(LDRValue); //prints the LDR values to serial monitor
delay(50); //This is the speed by which LDR sends value to arduino
if (LDRValue < light_sensitivity)
{
client.print(" Light");
}
else
{
client.print("No Light");
}
client.print("</td>");
client.print("</tr>");
client.print("</table>");
client.print("<table border=1 width=200>");
client.print("</td>");
client.print("<tr>");
client.print("<td align=center>");
client.print("<font color=white size=3>"); // And below we will print gas if there is, and No gas
if there's not.
// read the analog in value:
sensorValue = analogRead(analogInPin);
// determine alarm status
if (sensorValue >= 750)
{
digitalWrite(ledPin, HIGH); // sets the LED on
client.print(" No Gas Leakage");
}
else
{
digitalWrite(ledPin, LOW); // sets the LED off
client.print(" Gas Leakage");
}
// print the results to the serial monitor:
Serial.print("sensor = " );
Serial.println(sensorValue);
// wait 10 milliseconds before the next loop
// for the analog-to-digital converter to settle
// after the last reading:
delay(10);
client.print("</td>");
client.print("</tr>");
client.print("</table>");
client.println("</center>");
client.println("</font>");
client.println("</body>");
client.println("</html>");
delay(1);
if(readString.indexOf("?lighton1") >0) // these are the snippets the Arduino is watching
for.
{
digitalWrite(2, HIGH);
}
else{
if(readString.indexOf("?lightoff1") >0)
{
digitalWrite(2, LOW);
}
else{
if(readString.indexOf("?lighton2") >0) // these are the snippets the Arduino is watching
for.
{
digitalWrite(7, HIGH);
}
else{
if(readString.indexOf("?lightoff2") >0)
{
digitalWrite(7, LOW);
}
else{
if(readString.indexOf("?serv0") >0)
{
myservo.write(0);
}
else{
if(readString.indexOf("?serv45") >0)
{
myservo.write(45);
}
else{
if(readString.indexOf("?serv90") >0)
{
myservo.write(90);
71
}
}
}
}
}
}
}
readString="";
client.stop(); // End of session.
}
}
}
}
}
