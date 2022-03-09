// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class AutoAim extends CommandBase {

  private Shooter shooter;
  private Hood hood;
  private Vision vision;
  private DriveTrain drive;

  double kP = 0.05;
  double error;
  double turnPower;
  double transPower;
  //double area;

  public AutoAim(Shooter m_shooter, Hood h, Vision v, DriveTrain m_drive) {
    vision = v;
    shooter = m_shooter;
    hood = h;
    drive = m_drive;
    addRequirements(m_shooter);
    addRequirements(h);
    addRequirements(v);
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {
    vision.setLedOn(true);
    /*
    double speed = vision.calculateShooterSpeed();
    if(speed <= 0.55){
      shooter.setSpeed(speed);
    }

    if(vision.getDistance()<=Constants.closeDistance){
        hood.setHoodPosition(true);
    }
*/
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = vision.getXOffset();
    turnPower = kP*error;

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
  public void end(boolean interrupted) {
    vision.setLedOn(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
