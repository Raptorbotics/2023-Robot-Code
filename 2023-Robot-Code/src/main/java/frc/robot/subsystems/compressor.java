// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class compressor extends SubsystemBase {
  /** Creates a new compressor. */
  public compressor() {
  }
  Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
  boolean compressorState = false;

  public void compressorOn() {
	//pcmCompressor.enableAnalog();
  compressorState = true;
}

public void compressorOff(){
  pcmCompressor.disable();
  compressorState = false;
}

public boolean state() {
  return compressorState;
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
