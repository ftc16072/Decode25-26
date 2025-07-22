package org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.auto_paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Opmodes.QQOpmode;
import org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.constants.LConstants;

//@TeleOp
public class splines extends QQOpmode {
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
                        new BezierCurve(
                                new Point(8.000, 80.000, Point.CARTESIAN),
                                new Point(11.735, 0.994, Point.CARTESIAN),
                                new Point(53.580, 87.116, Point.CARTESIAN),
                                new Point(18.298, 115.359, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        line2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(18.298, 115.359, Point.CARTESIAN),
                                new Point(71.000, 67.000, Point.CARTESIAN),
                                new Point(90.895, 129.481, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
        //Picking up third sample to the park position for this randomization
    }

    //                            new Point(9.000, 105.000, Point.CARTESIAN),
    private final Pose startPose = new Pose(8, 80, Math.toRadians(0));

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

            Constants.setConstants(FConstants.class, LConstants.class);
            follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
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