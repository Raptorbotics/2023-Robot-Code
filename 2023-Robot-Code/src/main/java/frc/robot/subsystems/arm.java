// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;

public class arm extends SubsystemBase {
  PWMVictorSPX armMotor = new PWMVictorSPX(Constants.Motors.arm.armMotor);
  /** Creates a new arm. */
  public arm() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setArmMotorSpeed(double axis){
    armMotor.set(axis);
  }
}
