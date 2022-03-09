// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Vision;

public class GetDistance extends CommandBase {
  private final Vision eye;
  double theta;
  public GetDistance(Vision i) {
    eye = i;
    addRequirements(i);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    theta = ((45 + eye.getYOffset()) * Math.PI / 180);

    double d = 61.5/Math.tan(theta);

    //System.out.println(d);
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
