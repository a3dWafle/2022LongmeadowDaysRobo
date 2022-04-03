// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class GyroTurn extends CommandBase {
  /** Creates a new GyroTurn. */
  private DriveTrain drive;

  double kP = 0.1;
  double error;
  double targetAngle;
  double turnPower;

  public GyroTurn(DriveTrain m_drive, double a) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = m_drive;
    targetAngle = a;
    addRequirements(m_drive);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    error = targetAngle - drive.getGyroAngle();
    turnPower = kP*error;

    if(Math.abs(turnPower) >= 0.35){
      if(turnPower>0){
        turnPower = 0.35;
      } else {
        turnPower = -0.35;
      }
    }

    drive.joystickDrive(turnPower, 0);

  } 
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.joystickDrive(0, 0);
    drive.joystickDrive(0, 0);
    drive.resetGyro();


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(targetAngle) - Math.abs(drive.getGyroAngle()))<=4;
  }
}
