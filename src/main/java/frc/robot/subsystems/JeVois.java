/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.commands.ReadJeVois;

/**
 * Add your docs here.
 */
public class JeVois extends Subsystem {
  SerialPort cam = null;
  UsbCamera jevoisUSB = null;

  public JeVois() {
    setupUSB();
    setupSerial();
  }

  public void setupUSB() {
    jevoisUSB = new UsbCamera("jevoiscam", 0);
    jevoisUSB.setVideoMode(PixelFormat.kYUYV, 320, 254, 60);
  }

  public void setupSerial() {
    int tryCount = 0;
    do {
      try {
        System.out.println("Attempting to setup jevois serial port!");
        cam = new SerialPort(9600, SerialPort.Port.kMXP); //115200
        tryCount = 99;
        System.out.println("SUCCESS!");
      } catch (Exception e) {
        tryCount++;
        System.out.print("Failure: " + tryCount);
      }
    } while(tryCount < 3);
    if (tryCount == 99) {
      writeJeVois("info\n");
    }
  }

  public void writeJeVois(String cmd) {
    if (cam == null) {
      return;
    }
    int bytes = cam.writeString(cmd);
    System.out.println("Wrote "+bytes+"/"+cmd.length()+" bytes");
  }

  public void print() {
    try {
      if (cam.getBytesReceived() > 0) {
        System.out.println("Read: "+cam.readString());
      }
    } catch(Exception e) {
      System.out.println("Error");
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ReadJeVois());
  }
}
