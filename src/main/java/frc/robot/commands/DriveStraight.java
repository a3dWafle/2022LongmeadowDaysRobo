// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveStraight extends CommandBase {

  private DriveTrain drive;
  double distance;
  double power;
  double encoderTurns;
  double kP = 0.05;
  double error;
  boolean dropIntake;

  public DriveStraight(DriveTrain m_drive, double d, boolean drop) {
    drive = m_drive;
    distance = d;
    dropIntake = drop;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
    drive.resetEncoders();
    encoderTurns = (distance*Constants.encoderTurnsPerRev)/Constants.distancePerRev;
    drive.resetGyro();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = Math.abs(drive.getEncoder()) - Math.abs(encoderTurns);
    power = -kP*error;

    if(Math.abs(power) >= 0.45){
      if(power>0){
        power = 0.45;
      } else {
        power = -0.45;
      }
 
    }
/*
    if(Math.abs(power) <= 0.03){
      if(power>0){
        power = 0.03;
      } else {
        power = -0.03;
      }
 
    }
*/
  

  error = drive.getGyroAngle();
  double turnPower = -kP*error;

  if(Math.abs(turnPower) >= 0.25){
    if(turnPower>0){
      turnPower = 0.25;
    } else {
      turnPower = -0.25;
    }
  }

  drive.joystickDrive(turnPower, power);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(dropIntake){
      drive.joystickDrive(0, -0.05);

    } else {
      drive.joystickDrive(0, 0);

    }
    //drive.joystickDrive(0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (encoderTurns - drive.getEncoder())<=10;
  }
}
