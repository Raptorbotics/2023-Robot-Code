// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArmTeleop;
import frc.robot.commands.DriveTeleop;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;

/**
 * S
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

	// The robot's subsystems and commands are defined here...

	XboxController controller = new XboxController(Constants.Controller.m_controller);

	private Trigger xButton = new JoystickButton(controller, Constants.Controller.Buttons.m_xButton);
	private Trigger yButton = new JoystickButton(controller, Constants.Controller.Buttons.m_yButton);
	private Trigger bButton = new JoystickButton(controller, Constants.Controller.Buttons.m_bButton);
	private Trigger aButton = new JoystickButton(controller, Constants.Controller.Buttons.m_aButton);

	private Trigger rBumper = new JoystickButton(controller, Constants.Controller.Bumpers.m_rBumper);
	private Trigger lBumper = new JoystickButton(controller, Constants.Controller.Bumpers.m_lBumper);

	private POVButton UP = new POVButton(controller, 0);
	private POVButton DOWN = new POVButton(controller, 180);
	private POVButton LEFT = new POVButton(controller, 270);
	private POVButton RIGHT = new POVButton(controller, 90);

	private final drivetrain m_Drivetrain = new drivetrain();
	private final arm m_arm = new arm();

	final SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		m_Drivetrain.setDefaultCommand(new DriveTeleop(() -> controller.getLeftX(), () -> controller.getLeftY(), () -> controller.getRightX(), m_Drivetrain));

		SmartDashboard.putData(m_chooser);
		m_chooser.setDefaultOption("Auto Sequence 1", new frc.robot.Autonomous.AutonomousSequenceOne());

		configureBindings();
	}

	private void configureBindings() {

		// Configure the trigger bindings

		// ShoulderTeleop Keybinds
		//DOWN.whileTrue(new ShoulderTeleop("Manual Down", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Manual Down1
		//DOWN.and(rBumper).whileTrue(new ShoulderTeleop("Manual Down Slow", null, 0, 0, false, Robot.m_arm));
		//rBumper.and(aButton).onTrue(new ShoulderTeleop("Low", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined
																																																				// Low
		//rBumper.and(bButton).onTrue(new ShoulderTeleop("Medium", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined
																																																						// Medium
		//rBumper.and(yButton).onTrue(new ShoulderTeleop("High", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined
																																																					// High
		//rBumper.and(xButton).onTrue(new ShoulderTeleop("Default", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined
																																																						// High
		//UP.whileTrue(new ShoulderTeleop("Manual Up", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Manual Up
		//UP.and(rBumper).whileTrue(new ShoulderTeleop("Manual Up Slow", Robot.m_shoulder, 0, 0, false, Robot.m_arm));

		// ArmTelop Keybinds

		/*
		LEFT.whileTrue(new ArmTeleop("Manual Extend", Robot.m_arm, 0, 0));
		lBumper.and(aButton).onTrue(new ArmTeleop("Low", Robot.m_arm, 0, 0));
		lBumper.and(bButton).onTrue(new ArmTeleop("Medium", Robot.m_arm, 0, 0));
		lBumper.and(yButton).onTrue(new ArmTeleop("High", Robot.m_arm, 0, 0));
		lBumper.and(xButton).onTrue(new ArmTeleop("Default", Robot.m_arm, 0, 0));
		RIGHT.whileTrue(new ArmTeleop("Manual Retract", Robot.m_arm, 0, 0));
		*/
		// m_chooser.addOption("Auto Sequence 2", new
		// frc.robot..Autonomous.AutonomousSequences.AutonomousSequenceTwo());

	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An example command will be run in autonomous
		return m_chooser.getSelected();
		// }
	}

	public drivetrain getDriveTrain() {
		return m_Drivetrain;
	}
}
