// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.ejml.dense.row.decomposition.eig.WatchedDoubleStepQRDecomposition_DDRM;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;


public class SingleBallAuto extends SequentialCommandGroup {
  
  private DriveTrain d;
  private Feeder f;
  private Shooter s;
  private Intake i;
  private Vision v;

  public SingleBallAuto(DriveTrain drive, Feeder feed, Shooter shoot, Intake intake, Vision vision) {
    d = drive;
    f = feed;
    i = intake;
    s = shoot;
    v = vision;
    addRequirements(drive, feed, shoot, i, vision);
    
    addCommands(  
    new DriveStraight(d, 30,true),
    new WaitCommand(0.5),
    new DriveStraight(d, 80,false),
    new GyroTurn(d, 170),
    new DriveStraight(d, 70,false),
    new AutonomousShoot(s, v, d),
    new WaitCommand(2),
    new Fire(f, s)

    );
  }
}
