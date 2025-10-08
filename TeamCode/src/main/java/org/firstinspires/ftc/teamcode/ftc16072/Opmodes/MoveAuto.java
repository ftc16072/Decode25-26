package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Autonomous
@SuppressWarnings("unused")
public class MoveAuto extends QQOpmode {
    private Follower follower;
    private PathChain moveForward;
    int step = 0;

    public void init() {
        super.init();
        follower = Constants.createFollower(hardwareMap);
        moveForward = follower.pathBuilder()
                .addPath(
                        // Path 1
                        new BezierLine(new Pose(56.000, 9.000), new Pose(56.000, 30.000))
                )
                .setConstantHeadingInterpolation(Math.toRadians(270))
                .build();
    }
    public void start(){
        super.start();
        follower.setStartingPose(new Pose(56, 9, Math.toRadians(270)));
    }
    public void loop(){
        super.loop();
        follower.update();
        telemetry.addData("Position", follower.getPose());
        switch(step){
            case 0:
                follower.followPath(moveForward);
                step = 1;
                break;
            case 1:
                break;
        }

    }


}
