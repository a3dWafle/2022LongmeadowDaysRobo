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

  public DriveStraight(DriveTrain m_drive, double p, double d) {
    drive = m_drive;
    distance = d;
    power = p;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
    drive.resetEncoders();
    encoderTurns = (distance*Constants.encoderTurnsPerRev)/Constants.distancePerRev;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.driveStraight(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(drive.getEncoder())>=Math.abs(encoderTurns);
  }
}
