// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;


public class Autonomous extends SequentialCommandGroup {
  
  private DriveTrain d;
  private Feeder f;
  private Shooter s;
  private Intake i;
  private Vision v;

  public Autonomous(DriveTrain drive, Feeder feed, Shooter shoot, Intake intake, Vision vision) {
    d = drive;
    f = feed;
    i = intake;
    s = shoot;
    v = vision;
    addRequirements(drive, feed, shoot, i, vision);
    
    addCommands(  
    new SetIntakeSpeed(i, -0.2),
    new DriveStraight(d, 110),
    new WaitCommand(0.5),
    new SetIntakeSpeed(i, 0),
    new GyroTurn(d, 175),
    new DriveStraight(d, 55),
    new AutonomousShoot(s, v, d),
    new WaitCommand(4),
    new Fire(f, s)

    //new Fire(f, s)
    );
  }
}
