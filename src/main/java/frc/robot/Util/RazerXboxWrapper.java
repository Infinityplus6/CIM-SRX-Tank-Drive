package frc.robot.Util;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Full-featured wrapper for Razer Raiju and Wolverine controllers.
 *
 * Features:
 * - Deadband filtering
 * - Automatic Y-axis inversion
 * - Trigger buttons and trigger axes
 * - D-Pad triggers
 * - Stick-click buttons
 * - Rumble support
 * - Access to raw controller when needed
 *
 * Note:
 * Most Razer paddle buttons (M1-M6) are not exposed separately through
 * standard Xbox/XInput APIs and are usually remapped to existing buttons.
 */
public class RazerXboxWrapper {

    private final XboxController m_controller;

    // Configuration

    private double m_deadband = 0.05;
    private double m_triggerThreshold = 0.5;

    // Face Buttons

    public final Trigger A;
    public final Trigger B;
    public final Trigger X;
    public final Trigger Y;

    // Bumpers

    public final Trigger LB;
    public final Trigger RB;

    // Stick Buttons

    public final Trigger LeftStickButton;
    public final Trigger RightStickButton;

    // Menu Buttons

    public final Trigger Start;
    public final Trigger Back;

    // Trigger Buttons

    public final Trigger LT;
    public final Trigger RT;

    // D-Pad Buttons

    public final Trigger POVUp;
    public final Trigger POVRight;
    public final Trigger POVDown;
    public final Trigger POVLeft;

    /**
     * Creates a controller wrapper.
     *
     * @param port Driver Station USB port
     */
    public RazerXboxWrapper(int port) {

        m_controller = new XboxController(port);

        // Face buttons
        A = new Trigger(m_controller::getAButton);
        B = new Trigger(m_controller::getBButton);
        X = new Trigger(m_controller::getXButton);
        Y = new Trigger(m_controller::getYButton);

        // Bumpers
        LB = new Trigger(m_controller::getLeftBumper);
        RB = new Trigger(m_controller::getRightBumper);

        // Stick buttons
        LeftStickButton = new Trigger(m_controller::getLeftStickButton);
        RightStickButton = new Trigger(m_controller::getRightStickButton);

        // Menu buttons
        Start = new Trigger(m_controller::getStartButton);
        Back = new Trigger(m_controller::getBackButton);

        // Trigger buttons
        LT = new Trigger(() -> getLeftTrigger() > m_triggerThreshold);
        RT = new Trigger(() -> getRightTrigger() > m_triggerThreshold);

        // D-Pad
        POVUp = new Trigger(() -> m_controller.getPOV() == 0);
        POVRight = new Trigger(() -> m_controller.getPOV() == 90);
        POVDown = new Trigger(() -> m_controller.getPOV() == 180);
        POVLeft = new Trigger(() -> m_controller.getPOV() == 270);
    }

    // Axis Getters

    public double getLeftX() {
        return MathUtil.applyDeadband(
                m_controller.getLeftX(),
                m_deadband);
    }

    public double getLeftY() {
        return -MathUtil.applyDeadband(
                m_controller.getLeftY(),
                m_deadband);
    }

    public double getRightX() {
        return MathUtil.applyDeadband(
                m_controller.getRightX(),
                m_deadband);
    }

    public double getRightY() {
        return -MathUtil.applyDeadband(
                m_controller.getRightY(),
                m_deadband);
    }

    public double getLeftTrigger() {
        return MathUtil.applyDeadband(
                m_controller.getLeftTriggerAxis(),
                m_deadband);
    }

    public double getRightTrigger() {
        return MathUtil.applyDeadband(
                m_controller.getRightTriggerAxis(),
                m_deadband);
    }

    // Raw Axis getters
    public double getRawLeftX() {
        return m_controller.getLeftX();
    }

    public double getRawLeftY() {
        return m_controller.getLeftY();
    }

    public double getRawRightX() {
        return m_controller.getRightX();
    }

    public double getRawRightY() {
        return m_controller.getRightY();
    }

    // Configs
    public void setDeadband(double deadband) {
        m_deadband = deadband;
    }

    public double getDeadband() {
        return m_deadband;
    }

    public void setTriggerThreshold(double threshold) {
        m_triggerThreshold = threshold;
    }

    public double getTriggerThreshold() {
        return m_triggerThreshold;
    }

    // Rumble
    public void setRumble(double power) {
        power = MathUtil.clamp(power, 0.0, 1.0);

        m_controller.setRumble(RumbleType.kLeftRumble, power);
        m_controller.setRumble(RumbleType.kRightRumble, power);
    }

    public void stopRumble() {
        setRumble(0.0);
    }

    // Advanced Access
    public XboxController getRawController() {
        return m_controller;
    }
}