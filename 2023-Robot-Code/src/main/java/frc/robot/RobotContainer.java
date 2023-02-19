// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot;
import frc.robot.commands.Drive;
import frc.robot.commands.ShoulderTeleop;

/**S
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
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

	private void configureBindings() {
		// Schedule `ExampleCommand` when `exampleCondition` changes to `true`
		Robot.m_Drivetrain.setDefaultCommand(new Drive());
		//Robot.m_shoulder.setDefaultCommand(new ShoulderTeleop());

		// Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
		// cancelling on release.
	}

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer() {
		// Configure the trigger bindings

		//ShoulderTeleop Keybinds
		DOWN.whileTrue(new ShoulderTeleop("Manual Down"));
		rBumper.and(aButton).onTrue(new ShoulderTeleop("Low"));
		rBumper.and(bButton).onTrue(new ShoulderTeleop("Medium"));
		rBumper.and(yButton).onTrue(new ShoulderTeleop("High"));
		UP.whileTrue(new ShoulderTeleop("Manual Up"));
		configureBindings();
	}
	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	/**public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    /**return Autos.exampleAuto(m_exampleSubsystem);*/
	//}
}
