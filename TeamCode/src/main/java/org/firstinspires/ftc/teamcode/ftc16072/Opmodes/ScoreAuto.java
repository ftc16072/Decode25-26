package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Autonomous
@SuppressWarnings("unused")
public class ScoreAuto extends QQOpmode {
    private Follower follower;
    private PathChain StarttoShootPos;
    private PathChain ShootPostoPickup1Pos;
    private PathChain Pickup1PostoPickedUp1Pos;
    private PathChain PickedUp1PostoShootPos;
    private PathChain ShootPostoParkPos;
    public boolean isRed = true;
    int step = 0;

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

public void start(){
    super.start();
    follower.setStartingPose(new Pose(88, 8, Math.toRadians(90)));
    StarttoShootPos = follower
            .pathBuilder()
            .addPath(
                    new BezierLine(new Pose(88.000, 8.000), new Pose(84.000, 18.000))
            )
            .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(robot.odoPods.turnToGoal(isRed,84,18)))
            .build();

    ShootPostoPickup1Pos = follower
            .pathBuilder()
            .addPath(
                    new BezierCurve(
                            new Pose(84.000, 18.000),
                            new Pose(90.000, 35.000),
                            new Pose(104.000, 35.000)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(70), Math.toRadians(0))
            .build();

    Pickup1PostoPickedUp1Pos = follower
            .pathBuilder()
            .addPath(
                    new BezierLine(new Pose(104.000, 35.000), new Pose(130.000, 35.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0))
            .build();

    PickedUp1PostoShootPos = follower
            .pathBuilder()
            .addPath(
                    new BezierCurve(
                            new Pose(130.000, 35.000),
                            new Pose(75.000, 38.000),
                            new Pose(84.000, 18.000)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(robot.odoPods.turnToGoal(isRed,84,18)))
            .build();

    ShootPostoParkPos = follower
            .pathBuilder()
            .addPath(
                    new BezierLine(new Pose(84.000, 18.000), new Pose(98.000, 18.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(90))
            .build();
}
public void loop(){
    super.loop();
    follower.update();
    telemetry.addData("Position", follower.getPose());
    switch(step){
        case 0:
            follower.followPath(StarttoShootPos,true);
            step++;
            break;
        case 1:
            if(!follower.isBusy()) {
                robot.transfer.moveToShooter();
                step++;
            }
            break;
        case 2:
            if(robot.outtake.isReady(telemetry)){
                robot.transfer.resetBothDown();
                step++;
            }
            break;
        case 3:
            robot.transfer.moveToShooter();
            if(!follower.isBusy()){
                follower.followPath(ShootPostoPickup1Pos);
            }
            break;
        case 4:
            //Add code here to turn on intake when it is added
            if(!follower.isBusy()){
                follower.followPath(Pickup1PostoPickedUp1Pos);

            }
    }

}


}
