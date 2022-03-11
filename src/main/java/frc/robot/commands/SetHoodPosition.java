// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Vision;

public class SetHoodPosition extends CommandBase {

  private Hood hood;
  
  //boolean bottom;
  int hoodPosition;

  public SetHoodPosition(Hood h) {
    hood = h;
   
    //bottom = setToBottom;
    
    addRequirements(h);
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.hoodPosition==1){//go to top
     
      hood.setPWMValue(-0.4);
   
      } else if(RobotContainer.hoodPosition==0) {// go to bottom
       hood.setPWMValue(0.4);
     }
  }  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.setPWMValue(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean finished;

    if(RobotContainer.hoodPosition==1){
      finished = hood.getBottomLimitSwitch();
    }
    else if(RobotContainer.hoodPosition==0){
      finished = hood.getTopLimitSwitch();
    } else {
      finished = false;
    }

  
    
    return finished;
  }
}
