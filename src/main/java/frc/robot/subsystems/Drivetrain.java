/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.Drive;

public class Drivetrain extends Subsystem {
  Spark leftDrive, rightDrive;
  DifferentialDrive differentialDrive;

  public Drivetrain() {
    leftDrive = new Spark(RobotMap.leftMotor);
    rightDrive = new Spark(RobotMap.rightMotor);

    differentialDrive = new DifferentialDrive(leftDrive, rightDrive);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double throttleSpeed, double turnSpeed) {
    differentialDrive.arcadeDrive(throttleSpeed, turnSpeed);
  }

  public void stop() {
    tankDrive(0.0, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Drive());
  }
}
