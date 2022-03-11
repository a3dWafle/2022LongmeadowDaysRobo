// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //CAN motor controller IDs
        
        //Drive train
        public static int leftMaster = 3; 
        public static int leftFollower = 4;
        public static int rightMaster = 1;
        public static int rightFollower = 2;

        //Shooter
        public static int shooterMotor = 5;

        //Intake
        public static int intakeMotor = 7;

        //Climb
        public static int leftClimb = 12;
        public static int rightClimb = 11;
       

    //Roborio Outputs 
 

        //Feeder
        public static int rightFeederServo = 0;//PWM port on the roborio
        public static int leftFeederServo = 1;

        //Hood
        

    //Roborio Inputs - Sensors

        //Hood
        public static int bottomLimitSwitch = 3;
        public static int topLimitSwitch = 2;

        //Climb
        public static int leftLimitSwitch = 4;
        public static int rightLimitSwitch = 5;

    //Driverstation ports 
    public static int joystickPort = 0;//USB port on driver station computer
    public static int xboxPort = 1;


    //Robot constants

        //Drive train
        public static double encoderTurnsPerRev = 11;//encoder turns
        public static double encouderTurnsPer90 = 11.45;
        public static double distancePerRev = 20.5;//inches

        //Feeder
        public static int gateOffset = 5;//Degrees - How far off horizontal the gate servos are when closed

        //Vision 
        public static double targetHeight = 104; //inches
        public static double limelightHeight = 18.5; //inches
        public static double limelightOffset = 24; // inches from limelight to intake
        public static double limelightAngle = 45;

        public static double closeDistance = 60;
        public static double farDistance = 102;


}
