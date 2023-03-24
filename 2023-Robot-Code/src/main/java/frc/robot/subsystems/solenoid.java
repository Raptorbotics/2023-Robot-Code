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
  Solenoid solenoidObjectSix = new Solenoid(PneumaticsModuleType.CTREPCM, 6);
  Solenoid solenoidObjectOne = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
  Solenoid solenoidObjectSeven = new Solenoid(PneumaticsModuleType.CTREPCM, 7);
  Solenoid solenoidObjectZero = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

  Compressor pcmCompressor0 = new Compressor(0, PneumaticsModuleType.CTREPCM);
  Compressor pcmCompressor1 = new Compressor(1, PneumaticsModuleType.CTREPCM);

  private boolean clawState = true;

  public boolean getClawState() {
    return clawState;
  }

  public void openClaw() {
    solenoidObjectSeven.set(false);
    solenoidObjectZero.set(false);
    solenoidObjectSix.set(true);
    solenoidObjectOne.set(true);

    clawState = true;
    System.out.println("The claw has been set to " + clawState);
  }
  public void closeClaw() {
    solenoidObjectSix.set(false);
    solenoidObjectOne.set(false);
    solenoidObjectSeven.set(true);
    solenoidObjectZero.set(true);
    clawState = false;
    System.out.println("The claw has been set to " + clawState);
  }


  public solenoid() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
