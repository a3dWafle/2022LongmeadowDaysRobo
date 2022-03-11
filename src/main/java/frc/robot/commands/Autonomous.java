// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;


public class Autonomous extends SequentialCommandGroup {
  
  private DriveTrain d;
  private Feeder f;
  private Shooter s;
  private Intake intake;

  public Autonomous(DriveTrain drive, Feeder feed, Shooter shoot, Intake i) {
    d = drive;
    f = feed;
    intake = i;
    s = shoot;
    addRequirements(drive, feed, shoot, i);
    
    addCommands(  
    new SetIntakeSpeed(intake, -0.2),
    new DriveStraight(d, 100),
    new SetIntakeSpeed(intake, 0)
    //new Turn(d),
    //new Fire(f, s)
    );
  }
}
