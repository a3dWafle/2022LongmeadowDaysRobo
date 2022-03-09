// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  //Define the four Spark Max motor controllers for the drivetrain
  //There are two motor controllers for each side, one follower, and one master
  public CANSparkMax m_leftMaster = new CANSparkMax(Constants.leftMaster, MotorType.kBrushless);
  public CANSparkMax m_leftFollower = new CANSparkMax(Constants.leftFollower, MotorType.kBrushless);
  public CANSparkMax m_rightMaster = new CANSparkMax(Constants.rightMaster, MotorType.kBrushless);
  public CANSparkMax m_rightFollower = new CANSparkMax(Constants.rightFollower, MotorType.kBrushless);

  //Create a new DifferentialDrive object
  public DifferentialDrive m_drive;

  //Create an encoder for the master controller of each side
  public RelativeEncoder m_leftEncoder = m_leftMaster.getEncoder();
  public RelativeEncoder m_rightEncoder = m_rightMaster.getEncoder();

  public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  double error;
  double kP = 0.05;

  public DriveTrain() {

    //Restoring Factory Defaults for each motor controller
    m_leftMaster.restoreFactoryDefaults();
    m_leftFollower.restoreFactoryDefaults();
    m_rightMaster.restoreFactoryDefaults();
    m_rightFollower.restoreFactoryDefaults();

    gyro.calibrate();

    /*
      Since each side of the drive train has two motors geared togther,
      we tell one of the motor controllers on each size to follow, or copy, everything
      the other on the same side motor controller does.
    */
    m_leftFollower.follow(m_leftMaster);
    m_rightFollower.follow(m_rightMaster);

    //Feed the DifferentialDrive the two motor controllers
    m_drive = new DifferentialDrive(m_leftMaster, m_rightMaster);

  }

 

  public void turn(double theta)//Turns the robot right the input angle
  {
      double targetAngle = Math.round(getGyroAngle())+ theta-4;

      while(targetAngle != Math.round(getGyroAngle())){
        joystickDrive(0.25, 0);
      }

      for(int i =0; i<=10; i++){
        joystickDrive(-0.1, 0);
      }
     

  }

  public void turnPID(double targetAngle)
  {
      gyro.reset();
      double error = 100;
      double power = 0;

      while(Math.abs(targetAngle-getGyroAngle())>=2){
        error = (targetAngle-getGyroAngle());
        power = -kP*error;
  
      if(Math.abs(power) >= 0.25){
          if(power>0){
            power = 0.25;
          } else {
            power = -0.25;
          }
  
        }

        joystickDrive(power, 0);

      }

      joystickDrive(0,0);


  }

  public void driveStraight(double power){

      error = -gyro.getAngle();
      double turnPower = kP*error;
      joystickDrive(turnPower, power);

  }

  public double getGyroAngle(){
    return gyro.getAngle();
  }

  public void resetGyro(){
    gyro.reset();
  }

  public void joystickDrive(double turn, double move){ //Takes in doubles for translation and rotation(both -1 to 1)


    m_drive.arcadeDrive(turn, move);
    
  
  }

  public void resetEncoders(){//Sets the position of each encoder to zero to restart counting
    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);

  }

  public double getEncoder(){//returns the current number of motor rotations since the encoders were reset
    return m_leftEncoder.getPosition();

  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Gyro", getGyroAngle());
  }
}
