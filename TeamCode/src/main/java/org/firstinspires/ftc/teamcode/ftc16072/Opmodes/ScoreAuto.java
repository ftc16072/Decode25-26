package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Autonomous
@SuppressWarnings("unused")
public class ScoreAuto extends QQOpmode {
    private Follower follower;
    private PathChain StarttoShootPos;
    private PathChain ShootPostoPickup1Pos;
    private PathChain Pickup1PostoPickedup1Pos;
    private PathChain Pickedup1PostoPickup1Pos;
    private PathChain Pickup1PostoShootPos;
    public PathChain ShootPostoPickup2Pos;
    public PathChain Pickup2PostoPickedup2Pos;
    public PathChain Pickedup2PostoPickup2Pos;
    public PathChain Pickup2PostoShootPos;
    public PathChain ShootPostoPickup3Pos;
    public PathChain Pickup3PostoPickedup3Pos;
    public PathChain Pickedup3PostoPickup3Pos;
    public PathChain Pickup3PostoShootPos;
    public PathChain ShootPostoParkPos;
    public boolean isRed = true;

    public enum Step {
        START_TO_SHOOT,
        SHOOT_FIRST_BALL1,
        SHOOT_SECOND_BALL1,
        SHOOT_TO_PICKUP1,
        PICKUP1_TO_PICKEDUP1,
        PICKEDUP1_TO_PICKUP1,
        PICKUP1_TO_SHOOT,
        SHOOT_FIRST_BALL2,
        SHOOT_SECOND_BALL2,
        SHOOT_TO_PICKUP2,
        PICKUP2_TO_PICKEDUP2,
        PICKEDUP2_TO_PICKUP2,
        PICKUP2_TO_SHOOT,
        SHOOT_FIRST_BALL3,
        SHOOT_SECOND_BALL3,
        SHOOT_TO_PICKUP3,
        PICKUP3_TO_PICKEDUP3,
        PICKEDUP3_TO_PICKUP3,
        PICKUP3_TO_SHOOT,
        SHOOT_FIRST_BALL4,
        SHOOT_SECOND_BALL4,
        SHOOT_TO_PARK,
        DONE

    }



    public void init() {
        super.init();
        follower = Constants.createFollower(hardwareMap);
    }
    public void init_loop() {
        super.init_loop();
        if (gamepad1.x) {
            isRed = false;
        } else if (gamepad1.b) {
            isRed = true;
        }
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");

    }

public void start() {
    super.start();
    if (isRed) {
        follower.setStartingPose(new Pose(88, 8, Math.toRadians(90)));
        StarttoShootPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(88.000, 8.000), new Pose(84.000, 18.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(robot.odoPods.turnToGoal(isRed, 84, 18)))
                .build();
        ShootPostoPickup1Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(84.000, 18.000),
                                new Pose(104.000, 42.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        Pickup1PostoPickedup1Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(104.000, 42.000), new Pose(130.000, 42.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        Pickedup1PostoPickup1Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(130.000, 42.000), new Pose(104.000, 42.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
        Pickup1PostoShootPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(104.000, 42.000),
                                new Pose(84.000, 18.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(robot.odoPods.turnToGoal(isRed, 84, 18)))
                .build();
        ShootPostoPickup2Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(84.000, 18.000), new Pose(104.000, 64.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        Pickup2PostoPickedup2Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(104.000, 64.000), new Pose(130.000, 64.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        Pickedup2PostoPickup2Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(130.000, 64.000), new Pose(104.000, 64.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        Pickup2PostoShootPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(104.000, 64.000), new Pose(84.000, 18.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(70))
                .build();

        ShootPostoPickup3Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(84.000, 18.000), new Pose(104.000, 87.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        Pickup3PostoPickedup3Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(104.000, 87.000), new Pose(130.000, 87.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        Pickedup3PostoPickup3Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(130.000, 87.000), new Pose(104.000, 87.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        Pickup3PostoShootPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(104.000, 87.000), new Pose(84.000, 18.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(70))
                .build();

        ShootPostoParkPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(84.000, 18.000), new Pose(98.000, 18.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();
    } else {
        follower.setStartingPose(new Pose(56, 8, Math.toRadians(90)));
        StarttoShootPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(56.000, 8.000), new Pose(60.000, 18.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(robot.odoPods.turnToGoal(isRed, 60, 18)))
                .build();

        ShootPostoPickup1Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(60.000, 18.000),
                                new Pose(40.000, 35.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        Pickup1PostoPickedup1Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(40.000, 35.000), new Pose(14.000, 35.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        Pickedup1PostoPickup1Pos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.000, 35.000), new Pose(40.000, 35.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();

        Pickup1PostoShootPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(40.000, 35.000), new Pose(60.000, 18.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(robot.odoPods.turnToGoal(isRed, 60, 18)))
                .build();

        ShootPostoParkPos = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(60.000, 18.000), new Pose(46.000, 18.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(90))
                .build();
    }
    robot.transfer.resetBothDown();
    robot.outtake.spinUp();

}

private Step currentStep = Step.START_TO_SHOOT;
    private final ElapsedTime stepTimer = new ElapsedTime();

public void loop() {
    super.loop();
    follower.update();
    telemetry.addData("Position", follower.getPose());
    telemetry.addData("Step", currentStep);
    // In your loop or update method:
    switch (currentStep) {
        case START_TO_SHOOT:
            if(!follower.isBusy()) {
                follower.followPath(StarttoShootPos, true);
                nextStep(Step.SHOOT_FIRST_BALL1);
            }
            break;
        case SHOOT_FIRST_BALL1:
        case SHOOT_FIRST_BALL2:
        case SHOOT_FIRST_BALL3:
        case SHOOT_FIRST_BALL4:
            if (!follower.isBusy() && robot.outtake.isReady(telemetry)) {

                if(stepTimer.seconds()<1) {
                    robot.transfer.moveToShooter();
                }else if(stepTimer.seconds() <2){
                robot.transfer.resetBothDown();}
                else{
                    if (currentStep == Step.SHOOT_FIRST_BALL1)
                        nextStep(Step.SHOOT_SECOND_BALL1);
                    else if (currentStep == Step.SHOOT_FIRST_BALL2)
                        nextStep(Step.SHOOT_SECOND_BALL2);
                    else if (currentStep == Step.SHOOT_FIRST_BALL3)
                        nextStep(Step.SHOOT_SECOND_BALL3);
                    else
                        nextStep(Step.SHOOT_SECOND_BALL4);
                }
            }else {
                stepTimer.reset();
            }
            break;

        case SHOOT_SECOND_BALL1:
        case SHOOT_SECOND_BALL2:
        case SHOOT_SECOND_BALL3:
        case SHOOT_SECOND_BALL4:
            if (robot.outtake.isReady(telemetry)) {
                if(stepTimer.seconds()<1){robot.transfer.moveToShooter();}
                else {
                    if (currentStep == Step.SHOOT_SECOND_BALL1)
                        nextStep(Step.SHOOT_TO_PICKUP1);
                    else if (currentStep == Step.SHOOT_SECOND_BALL2)
                        nextStep(Step.SHOOT_TO_PICKUP2);
                    else if (currentStep == Step.SHOOT_SECOND_BALL3)
                        nextStep(Step.SHOOT_TO_PICKUP3);
                    else
                        nextStep(Step.SHOOT_TO_PARK);
                }
            }else {
                stepTimer.reset();
            }
            break;

        case SHOOT_TO_PICKUP1:
            if (!follower.isBusy()) {
                follower.followPath(ShootPostoPickup1Pos);
                robot.transfer.resetBothDown();
                nextStep(Step.PICKUP1_TO_PICKEDUP1);
            }
            break;

        case PICKUP1_TO_PICKEDUP1:
            if (!follower.isBusy()) {
                follower.followPath(Pickup1PostoPickedup1Pos);
                nextStep(Step.PICKEDUP1_TO_PICKUP1);
            }
            break;
        case PICKEDUP1_TO_PICKUP1:
            if (!follower.isBusy()) {
                follower.followPath(Pickedup1PostoPickup1Pos);
                nextStep(Step.PICKUP1_TO_SHOOT);
            }
            break;

        case PICKUP1_TO_SHOOT:
            if (!follower.isBusy()) {
                follower.followPath(Pickup1PostoShootPos, true);
                nextStep(Step.SHOOT_FIRST_BALL2);
            }
            break;
        case SHOOT_TO_PICKUP2:
            if (!follower.isBusy()) {
                follower.followPath(ShootPostoPickup2Pos);
                robot.transfer.resetBothDown();
                nextStep(Step.PICKUP2_TO_PICKEDUP2);
            }
            break;

        case PICKUP2_TO_PICKEDUP2:
            if (!follower.isBusy()) {
                follower.followPath(Pickup2PostoPickedup2Pos);
                nextStep(Step.PICKEDUP2_TO_PICKUP2);
            }
            break;
        case PICKEDUP2_TO_PICKUP2:
            if (!follower.isBusy()) {
                follower.followPath(Pickedup2PostoPickup2Pos);
                nextStep(Step.PICKUP2_TO_SHOOT);
            }
            break;

        case PICKUP2_TO_SHOOT:
            if (!follower.isBusy()) {
                follower.followPath(Pickup2PostoShootPos, true);
                nextStep(Step.SHOOT_FIRST_BALL3);
            }
            break;
        case SHOOT_TO_PICKUP3:
            if (!follower.isBusy()) {
                follower.followPath(ShootPostoPickup3Pos);
                robot.transfer.resetBothDown();
                nextStep(Step.PICKUP3_TO_PICKEDUP3);
            }
            break;
        case PICKUP3_TO_PICKEDUP3:
            if (!follower.isBusy()) {
                follower.followPath(Pickup3PostoPickedup3Pos);
                nextStep(Step.PICKEDUP3_TO_PICKUP3);
            }
            break;
        case PICKEDUP3_TO_PICKUP3:
            if (!follower.isBusy()) {
                follower.followPath(Pickedup3PostoPickup3Pos);
                nextStep(Step.PICKUP3_TO_SHOOT);}
            break;
        case PICKUP3_TO_SHOOT:
            if (!follower.isBusy()) {
                follower.followPath(Pickup3PostoShootPos, true);
                nextStep(Step.SHOOT_FIRST_BALL4);
            }
            break;
        case SHOOT_TO_PARK:
            if (!follower.isBusy() && robot.outtake.isReady(telemetry)) {
                follower.followPath(ShootPostoParkPos, true);
                robot.transfer.resetBothDown();
                robot.outtake.stop();
                nextStep(Step.DONE);
            }
            break;
        case DONE:
            //Do nothing
            break;
    }


}

    private void nextStep(Step next) {
        currentStep = next;
        stepTimer.reset();
    }
}

