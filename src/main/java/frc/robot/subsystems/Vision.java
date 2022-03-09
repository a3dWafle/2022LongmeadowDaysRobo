// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Vision extends SubsystemBase {

public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
public NetworkTableEntry tv = table.getEntry("tv");
public NetworkTableEntry tx = table.getEntry("tx");
public NetworkTableEntry ty = table.getEntry("ty");
public NetworkTableEntry ta = table.getEntry("ta");

public NetworkTableEntry ledMode = table.getEntry("ledMode");


  public Vision() {
      ledMode.setNumber(1);//Set led off
  }

  public void setLedOn(boolean on){
      if(on){
        ledMode.setNumber(3);
      }
      else{
        ledMode.setNumber(1);
      }
  }

  public double getXOffset(){
    return tx.getDouble(0.0);
  }

  public double getArea(){
    return ta.getDouble(0.0);
  }

  public double getYOffset(){
    return ty.getDouble(0.0);
  }

  public double getValidTarget(){
    return tv.getDouble(0.0);
  }

  public double getDistance(){
    double theta = ((Constants.limelightAngle + getYOffset()) * Math.PI / 180);

    return ((Constants.targetHeight - Constants.limelightHeight)/Math.tan(theta))-Constants.limelightOffset;

  }

  public double calculateShooterSpeed(){
    double speed = 0;

    if(getDistance()<=Constants.closeDistance){
      speed = Math.round(getDistance()/12) + 40; // v = x + 40
    }

    return speed/100;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
