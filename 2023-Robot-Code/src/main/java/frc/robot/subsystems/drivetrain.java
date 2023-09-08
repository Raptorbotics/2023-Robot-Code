// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class drivetrain extends SubsystemBase {

	/** Creates a new ExampleSubsystem. */
	private CANSparkMax frontLeftMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.frontLeftMotorOne, MotorType.kBrushless);
	private CANSparkMax frontLeftMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.frontLeftMotorTwo, MotorType.kBrushless);

	private CANSparkMax frontRightMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.frontRightMotorOne, MotorType.kBrushless);
	private CANSparkMax frontRightMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.frontRightMotorTwo, MotorType.kBrushless);

	private CANSparkMax backLeftMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.backLeftMotorOne, MotorType.kBrushless);
	private CANSparkMax backLeftMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.backLeftMotorTwo, MotorType.kBrushless);

	private CANSparkMax backRightMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.backRightMotorOne, MotorType.kBrushless);
	private CANSparkMax backRightMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.backRightMotorTwo, MotorType.kBrushless);

	MotorControllerGroup m_Left = new MotorControllerGroup(frontLeftMotorOne, frontLeftMotorTwo, backLeftMotorOne, backLeftMotorTwo);
	MotorControllerGroup m_Right = new MotorControllerGroup(frontRightMotorOne, frontRightMotorTwo, backRightMotorOne, backRightMotorTwo);

	private RelativeEncoder FLM1Encoder = frontLeftMotorOne.getEncoder();
	private RelativeEncoder FLM2Encoder = frontLeftMotorTwo.getEncoder();
	private RelativeEncoder BLM1Encoder = backLeftMotorOne.getEncoder();
	private RelativeEncoder BLM2Encoder = backLeftMotorOne.getEncoder();
	private RelativeEncoder FRM1Encoder = frontRightMotorOne.getEncoder();
	private RelativeEncoder FRM2Encoder = frontRightMotorOne.getEncoder();
	private RelativeEncoder BRM1Encoder = backRightMotorOne.getEncoder();
	private RelativeEncoder BRM2Encoder = backRightMotorOne.getEncoder();

	public double getMotorPosition() {
		return FLM1Encoder.getPosition();
	}

	public drivetrain() {

		Robot.drive = new DifferentialDrive(m_Left, m_Right);
	}

	public void setMotorSpeed(double xAxis, double yAxis, double zAxis) {
		Robot.drive.arcadeDrive(yAxis, zAxis);
	}

	public boolean exampleCondition() {
		// Query some boolean state, such as a digital sensor.
		return false;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}

	public void MotorTest() {

	}
}
