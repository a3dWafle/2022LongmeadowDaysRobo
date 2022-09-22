// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class CalculateAutoShooterSpeed extends CommandBase {

  private Shooter shooter;
  private Vision v;
  double speed;

  public CalculateAutoShooterSpeed(Shooter m_shooter, Vision vision) {
    shooter = m_shooter;
    v = vision;
    addRequirements(m_shooter, vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      speed = v.calculateAutoShooterSpeed();
      if(speed<=0.58){
        shooter.setSpeed(speed);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
