// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoAim;
import frc.robot.commands.Autonomous;
import frc.robot.commands.AutonomousShoot;
import frc.robot.commands.CalibrateHood;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.Fire;
import frc.robot.commands.GetDistance;
import frc.robot.commands.GetShootSpeed;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.IncrementSpeed;
import frc.robot.commands.JoystickDrive;
import frc.robot.commands.PostLimelightValues;
import frc.robot.commands.PrintColorSensor;
import frc.robot.commands.RaiseLeftHook;
import frc.robot.commands.RaiseRightHook;
import frc.robot.commands.SetBothHooksSpeed;
import frc.robot.commands.SetFeederSpeed;
import frc.robot.commands.SetHoodPosition;
import frc.robot.commands.SetHoodSpeed;
import frc.robot.commands.SetIntakeSpeed;
import frc.robot.commands.SetLeftClimbSpeed;
import frc.robot.commands.SetRightClimbSpeed;
import frc.robot.commands.SetShooterSpeed;
import frc.robot.commands.SetSpeedFromSlider;
import frc.robot.commands.SetTurnSpeed;
import frc.robot.commands.ToggleGate;
import frc.robot.commands.ToggleRestingSpeed;
import frc.robot.commands.TrackTarget;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Sensors;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DriveTrain m_drive = new DriveTrain();
  private final Feeder feeder = new Feeder();
  private final Shooter m_shooter = new Shooter();
  private final Intake m_intake = new Intake();
  private final Vision vision = new Vision();
  private final Hood hood = new Hood();
  private final Sensors sensors = new Sensors();
  private final Climb climb = new Climb();

  
  //Default Commands
  private final Autonomous m_autoCommand = new Autonomous(m_drive, feeder, m_shooter, m_intake, vision, hood); 
  private final Fire fire = new Fire(feeder, m_shooter); //added this line after changing autoCommand
  private final JoystickDrive joystickDrive = new JoystickDrive(m_drive);
  private final GetDistance postLimelight = new GetDistance(vision);
  private final TrackTarget trackTape = new TrackTarget(m_drive, vision);
  private final CalibrateHood calibrateHood = new CalibrateHood(hood);
  private final PrintColorSensor printColorSensor = new PrintColorSensor(sensors);
  private final GetShootSpeed getShootSpeed = new GetShootSpeed(m_shooter);

  public static Joystick joystick = new Joystick(Constants.joystickPort);
  public static XboxController xbox = new XboxController(Constants.xboxPort);

  public static int hoodPosition;
  


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drive.setDefaultCommand(joystickDrive);
    vision.setDefaultCommand(postLimelight);
    //feeder.setDefaultCommand(lockGate);
    //sensors.setDefaultCommand(printColorSensor);

    m_shooter.setDefaultCommand(getShootSpeed);
    

  }

  
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  //Joystick Buttons

    //Intake
    JoystickButton intakeButton = new JoystickButton(joystick, 1);
        intakeButton.whenHeld(new SetIntakeSpeed(m_intake, -0.275)); 
        intakeButton.whenReleased(new SetIntakeSpeed(m_intake, 0));

    JoystickButton ejectButton = new JoystickButton(joystick, 2);
        ejectButton.whenHeld(new SetIntakeSpeed(m_intake, 0.1)); 
         ejectButton.whenReleased(new SetIntakeSpeed(m_intake, 0));

  //Xbox Buttons

    //Shooting
    JoystickButton fireButton = new JoystickButton(xbox, XboxController.Button.kY.value); //Release Balls
        fireButton.whenPressed(new Fire(feeder, m_shooter));

    JoystickButton lowShot = new JoystickButton(xbox, XboxController.Button.kA.value); //set to 30%
    lowShot.whenPressed(new SetShooterSpeed(m_shooter, 0.35));
    /*
      lowShot.whenPressed(new ParallelCommandGroup(
        new SetShooterSpeed(m_shooter, 0.31),
        new SetHoodPosition(hood)
      )) ; 


    //JoystickButton closeLowButton = new JoystickButton(xbox, XboxController.Button.kA.value); //Low shot next to hub
      
    /*    
    closeLowButton.whenPressed(new SequentialCommandGroup(
                                   //new SetHoodPosition(hood, false),
                                   //new WaitCommand(0.25),
                                    new SetShooterSpeed(m_shooter, 0.3)
                                 ));
                                 */

    JoystickButton closeHighButton = new JoystickButton(joystick, 5);//High shot next to hub
        closeHighButton.whenPressed(new SequentialCommandGroup(
                                    //new SetHoodPosition(hood, true),
                                   // new WaitCommand(0.25),
                                    //new SetShooterSpeed(m_shooter, 0.40)
                                    new SetSpeedFromSlider(m_shooter)
                                    //new SetHoodPosition(hood, true)
                                    ));                  
                                    
    JoystickButton autoAimButton = new JoystickButton(xbox, XboxController.Button.kB.value);//Automatic aiming
        autoAimButton.whenHeld( new ParallelCommandGroup(
          new AutoAim(m_shooter, vision, m_drive)
          //new SetHoodPosition(hood)
        ));
    
        
    JoystickButton toggleRestingSpeed = new JoystickButton(joystick, 6);//Stop Shooter
        toggleRestingSpeed.whenPressed( new ParallelCommandGroup(
                                      new ToggleRestingSpeed(m_shooter),
                                      new ToggleGate(feeder, true)
                                      ));
      
     JoystickButton stopShooter = new JoystickButton(xbox, XboxController.Button.kX.value);//Stop Shooter
      stopShooter.whenPressed(new ToggleRestingSpeed(m_shooter));
  
    





    JoystickButton button3= new JoystickButton(xbox, XboxController.Button.kLeftBumper.value);
    button3.whenHeld(new SetBothHooksSpeed(climb, 0.3));
    button3.whenReleased(new SetBothHooksSpeed(climb, 0));

    JoystickButton button4= new JoystickButton(xbox, XboxController.Button.kRightBumper.value);
    button4.whenHeld(new SetBothHooksSpeed(climb, -0.3));
    button4.whenReleased(new SetBothHooksSpeed(climb, 0));
    


    //JoystickButton button7 = new JoystickButton(joystick, 6);
    //button7.whenPressed(new ToggleHood(hood));

    JoystickButton trimLeft = new JoystickButton(joystick, 3);
    trimLeft.whenHeld(new SetTurnSpeed(m_drive, -0.25));
    /*
    button8.whenHeld(new SetHoodSpeed(hood, 0.5));
    button8.whenReleased(new SetHoodSpeed(hood, 0));
    */

    JoystickButton trimRight = new JoystickButton(joystick, 4);
    trimRight.whenHeld(new SetTurnSpeed(m_drive, 0.25));

    /*
    button.whenHeld(new SetHoodSpeed(hood, -0.5));
    button.whenReleased(new SetHoodSpeed(hood, 0));
    */
    JoystickButton button9 = new JoystickButton(joystick, 10);
    button9.whenPressed(new AutonomousShoot(m_shooter, vision, m_drive));

    


    JoystickButton button14 = new JoystickButton(joystick, 12);
    button14.whenPressed(new SetFeederSpeed(feeder, -1));
    button14.whenReleased(new SetFeederSpeed(feeder, 0));
    /*
    button14.whenPressed(new SequentialCommandGroup(
      
    new GyroTurn(m_drive, 180),
    new SetShooterSpeed(m_shooter, 0.4),

      new ParallelCommandGroup(
       new AutonomousShoot(m_shooter, vision, m_drive),
        new SetHoodPosition(hood)
      ),
      new WaitCommand(2.5),

      new Fire(feeder, m_shooter)
    
    ));

    /*
    JoystickButton button13 = new JoystickButton(joystick, 10); //set to 30%
    button13.whenPressed(new SequentialCommandGroup(
                          new RaiseLeftHook(climb),

                          new RaiseRightHook(climb)
    ) );
   */

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}
