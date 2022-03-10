// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */

  public CANSparkMax m_shooter = new CANSparkMax(Constants.shooterMotor, MotorType.kBrushless);
 

  public Shooter() {
    m_shooter.restoreFactoryDefaults();
  }

  public void setSpeed(double speed){
    if(speed!=0){
      SmartDashboard.putNumber("DB/Slider 0", speed);

    }
    m_shooter.set(speed);//feed -1 to 1
  }

  public double getSpeed(){
    return m_shooter.get();
  }

  public void setSpeedFromSlider(){
      double speed = SmartDashboard.getNumber("DB/Slider 0", 0.0);
      System.out.println(speed);
      //if(speed>=0.55){
        //speed = 0;
      //}

      m_shooter.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
