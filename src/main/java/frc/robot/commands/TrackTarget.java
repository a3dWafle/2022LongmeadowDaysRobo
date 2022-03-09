// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class TrackTarget extends CommandBase {

  private DriveTrain drive;
  private Vision vision;

  double kP = 0.05;
  double error;
  double turnPower;
  double transPower;
  double area;

  public TrackTarget(DriveTrain m_drive, Vision v) {
    drive = m_drive;
    vision = v;
    addRequirements(m_drive);
    addRequirements(v);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    /*
    while(vision.getValidTarget()==0){
      drive.joystickDrive(0.25, 0);
    }
    */
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = vision.getXOffset();
    turnPower = kP*error;

    area = vision.getArea();

    if(area>=0.75){
      transPower = 0;
    } else{
      transPower = 0.25;
    }

    if(Math.abs(error)<0.25){
        turnPower = 0;
    }

    if(Math.abs(turnPower) >= 0.25){
        if(turnPower>0){
          turnPower = 0.25;
        } else {
          turnPower = -0.25;
        }

    }
    
    drive.joystickDrive(turnPower, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
