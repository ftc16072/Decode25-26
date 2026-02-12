package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(8.6)
            .forwardZeroPowerAcceleration(-35.8003)
            .lateralZeroPowerAcceleration(-60.0476)
            .centripetalScaling(0.005)
//          .translationalPIDFCoefficients(new PIDFCoefficients(0.2, 0, 0.03, 0.0))
//          .translationalPIDFSwitch(3)
//          .headingPIDFCoefficients(new PIDFCoefficients(1, 0, 0.01, 0))
//          .headingPIDFSwitch(1)
//          .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.1,0.0,0.08,0.6,0.0))
//          .drivePIDFSwitch(0.5)
    ;


    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(0.2)
            .leftFrontMotorName("front_left_motor")
            .leftRearMotorName("back_left_motor")
            .rightFrontMotorName("front_right_motor")
            .rightRearMotorName("back_right_motor")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(60.2359)
            .yVelocity(49.7593);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-84)
            .strafePodX(-168)
            .distanceUnit(DistanceUnit.MM)
            .hardwareMapName("pinpoint")
            .encoderResolution(
                    GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD
            )
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);

    public static PathConstraints pathConstraints = new PathConstraints(
            0.995,
            0.1,
            0.1,
            0.007,
            500,
            3,
            10,
            1
    );

    public static Follower createFollower(HardwareMap hardwareMap) {
        Follower follower =  new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
        follower.setMaxPower(driveConstants.getMaxPower());
        follower.updateConstants();
        return follower;
    }

}