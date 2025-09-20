package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class AprilTagsDriveFieldRelative extends QQOpmode{
    public boolean isRed = true;
    public void init_loop(){
        super.init_loop();
        if(gamepad1.x){
            isRed = false;
        }else if(gamepad1.b){
            isRed = true;
        }
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");
    }
    public void loop(){
        super.loop();
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");
        double bearingToTargetDegrees = robot.camera.getBearingToTargetDegrees(isRed);
        telemetry.addData("Bearing Degrees", bearingToTargetDegrees);
    }
}
