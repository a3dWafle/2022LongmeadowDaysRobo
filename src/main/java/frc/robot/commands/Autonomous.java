// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.ejml.dense.row.decomposition.eig.WatchedDoubleStepQRDecomposition_DDRM;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;


public class Autonomous extends SequentialCommandGroup {
  
  private DriveTrain d;
  private Feeder f;
  private Shooter s;
  private Intake i;
  private Vision v;
  private Hood h;
  public double speed;

  public Autonomous(DriveTrain drive, Feeder feed, Shooter shoot, Intake intake, Vision vision, Hood hood) {
    d = drive;
    f = feed;
    i = intake;
    s = shoot;
    v = vision;
    h = hood;
    addRequirements(drive, feed, shoot, i, vision, h);
    
    addCommands(  
    new ToggleLimelightLED(v),   // Works

    new DriveStraight(d, 30,true),
    new WaitCommand(0.5),

    new SetIntakeSpeed(i, -0.275),

    new DriveStraight(d, 60,false),

    new WaitCommand(1),
    //new SetIntakeSpeed(i, 0),

    new GyroTurn(d, 170),
    
    new CalculateAutoShooterSpeed(s, v),

    new WaitCommand(2.5),

    new Fire(f, s),

    new WaitCommand(0.75),

    new Fire(f,s),

    new SetShooterSpeed(s, 0),

    new SetFeederSpeed(f, 0)

    //new DriveStraight(d, 80,false)

    /*
    new SetShooterSpeed(s, 0.4),

      new ParallelCommandGroup(
       new AutonomousShoot(s, v, d),
        new SetHoodPosition(h)
      ),
      new WaitCommand(2.5),

      new Fire(f, s)
*/

    
    //new DriveStraight(d, 60,false),

    //new AutonomousShoot(s, v, d),

    //new WaitCommand(2),
    //new Fire(f, s)

    );
  }
}
