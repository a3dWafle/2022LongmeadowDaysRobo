// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  
  public Servo leftServo = new Servo(Constants.leftFeederServo);
  public Servo rightServo = new Servo(Constants.rightFeederServo);

  public Feeder() {}

  public void openGate(boolean closed) {
    if(closed) {
      rightServo.setAngle(140);
      leftServo.setAngle(40);
    }
    else {
     
      
      rightServo.setAngle(80);
      leftServo.setAngle(100);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
