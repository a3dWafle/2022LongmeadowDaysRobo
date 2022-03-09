// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class DriveDistance extends CommandBase {
  private final DriveTrain drive;
  private final Vision eye;
  public DriveDistance(DriveTrain d, Vision i) {
    drive = d;
    eye = i;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double d = eye.getDistance();
    
    double error = (d-14.5) - 75;

    double kp = 0.5;

    double power = kp*error;

    if(Math.abs(error)<2){
      power = 0;
    }

  if(Math.abs(power) >= 0.25){
      if(power>0){
        power = 0.25;
      } else {
        power = -0.25;
      }

  }

    drive.joystickDrive(0, power);
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
