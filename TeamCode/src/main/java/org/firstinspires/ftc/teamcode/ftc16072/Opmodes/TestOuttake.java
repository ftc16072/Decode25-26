package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class TestOuttake extends QQOpmode{
    double angleDegrees = 0;
    public void loop(){
        super.loop();
        if (gamepad1.dpadUpWasPressed()){
            angleDegrees += 5;
        }
        else if(gamepad1.dpadDownWasPressed()){
            angleDegrees -= 5;
        }
        if(gamepad1.aWasPressed()){
            robot.outtake.spinUp();
        }else if(gamepad1.bWasPressed()){
            robot.outtake.stop();
        }
        telemetry.addData("isReady", robot.outtake.isReady(telemetry));

        telemetry.addData("Angle shooter", angleDegrees);
        robot.outtake.setAngle(angleDegrees, AngleUnit.DEGREES, telemetry);
    }
}
