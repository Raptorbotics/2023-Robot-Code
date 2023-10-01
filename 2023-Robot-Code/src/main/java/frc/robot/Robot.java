// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DriveTeleop;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

	private Command m_autonomousCommand;
	public static drivetrain m_Drivetrain = new drivetrain();

	public static DifferentialDrive drive;
	public static arm m_arm = new arm();

	private CANSparkMax frontLeftMotorOne = m_Drivetrain.frontLeftMotorOne;
	private CANSparkMax frontLeftMotorTwo = m_Drivetrain.frontLeftMotorTwo;

	private CANSparkMax frontRightMotorOne = m_Drivetrain.frontRightMotorOne;
	private CANSparkMax frontRightMotorTwo = m_Drivetrain.frontRightMotorTwo;

	private CANSparkMax backLeftMotorOne = m_Drivetrain.backLeftMotorOne;
	private CANSparkMax backLeftMotorTwo = m_Drivetrain.backLeftMotorTwo;

	private CANSparkMax backRightMotorOne = m_Drivetrain.backRightMotorOne;
	private CANSparkMax backRightMotorTwo = m_Drivetrain.backRightMotorTwo;

	// MUST BE LAST THING INSTANTIATED
	public static RobotContainer m_robotContainer = new RobotContainer();
	

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any
	 * initialization code.
	 */
	@Override
	public void robotInit() {
		// Instantiate our RobotContainer. This will perform all our button bindings,
		// and put our
		// autonomous chooser on the dashboard.
		m_robotContainer = new RobotContainer();

		//CameraServer.startAutomaticCapture();
	}

	/**
	 * This function is called every 20 ms, no matter the mode. Use this for items
	 * like diagnostics
	 * that you want ran during disabled, autonomous, teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and
	 * SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
		// Runs the Scheduler. This is responsible for polling buttons, adding
		// newly-scheduled
		// commands, running already-scheduled commands, removing finished or
		// interrupted commands,
		// and running subsystem periodic() methods. This must be called from the
		// robot's periodic
		// block in order for anything in the Command-based framework to work.
		CommandScheduler.getInstance().run();
	}

	/** This function is called once each time the robot enters Disabled mode. */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	/**
	 * This autonomous runs the autonomous command selected by your
	 * {@link RobotContainer} class.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_robotContainer.getAutonomousCommand();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.schedule();
		}
	}

	/** This function is called periodically during autonomous. */
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		// Robot.m_Compressor.setDefaultCommand(new compressorCommand(m_Compressor, 7));

		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/** This function is called periodically during operator control. */
	@Override
	public void teleopPeriodic() {
		Robot.m_Drivetrain.setDefaultCommand(new DriveTeleop(Constants.Predetermined.Drive.teleop, 0, 0, 0, 0));
	}

	@Override
	public void testInit() {
		// Cancels all running commands at the start of test mode.
		CommandScheduler.getInstance().cancelAll();
	}

	/** This function is called periodically during test mode. */
	@Override
	public void testPeriodic() {
	}

	/** This function is called once when the robot is first started up. */
	@Override
	public void simulationInit() {
		REVPhysicsSim.getInstance().addSparkMax(frontLeftMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(frontLeftMotorTwo, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(frontRightMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(frontRightMotorTwo, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backLeftMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backLeftMotorTwo, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backRightMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backRightMotorTwo, DCMotor.getNEO(1));
	}

	/** This function is called periodically whilst in simulation. */
	@Override
	public void simulationPeriodic() {
		REVPhysicsSim.getInstance().run();
	}
}
