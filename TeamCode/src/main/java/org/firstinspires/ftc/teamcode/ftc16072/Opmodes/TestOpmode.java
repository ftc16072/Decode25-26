package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class TestOpmode extends QQOpmode {
    public Robot robot = new Robot();
    public double angleDegrees = 0;
    @Override
    public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
        super.loop();

        if (gamepad1.x) {
            robot.outtake.spinUp();
        }

        if (gamepad1.y){
            robot.outtake.stop();
        }



        if (gamepad1.dpadUpWasPressed()){
            angleDegrees += 5;
        }
        else if(gamepad1.dpadDownWasPressed()){
            angleDegrees -= 5;
        }
        angleDegrees = robot.outtake.setAngle(angleDegrees, AngleUnit.DEGREES,telemetry);


        if (gamepad1.a) {
            robot.transfer.moveBallToShooter();
        }

        if (gamepad1.b){
            robot.transfer.storeBall();
        }

        if(gamepad1.dpad_left){
            robot.transfer.shooterDown();
        }

        if(gamepad1.dpad_right){
            robot.transfer.storageDown();
        }



    }


    }

