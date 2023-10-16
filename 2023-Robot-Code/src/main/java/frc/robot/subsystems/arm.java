// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXSimCollection;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;

public class arm extends SubsystemBase {

	WPI_TalonFX armMotor = new WPI_TalonFX(armConstants.armMotor);

	TalonFXSimCollection armMotorSim = new TalonFXSimCollection(armMotor);

	int level = 0;

	public void setArmLength(double position) {
		//armMotor.set(ControlMode.MotionMagic, armMotor.getSelectedSensorPosition(), DemandType.ArbitraryFeedForward, -0.07);
	}
	/* 
	public void changeArmLength(double amount) {
		armMotor.set(ControlMode.Position, armMotor.getSelectedSensorPosition() + amount);
	}
	*/

	public void resetPosition() {
		armMotor.setSelectedSensorPosition(0);
	}

	public void increaseArmLength() {
		if(level == 0) {
			level += 1;
			armMotor.set(ControlMode.Position, 5000);
		} else if(level == 1) {
			level += 1;
			armMotor.set(ControlMode.Position, 10000);
		} else if(level == 2) {
			level += 1;
			armMotor.set(ControlMode.Position, 15000);
		} else if(level == 3) {
			level += 1;
			armMotor.set(ControlMode.Position, 17500);
		} else if(level == 4) {
			level += 1;
			armMotor.set(ControlMode.Position, 20000);
		} else if(level == 5) {
			level += 1;
			armMotor.set(ControlMode.Position, 25000);
		} else if(level == 6) {
			level += 1;
			armMotor.set(ControlMode.Position, 30000);
		} else if(level == 7) {
			level += 1;
			armMotor.set(ControlMode.Position, 35000);
		} else if(level == 8) {
			level += 1;
			armMotor.set(ControlMode.Position, 40000);
		}
		System.out.println("Level: " + level);
	}

	public void decreaseArmLength() {
		if(level == 1) {
			level -= 1;
			armMotor.set(ControlMode.Position, 0);
		} else if(level == 2) {
			level -= 1;
			armMotor.set(ControlMode.Position, 5000);
		} else if(level == 3) {
			level -= 1;
			armMotor.set(ControlMode.Position, 10000);
		} else if(level == 4) {
			level -= 1;
			armMotor.set(ControlMode.Position, 15000);
		} else if(level == 5) {
			level -= 1;
			armMotor.set(ControlMode.Position, 17500);
		} else if(level == 6) {
			level -= 1;
			armMotor.set(ControlMode.Position, 20000);
		} else if(level == 7) {
			level -= 1;
			armMotor.set(ControlMode.Position, 25000);
		} else if(level == 8) {
			level -= 1;
			armMotor.set(ControlMode.Position, 30000);
		} else if(level == 9) {
			level -= 1;
			armMotor.set(ControlMode.Position, 35000);
		}
		System.out.println("Level: " + level);
	}

	/*
	public void changeArmLength() {
		armMotor.set(ControlMode.Position, armMotor.getSelectedSensorPosition());
		System.out.println(armMotor.getSelectedSensorPosition());
	}*/

	public double getArmLength() {
		return armMotor.getSelectedSensorPosition();
	}

	public arm() {
		final ErrorCode resetStatus = armMotor.setSelectedSensorPosition(0);
		if(resetStatus != ErrorCode.valueOf(0)) {
			System.out.println("Something went wrong with resetting armMotor back to position: " + resetStatus);
		} else {
			System.out.println("ArmMotor Reset Status: " + resetStatus);
		}
		armMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
		armMotor.setInverted(true);
		armMotor.setNeutralMode(NeutralMode.Brake);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		System.out.println(armMotor.getSelectedSensorPosition());
	}

	public void simulationPeriodic() {
	}
}
