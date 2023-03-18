// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class arm extends SubsystemBase {

	private double ArmLength = 0;

	public void setArmLength(double value) {
		ArmLength = value;
	}

	public double getArmLength() {
		return ArmLength;
	}

	public void reduceArmLength(double amount) {
		ArmLength = ArmLength - amount;
	}

	public void increaseArmLength(double amount) {
		ArmLength = ArmLength + amount;
	}

	PWMVictorSPX armMotor = new PWMVictorSPX(Constants.Motors.arm.armMotor);

	public void setMotorSpeed(double axis) {
		armMotor.set(axis);
	}

	public void stopMotor() {
		armMotor.stopMotor();
	}

	public arm() {}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
