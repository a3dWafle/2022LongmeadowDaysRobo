// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  public CANSparkMax m_intake = new CANSparkMax(Constants.intakeMotor, MotorType.kBrushless);

  public Intake() {
    m_intake.restoreFactoryDefaults();
  }

  public void setSpeed(double speed){
    m_intake.set(speed);//feed -1 to 1
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
