// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants;
import frc.robot.subsystems.drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;

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
  int dPadValue = controller.getPOV();



  public double GetDriverRawAxis(int axis){
    return controller.getRawAxis(axis);
  }
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
     if(dPadValue == 0) {
      System.out.println("Hello you're pressing the UP dpad button!!!r pressing the UP dpad button");;
     }
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`


    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
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
