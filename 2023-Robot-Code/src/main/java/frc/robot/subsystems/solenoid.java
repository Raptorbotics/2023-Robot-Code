// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

public class solenoid extends SubsystemBase {
  /** Creates a new solenoid. */
  Solenoid solenoidObjectYellow = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
  Solenoid solenoidObjectBlue = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  Compressor pcmCompressor0 = new Compressor(0, PneumaticsModuleType.CTREPCM);
  Compressor pcmCompressor1 = new Compressor(1, PneumaticsModuleType.CTREPCM);

  private boolean solenoidState = false;

  public boolean getSolenoidState() {
    return solenoidState;
  }

  public void setSolenoidStateTrue() {
    solenoidObjectBlue.set(false);
    solenoidObjectYellow.set(true);
    solenoidState = true;
    System.out.println("The solenoidState has been set to " + solenoidState);
  }
  public void setSolenoidStateFalse() {
    solenoidObjectBlue.set(true);
    solenoidObjectYellow.set(false);
    solenoidState = false;
    System.out.println("The solenoidState has been set to " + solenoidState);
  }


  public solenoid() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
