package org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = "front_left_motor";
        FollowerConstants.leftRearMotorName = "back_left_motor";
        FollowerConstants.rightFrontMotorName = "front_right_motor";
        FollowerConstants.rightRearMotorName = "back_right_motor";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.mass = 5.7;

        FollowerConstants.xMovement = 69.12032894585829;
        FollowerConstants.yMovement = 58.534758404539765;

        FollowerConstants.forwardZeroPowerAcceleration = -34.47695089584519;
        FollowerConstants.lateralZeroPowerAcceleration = -53.151976321061966;

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.1,0,0.015,0);
        FollowerConstants.headingPIDFCoefficients.setCoefficients(2,0,0.1,0);

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.001,0,0.00005,0.6,0);

        FollowerConstants.zeroPowerAccelerationMultiplier = 3;
        FollowerConstants.centripetalScaling = 0.00002;

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
