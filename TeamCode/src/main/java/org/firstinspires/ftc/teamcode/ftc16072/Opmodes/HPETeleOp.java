package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

    public class HPETeleOp extends QQOpmode {
        public static final double TRIGGER_THRESHOLD = 0.5;
        @Override
        public void init() {


        }

        @Override
        public void loop() {
            super.loop();
            if((gamepad1.right_trigger > TRIGGER_THRESHOLD)) {
                robot.mecanumDrive.setSpeed(MecanumDrive.Speed.TURBO);
            }
            else if((gamepad1.right_bumper)){
                robot.mecanumDrive.setSpeed(MecanumDrive.Speed.SLOW);
            }
            else {
                robot.mecanumDrive.setSpeed(MecanumDrive.Speed.FAST);
            }
            nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            if (gamepad1.a) {
                robot.transfer.moveBallToShooter();
            }else if (gamepad1.dpad_left){
                robot.transfer.shooterDown();
            }

            if (gamepad1.b){
                robot.transfer.storeBall();
            }else if(gamepad1.dpad_right){
                robot.transfer.storageDown();
            }

            /*(if((gamepad1.y)){
                robot.intake.intake();
            }
            else if((gamepad1.x)){
                robot.intake.eject();
            }
            else {
                robot.intake.stop();
            */

            if((gamepad1.b)){
                robot.outtake.spinUp();
            }
            else {
                robot.outtake.stop();
            }



        }



    }



