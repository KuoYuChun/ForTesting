/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.sound.midi.VoiceStatus;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  VictorSP top;
  VictorSP down;
  VictorSP shooter1;
  VictorSP shooter2;
  Joystick m_stick;
  Timer m_timer;
  AnalogInput m_US;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    top = new VictorSP(1);
    down = new VictorSP(2);
    shooter1 = new VictorSP(3);
    shooter2 = new VictorSP(4);
    m_stick = new Joystick(0);
    m_timer = new Timer();
    m_US = new AnalogInput(0);
  }


  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    if(m_stick.getRawAxis(1) > 0.3 || m_stick.getRawAxis(1) < -0.3){
      top.set(m_stick.getRawAxis(1) * - 0.7);
    }else{
      top.set(0);
    }
    if(m_stick.getRawAxis(5) > 0.3 || m_stick.getRawAxis(5) < -0.3){
      down.set(m_stick.getRawAxis(5) * 0.8);
    }else{
      down.set(0);
    }
    if(m_stick.getRawAxis(2) > 0.3){
      shooter1.set(m_stick.getRawAxis(2) * -0.2);
      shooter2.set(m_stick.getRawAxis(2) * 0.8);
    }else{
      shooter1.set(0);
      shooter2.set(0);
    }
    // if(m_stick.getRawAxis(3) > 0.3){
    //   shooter2.set(m_stick.getRawAxis(3) * 0.7);
    // }else{
    //   shooter2.set(0);
    // }
    double sensorValue = m_US.getVoltage();
    double scaleFactor = 1/(5./1024.); //scale converting voltage to distance
    double distance = 5*sensorValue*scaleFactor; //convert the voltage to distance
    SmartDashboard.putNumber("DB/Slider 0", distance); //write the value to the LabVIEW DriverStation
    System.out.println("distance" + distance);
    System.out.println("Voltage" + sensorValue);

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
