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
import frc.robot.commands.ShoulderTeleop;
import frc.robot.commands.SolenoidTeleop;

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

	public Trigger xButton = new JoystickButton(controller, Constants.Controller.Buttons.m_xButton);
	public Trigger yButton = new JoystickButton(controller, Constants.Controller.Buttons.m_yButton);
	public Trigger bButton = new JoystickButton(controller, Constants.Controller.Buttons.m_bButton);
	public Trigger aButton = new JoystickButton(controller, Constants.Controller.Buttons.m_aButton);

	public Trigger rBumper = new JoystickButton(controller, Constants.Controller.Bumpers.m_rBumper);
	public Trigger lBumper = new JoystickButton(controller, Constants.Controller.Bumpers.m_lBumper);

	public POVButton UP = new POVButton(controller, 0);
	public POVButton DOWN = new POVButton(controller, 180);
	public POVButton LEFT = new POVButton(controller, 270);
	public POVButton RIGHT = new POVButton(controller, 90);

	public double GetDriverRawAxis(int axis) {
		return controller.getRawAxis(axis);
	}

	final SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Configure the trigger bindings

		// ShoulderTeleop Keybinds
		DOWN.whileTrue(new ShoulderTeleop("Manual Down", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Manual Down1
		rBumper.and(aButton).onTrue(new ShoulderTeleop("Low", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined Low
		rBumper.and(bButton).onTrue(new ShoulderTeleop("Medium", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined Medium
		rBumper.and(yButton).onTrue(new ShoulderTeleop("High", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined High
		rBumper.and(xButton).onTrue(new ShoulderTeleop("Default", Robot.m_shoulder, 0, 0, false, Robot.m_arm)); // Predetermined High
		UP.whileTrue(new ShoulderTeleop("Manual Up", Robot.m_shoulder, 0, 0,  false, Robot.m_arm)); // Manual Up

		// ArmTelop Keybinds

		LEFT.whileTrue(new ArmTeleop("Manual Extend", Robot.m_arm, 0, 0));
		lBumper.and(aButton).onTrue(new ArmTeleop("Low", Robot.m_arm, 0, 0));
		lBumper.and(bButton).onTrue(new ArmTeleop("Medium", Robot.m_arm, 0, 0));
		lBumper.and(yButton).onTrue(new ArmTeleop("High", Robot.m_arm, 0, 0));
		lBumper.and(xButton).onTrue(new ArmTeleop("Default", Robot.m_arm, 0, 0));
		RIGHT.whileTrue(new ArmTeleop("Manual Retract", Robot.m_arm, 0, 0));

		//Solenoid keybind
		aButton.and(rBumper.negate()).onTrue(new SolenoidTeleop(Robot.solenoidObject));
		configureBindings();
	}

	private void configureBindings() {
		// Robot.m_shoulder.setDefaultCommand(new ShoulderTeleop());

		m_chooser.setDefaultOption("Auto Sequence 1", new frc.robot.Autonomous.AutonomousSequenceOne());
		// m_chooser.addOption("Auto Sequence 2", new
		// frc.robot..Autonomous.AutonomousSequences.AutonomousSequenceTwo());
		SmartDashboard.putData(m_chooser);
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
}
