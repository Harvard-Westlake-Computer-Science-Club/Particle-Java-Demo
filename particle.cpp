#include <neopixel.h>
#include "Particle.h"

// Initialize our Neopixel ring
Adafruit_NeoPixel ring(16, D6);

void setup() {
    ring.begin();
    
    // Tell the Particle Cloud about our function. This will say "when I receive a request with the name changeColor, I'll call the changeColor function"
    Particle.function("changeColor", changeColor);
}

int changeColor(String color) {
    
    int red = 0;
    int green = 0;
    int blue = 0;
    
    if(color == "red") {
        red = 255;
        green = 0;
        blue = 0;
    } else if(color == "green") {
        red = 0;
        green = 255;
        blue = 0;
    } else if(color == "blue") {
        red = 0;
        green = 0;
        blue = 255;
    }
    
    for(int i = 0; i < ring.numPixels(); i++){
        ring.setPixelColor(i, ring.Color(red, green, blue));
    }
    ring.show();
    return 0;
}
