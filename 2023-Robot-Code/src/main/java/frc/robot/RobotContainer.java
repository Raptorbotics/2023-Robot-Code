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
import frc.robot.commands.intakeTeleop;
import frc.robot.commands.extenderTeleop;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.extender;

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
	private final intake m_intake = new intake();
	private final extender m_extender = new extender();


	final SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		m_Drivetrain.setDefaultCommand(new DriveTeleop(() -> controller.getLeftX(), () -> controller.getLeftY(), () -> controller.getRightX(), m_Drivetrain));
		configureBindings();
	}

	private void configureBindings() {

		// Configure the trigger bindings


		LEFT.whileTrue(new extenderTeleop(m_extender, "retract"));
		RIGHT.whileTrue(new extenderTeleop(m_extender, "extend"));
		// ArmTelop Keybinds
		aButton.onTrue(new intakeTeleop(m_intake, "toggle"));
		bButton.onTrue(new intakeTeleop(m_intake, "stop"));

		
		lBumper.onTrue(new ArmTeleop(m_arm, "decrease"));
		xButton.onTrue(new ArmTeleop(m_arm, "reset"));
		rBumper.onTrue(new ArmTeleop(m_arm, "increase"));
		
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
