// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {

  public CANSparkMax leftClimb = new CANSparkMax(Constants.leftClimb, MotorType.kBrushless);
  public CANSparkMax rightClimb = new CANSparkMax(Constants.rightClimb, MotorType.kBrushless);

  public DigitalInput leftLimitSwitch = new DigitalInput(Constants.leftLimitSwitch);
  public DigitalInput rightLimitSwitch = new DigitalInput(Constants.rightLimitSwitch);


  

  public Climb() {
    leftClimb.restoreFactoryDefaults();
    rightClimb.restoreFactoryDefaults();
  }


  public void setLeftSpeed(double speed){
      leftClimb.set(speed);
  }

  public void setRightSpeed(double speed){
     rightClimb.set(speed);
  }

  public void setBothSpeed(double speed){
      leftClimb.set(-speed);
      rightClimb.set(speed);
  }

  public boolean getLeftSwitch(){
    return leftLimitSwitch.get();
  }

  public boolean getRightSwitch(){
    return rightLimitSwitch.get();
  }

  public void raiseLeftHook(){
    while(!getLeftSwitch()){
      setLeftSpeed(0.3);
    }
    setLeftSpeed(0);
  }

  public void raiseRightHook(){
    while(!getRightSwitch()){
      setRightSpeed(-0.3);
    }
    setRightSpeed(0);
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
