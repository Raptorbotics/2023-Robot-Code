// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants.extenderConstants;

public class extender extends SubsystemBase {
  private VictorSPX extensionMotor = new VictorSPX(extenderConstants.extenderMotor);
  /** Creates a new extender. */
  public extender() {}

  public void extend() {
    extensionMotor.set(VictorSPXControlMode.PercentOutput, .15);
  }
  public void retract() {
    extensionMotor.set(VictorSPXControlMode.PercentOutput, -1);
  }
  public void stop() {
    extensionMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
