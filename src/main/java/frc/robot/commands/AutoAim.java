// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class AutoAim extends CommandBase {

  private Shooter shooter;
  private Vision vision;
  private DriveTrain drive;

  double kP = 0.05;
  double error;
  double turnPower;
  double transPower;
  //double area;
  double speed;

  public AutoAim(Shooter m_shooter, Vision v, DriveTrain m_drive) {
    vision = v;
    shooter = m_shooter;
    drive = m_drive;
    addRequirements(m_shooter);
    addRequirements(v);
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {
    //vision.setLedOn(true);
    Timer.delay(0.5);
    vision.calculateHoodPosition(); //USe distance calculation to set hood position(0 or 1)
                                    //References global variable hoodPosition in RobotContainer
    
    speed = vision.calculateShooterSpeed();//Reference distance for shooting speed
    if(speed<=0.58){//Make sure speed is under 58 percent
      shooter.setSpeed(speed);

    }
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println(vision.distance);
    
    System.out.println("Speed: " + speed);
    
    System.out.println("Distance in Inches: " + vision.distance);
/*
    System.out.println("Angle: " + vision.theta);

    System.out.println("Hood Position: " + RobotContainer.hoodPosition);
    */
    error = vision.getXOffset();    //Tracking tape with proportional loop
    turnPower = kP*error;

    if(Math.abs(error)<0.15){
        turnPower = 0;
    }

    if(Math.abs(turnPower) >= 0.3){
        if(turnPower>0){
          turnPower = 0.3;
        } else {
          turnPower = -0.3;
        }

    }
    
    drive.joystickDrive(turnPower, 0);
  }
;
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //vision.setLedOn(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() { //Ends when button is released
    return false; 
  }
}
