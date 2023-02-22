// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class arm extends SubsystemBase {
	private double ArmAngle = 0;

	public void setArmAngle(double value) {
		ArmAngle = value;
	}

	public double getArmAngle() {
		return ArmAngle;
	}

	public void reduceArmAngle(double amount) {
		ArmAngle = ArmAngle - amount;
	}

	public void increaseArmAngle(double amount) {
		ArmAngle = ArmAngle + amount;
	}

	PWMVictorSPX armMotor = new PWMVictorSPX(Constants.Motors.arm.armMotor);

	public void setMotorSpeed(double axis) {
		armMotor.set(axis);
	}

	public arm() {
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

}
