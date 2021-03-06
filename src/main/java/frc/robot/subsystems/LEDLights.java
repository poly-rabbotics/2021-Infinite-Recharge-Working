/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

/**
 * Add your docs here.
 */
public class LEDLights {

    AddressableLEDBuffer m_ledBuffer;
    AddressableLED m_led;
    int m_rainbowFirstPixelHue = 0;
    int setbrightestone = 0;
    int counter=0;
    boolean blinkeven=false;
    public static int pattern=2;

    public LEDLights() {
        
        m_led = RobotMap.led;
    
        
        // Length is expensive to set, so only set it once, then just update data
        m_ledBuffer = new AddressableLEDBuffer(56);
        m_led.setLength(m_ledBuffer.getLength());
    
        // Set the data
        m_led.setData(m_ledBuffer);
        m_led.start();
      }

      public void run()
      {
         switch(pattern){
             case 1:
                singleColor(0,255,0);
            case 2:
                rainbow();
            case 3:
                up(1);
            case 4:
                down();
            case 5:
                blink(255,0,0);
            case 6:
                blink(0,255,0);

            default:
                singleColor(0,0,0);
         }
 
      }

      void singleColor(int r,int g, int b)
      {
        for (int i = 0; i < 56; i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, r, g, b);
         }
         
         m_led.setData(m_ledBuffer);
        
      }

      private void rainbow() {
        // For every pixel
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          // Calculate the hue - hue is easier for rainbows because the color
          // shape is a circle so only one value needs to precess
          final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
          // Set the value
          m_ledBuffer.setHSV(i, hue, 255, 128);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
        m_led.setData(m_ledBuffer);
      }

      private void up(int speed){
          counter++;
          if(counter%speed==0)
          {
          for (int i=0;i<56;i++){
            m_ledBuffer.setRGB(i, 0, 0, 0);
        
            }

          // (int i=setbrightestone;i<setbrightestone+4;i+=4){
          m_ledBuffer.setRGB(setbrightestone, 25, 25, 0);
          m_ledBuffer.setRGB(setbrightestone+1, 75, 75, 0);
          m_ledBuffer.setRGB(setbrightestone+2, 150, 150, 0);
          m_ledBuffer.setRGB(setbrightestone+3, 255, 255, 0);

          m_ledBuffer.setRGB(55-setbrightestone, 25, 25, 0);
          m_ledBuffer.setRGB(55-setbrightestone-1, 75, 75, 0);
          m_ledBuffer.setRGB(55-setbrightestone-2, 150, 150, 0);
          m_ledBuffer.setRGB(55-setbrightestone-3, 255, 255, 0);
         // }
         setbrightestone+=1;
         if(setbrightestone>24)
            setbrightestone=0;
            m_led.setData(m_ledBuffer);
          }
          
      }

      private void down(){
        
        for (int i=0;i<56;i++){
          m_ledBuffer.setRGB(i, 0, 0, 0);
      
          }

        // (int i=setbrightestone;i<setbrightestone+4;i+=4){
        m_ledBuffer.setRGB(28+setbrightestone, 25, 25, 0);
        m_ledBuffer.setRGB(28+setbrightestone+1, 75, 75, 0);
        m_ledBuffer.setRGB(28+setbrightestone+2, 150, 150, 0);
        m_ledBuffer.setRGB(28+setbrightestone+3, 255, 255, 0);

        m_ledBuffer.setRGB(27-setbrightestone, 25, 25, 0);
        m_ledBuffer.setRGB(27-setbrightestone-1, 75, 75, 0);
        m_ledBuffer.setRGB(27-setbrightestone-2, 150, 150, 0);
        m_ledBuffer.setRGB(27-setbrightestone-3, 255, 255, 0);
       // }
       setbrightestone+=1;
       if(setbrightestone>24)
          setbrightestone=0;
       
        
        m_led.setData(m_ledBuffer);
    }

    public void blink(int r, int g, int b)
    {
        

        int startingLED;
        if(blinkeven)
        {
            startingLED=1;
        }
        else startingLED = 0;

        for (int i=0;i<56;i++){
            m_ledBuffer.setRGB(i, 0, 0, 0);
        
            }

         for (int i=startingLED;i<56;i+=2){
            m_ledBuffer.setRGB(i, 255, 0, 0);
            
            }
            counter++;
            if(counter%10==0)
            {
                blinkeven=!blinkeven;
                m_led.setData(m_ledBuffer);
            }
        
    }

}
