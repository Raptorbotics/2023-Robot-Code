// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import frc.robot.Constants.intakeConstants;

public class intake extends SubsystemBase {

  private VictorSPX motor1 = new VictorSPX(intakeConstants.intakeMotor1);
  private VictorSPX motor2 = new VictorSPX(intakeConstants.intakeMotor2);
  private boolean toggle = true;

  /** Creates a new intake. */
  public intake() {}

  public void start() {
      motor1.set(VictorSPXControlMode.PercentOutput, -intakeConstants.spinPercent);
      motor2.set(VictorSPXControlMode.PercentOutput, intakeConstants.spinPercent);
  }

  public void stop() {
    motor1.set(VictorSPXControlMode.PercentOutput, 0);
    motor2.set(VictorSPXControlMode.PercentOutput, 0);
  }

  public void changeToggle() {
    motor1.set(VictorSPXControlMode.PercentOutput, intakeConstants.spinPercent);
    motor2.set(VictorSPXControlMode.PercentOutput, -intakeConstants.spinPercent);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    motor1.set(VictorSPXControlMode.PercentOutput, -intakeConstants.spinPercent);
    motor2.set(VictorSPXControlMode.PercentOutput, intakeConstants.spinPercent);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
