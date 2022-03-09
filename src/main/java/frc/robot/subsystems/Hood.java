// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hood extends SubsystemBase {

  public PWMVictorSPX m_leftHood = new PWMVictorSPX(2);
  public PWMVictorSPX m_rightHood = new PWMVictorSPX(3);
  public DigitalInput topLimitSwitch = new DigitalInput(Constants.topLimitSwitch);
  public DigitalInput bottomLimitSwitch = new DigitalInput(Constants.bottomLimitSwitch);
  

  public Hood() {

  }


  public double getPWMValue(){
    return m_leftHood.get();
  }

  public boolean getTopLimitSwitch(){
      return topLimitSwitch.get();
  }
  
  public boolean getBottomLimitSwitch(){
      return bottomLimitSwitch.get();
  }
  
  public void setHoodPosition(boolean goToBottomPosition){

      if(goToBottomPosition){//go to bottom
        setPWMValue(0.25);

        while(true){
          if(getBottomLimitSwitch()){
            setPWMValue(0);
            break;
          }
        }

      } else {// go to top
        setPWMValue(-0.25);
        while(true){

          if(getTopLimitSwitch()){
            setPWMValue(0);
            break;
          }
      }}

  }


  public void calibrate(){
    while(!getBottomLimitSwitch()){
        setPWMValue(0.25);
    }  
      setPWMValue(0);
  }
  
  public void setPWMValue(double speed){//-1 to 1 - never do more than 0.5
    m_leftHood.set(speed);
    m_rightHood.set(-speed);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
