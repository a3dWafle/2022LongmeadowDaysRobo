// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class ToggleHood extends CommandBase {

  private final Hood hood;


  public ToggleHood(Hood h) {
    hood = h;
    addRequirements(h);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      hood.setPWMValue(0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.setPWMValue(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return hood.getTopLimitSwitch();
  }
}
