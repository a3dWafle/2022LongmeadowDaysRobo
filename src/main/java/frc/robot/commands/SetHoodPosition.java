// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class SetHoodPosition extends CommandBase {

  private Hood hood;
  boolean bottom;
  public SetHoodPosition(Hood h, boolean setToBottom) {
    hood = h;
    bottom = setToBottom;
    addRequirements(h);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(bottom){//go to bottom
     
      hood.setPWMValue(-0.25);
   
      } else {// go to top
       hood.setPWMValue(0.25);
     }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
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

    if(bottom){
      finished = hood.getBottomLimitSwitch();
    }
    else{
      finished = hood.getTopLimitSwitch();
    }

  
    
    return finished;
  }
}
