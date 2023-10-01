// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class drivetrain extends SubsystemBase {

	/** Creates a new ExampleSubsystem. */
	/* 
	private PWMSparkMax frontLeftMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.frontLeftMotorOne);
	private PWMSparkMax frontLeftMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.frontLeftMotorTwo);

	private PWMSparkMax frontRightMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.frontRightMotorOne);
	private PWMSparkMax frontRightMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.frontRightMotorTwo);

	private PWMSparkMax backLeftMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.backLeftMotorOne);
	private PWMSparkMax backLeftMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.backLeftMotorTwo);

	private PWMSparkMax backRightMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.backRightMotorOne);
	private PWMSparkMax backRightMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.backRightMotorTwo);
	*/
	
	public CANSparkMax frontLeftMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.frontLeftMotorOne, MotorType.kBrushless);
	public CANSparkMax frontLeftMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.frontLeftMotorTwo, MotorType.kBrushless);

	public CANSparkMax frontRightMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.frontRightMotorOne, MotorType.kBrushless);
	public CANSparkMax frontRightMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.frontRightMotorTwo, MotorType.kBrushless);

	public CANSparkMax backLeftMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.backLeftMotorOne, MotorType.kBrushless);
	public CANSparkMax backLeftMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.backLeftMotorTwo, MotorType.kBrushless);

	public CANSparkMax backRightMotorOne = new CANSparkMax(Constants.Motors.Drivetrain.backRightMotorOne, MotorType.kBrushless);
	public CANSparkMax backRightMotorTwo = new CANSparkMax(Constants.Motors.Drivetrain.backRightMotorTwo, MotorType.kBrushless);

	MotorControllerGroup m_Left = new MotorControllerGroup(frontLeftMotorOne, frontLeftMotorTwo, backLeftMotorOne, backLeftMotorTwo);
	MotorControllerGroup m_Right = new MotorControllerGroup(frontRightMotorOne, frontRightMotorTwo, backRightMotorOne, backRightMotorTwo);
	

	public drivetrain() {

		Robot.drive = new DifferentialDrive(m_Left, m_Right);
	}

	public void setMotorSpeed(double xAxis, double yAxis, double zAxis) {
		Robot.drive.arcadeDrive(yAxis, -zAxis);
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
