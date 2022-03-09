// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;


public class Autonomous extends SequentialCommandGroup {
  
  private DriveTrain d;
  private Feeder f;
  private Shooter s;

  public Autonomous(DriveTrain drive, Feeder feed, Shooter shoot) {
    d = drive;
    f = feed;
    s = shoot;
    
    addCommands(  
    new DriveStraight(d, 0.4, 40) //as far as I know, this won't drive far enough. may be as simple as putting a loop
    //in the driveStraight method, or just calling it multiple times here
    //new Turn(d),
    //new Fire(f, s)
    );
  }
}
