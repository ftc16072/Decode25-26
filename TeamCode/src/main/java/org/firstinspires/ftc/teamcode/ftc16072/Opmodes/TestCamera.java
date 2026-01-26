package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//@TeleOp
public class TestCamera extends QQOpmode{
    boolean isRed = true;
    public void loop(){
        super.loop();
        if(gamepad1.left_bumper){
            isRed = false;
        }
        if(gamepad1.right_bumper){
            isRed = true;
        }
        telemetry.addData("isRed", isRed);
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("Tag Visible", robot.camera.isAprilTagVisible());
        if(robot.camera.isAprilTagVisible()){
            telemetry.addData("X", robot.camera.getPosXInches());
            telemetry.addData("Y", robot.camera.getPosYInches());
            telemetry.addData("H", robot.camera.getHeadingDegrees());
            telemetry.addData("bearing to target", robot.camera.getBearingToTargetDegrees(isRed));
            telemetry.addData("distance to target", robot.camera.getDistanceToTargetInches(isRed));

        }
    }
}
