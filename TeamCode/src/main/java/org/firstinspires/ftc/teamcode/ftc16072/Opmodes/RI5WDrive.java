package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class RI5WDrive extends QQOpmode{
    public void loop(){
        super.loop();
        //nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        if(gamepad1.x){
            robot.intake.intake();
        }else{
            robot.intake.stop();
        }
        if(gamepad1.a){
            robot.intake.outtake();
        }
        int balls = robot.intake.numberOfBalls();
        telemetry.addData("Number of Balls", balls);

        if (gamepad1.b){
            robot.intake.slow();
        }
    }
}