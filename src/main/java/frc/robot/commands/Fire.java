// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Fire extends SequentialCommandGroup {

  private Feeder gate; 
  private Shooter shooter;

  public Fire(Feeder feeder, Shooter m_shooter) {
    
    gate = feeder;
    shooter = m_shooter;
    addCommands(
      new SetFeederSpeed(gate, -1),

      new WaitCommand(1),

      new SetFeederSpeed(gate, 0),
      /*
      new ToggleGate(gate, true),

      new ToggleGate(gate, false),//Release balls
     
      new WaitCommand(2),

      new ToggleGate(gate, true),
      //Reset Shooter
      new WaitCommand(1),
    */
      new SetShooterSpeed(shooter, 0)//Constants.restingSpeed)
    );
  }
}
