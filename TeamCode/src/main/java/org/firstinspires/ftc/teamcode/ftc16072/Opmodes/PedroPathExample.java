package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class PedroPathExample extends QQOpmode {
    Follower follower = Constants.createFollower(hardwareMap);
    private PathChain examplePath;

    private PathChain makeFirstPathChain(){
        Pose startPose = new Pose(8,36);
        Pose poseStep1 = new Pose(34, 36);
        Pose poseStep2 = new Pose(34, 12);
        Pose poseStep3 = new Pose( 8, 12);
        follower.setStartingPose(startPose);
        PathBuilder builder = Follower.pathBuilder();
        builder.addPath(new BezierLine(startPose, poseStep1))
                .setConstantHeadingInterpolation(Math.toRadians(0));
        builder.addPath(new BezierLine(poseStep1, poseStep2))
                .setConstantHeadingInterpolation(Math.toRadians(0));
        builder.addPath(new BezierLine(poseStep2, poseStep3))
                .setConstantHeadingInterpolation(Math.toRadians(0));
        return builder.build();
    }
    /**
     * This runs the OpMode, updating the Follower as well as printing out the debug statements to
     * the Telemetry, as well as the Panels.
     */
    @Override
    public void loop() {
        super.loop();
        follower.update();

        if (follower.atParametricEnd()) {
            follower.followPath(examplePath, true);
        }
    }

    @Override
    public void start() {
        examplePath = makeFirstPathChain();
        follower.followPath(examplePath);
    }
}
