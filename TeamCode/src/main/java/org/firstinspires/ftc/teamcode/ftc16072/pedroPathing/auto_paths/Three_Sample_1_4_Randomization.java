package org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.auto_paths;

import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.util.Constants;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.teamcode.ftc16072.Opmodes.QQOpmode;
import org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.LConstants;

@Autonomous
public class Three_Sample_1_4_Randomization extends QQOpmode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;

    /**
     * This is the variable where we store the state of our auto.
     * It is used by the pathUpdate method.
     */
    private int pathState;

    private PathChain line1, line2, line3, line4, line5, line6, line7;
    public static PathBuilder builder = new PathBuilder();

    public void buildPaths() {
        line1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(9.000, 105.000, Point.CARTESIAN),
                                new Point(21.000, 113.500, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(23))
                .build();
        //Starting point to picking up first sample

        line2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(21.000, 113.500, Point.CARTESIAN),
                                new Point(20.000, 124.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(23), Math.toRadians(-45))
                .build();
        //Picking up first sample to placing position
        line3 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(20.000, 124.000, Point.CARTESIAN),
                                new Point(21.000, 123.500, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(23))
                .build();
        //Placing position to pick up second sample


        line4 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(21.000, 123.500, Point.CARTESIAN),
                                new Point(20.000, 124.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(23), Math.toRadians(-45))
                .build();
        //Picking up second sample to placing position
        line5 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(20.000, 124.000, Point.CARTESIAN),
                                new Point(21.000, 130.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(34))
                .build();
        //Placing position to pick up third sample
       line6 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(21.000, 130.000, Point.CARTESIAN),
                                new Point(20.000, 124.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(34), Math.toRadians(-45))
                .build();
        //Picking up third sample to Placing Position
        line7 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(20.000, 124.000, Point.CARTESIAN),
                                new Point(12.000, 12.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0))
                .build();
//Picking up third sample to the park position for this randomization
    }

    //                            new Point(9.000, 105.000, Point.CARTESIAN),
    private final Pose startPose = new Pose(9, 105, Math.toRadians(0));

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(line1);
                setPathState(1);
                break;
            case 1:

                if (!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(line2, true);
                    setPathState(2);
                }
                break;
            case 2:

                if (!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(line3, true);
                    setPathState(3);
                }
                break;
            case 3:

                if (!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(line4, true);
                    setPathState(4);
                }
                break;
            case 4:

                if (!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(line5, true);
                    setPathState(5);
                }
                break;
            case 5:

                if (!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(line6, true);
                    setPathState(6);
                }
                break;
            case 6:

                if (!follower.isBusy()) {
                    /* Score Preload */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(line7, true);
                    setPathState(7);
                }
                break;
            case 7:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Level 1 Ascent */

                    /* Set the state to a Case we won't use or define, so it just stops running an new paths */
                    setPathState(-1);
                }
                break;
        }
    }

        @Override
        public void init () {
            pathTimer = new Timer();
            opmodeTimer = new Timer();
            opmodeTimer.resetTimer();

            Constants.setConstants(org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.FConstants.class, org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.LConstants.class);
            follower = new Follower(hardwareMap, org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.FConstants.class, org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.LConstants.class);
            follower.setStartingPose(startPose);
        buildPaths();
        }

        /** This method is called continuously after Init while waiting for "play". **/
        @Override
        public void init_loop () {
        }

        /** This method is called once at the start of the OpMode.
         * It runs all the setup actions, including building paths and starting the path system **/
        @Override
        public void start () {
            opmodeTimer.resetTimer();
            setPathState(0);
        }


        @Override
        public void loop () {
            follower.update();
            autonomousPathUpdate();
            telemetry.addData("Path State", pathState);
            telemetry.addData("Position", follower.getPose().toString());
            telemetry.update();
        }
}