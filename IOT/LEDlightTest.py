import RPi.GPIO as GPIO
from rpi_ws281x import *
import board
import neopixel
import time
from gpiozero import DistanceSensor
GPIO.setmode(GPIO.BCM)
import math
sensor = DistanceSensor(echo=24, trigger=23 ) 


# LED strip configuration:
LED_COUNT      = 24      # Number of LED pixels.
# LED_PIN        = 10       # GPIO pin connected to the pixels (18 uses PWM!).
LED_PIN        = 10      # GPIO pin connected to the pixels (10 uses SPI /dev/spidev0.0).
LED_FREQ_HZ    = 800000  # LED signal frequency in hertz (usually 800khz)
LED_DMA        = 100      # DMA channel to use for generating signal (try 10)
LED_BRIGHTNESS = 0     # Set to 0 for darkest and 255 for brightest
# LED_INVERT     = False   # True to invert the signal (when using NPN transistor level shift)
LED_CHANNEL    = 1       # set to '1' for GPIOs 13, 19, 41, 45 or 53

pixels = neopixel.NeoPixel(board.D10, 24)

if __name__ =='__main__':
    try:

        while True:
            pixels.fill((0,0,0))

            Dist= sensor.value
            print(Dist)
            print(f'Distance {sensor.value :1.2f} M')
            if Dist < 0.37:
                index = math.floor(LED_COUNT*Dist)
                for i in range(index):
                    pixels[i] = (255,255,0)
            elif Dist > 0.37 and Dist < 0.62:
                index = math.floor(LED_COUNT*Dist)
                for i in range(index):
                    pixels[i] = (0,255,0)
            else:
                index = math.floor(LED_COUNT*Dist)
                for i in range(index):
                    pixels[i] = (255,0,0)
            pixels.fill((0,0,0))
            time.sleep(0.0001)

#         
        
    except KeyboardInterrupt:
        pixels.fill((0,0,0))
        print("ending")
        GPIO.cleanup()
        exit()