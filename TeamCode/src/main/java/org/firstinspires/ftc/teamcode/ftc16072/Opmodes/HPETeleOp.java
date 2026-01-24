package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

@TeleOp
public class HPETeleOp extends QQOpmode {
    public static final double TRIGGER_THRESHOLD = 0.5;
    public double angleDegrees = 35;
    public boolean isRed = true;

    public void init_loop() {
        super.init_loop();
        if (gamepad1.x) {
            isRed = false;
        } else if (gamepad1.b) {
            isRed = true;
        }
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");

    }
    @Override
    public void start(){
        robot.odoPods.setPose(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
        super.start();
        robot.transfer.resetBothDown();
    }

    @Override
    public void loop() {
        super.loop();
        if ((gamepad1.right_trigger > TRIGGER_THRESHOLD)) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.TURBO);
        } else if ((gamepad1.right_bumper)) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.SLOW);
        } else {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.FAST);
        }
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");
        double bearingToTargetDegrees = robot.camera.getBearingToTargetDegrees(isRed);
        telemetry.addData("Bearing Degrees", bearingToTargetDegrees);

        double turnSpeed = calculateTurn(robot.odoPods.turnToGoal(isRed, robot.odoPods.getPose().getX(DistanceUnit.INCH), robot.odoPods.getPose().getY(DistanceUnit.INCH)));
        if (!gamepad1.left_bumper) {
            turnSpeed = gamepad1.right_stick_x;
            if (gamepad1.dpadUpWasPressed()) {
                angleDegrees += 2;
            } else if (gamepad1.dpadDownWasPressed()) {
                angleDegrees -= 2;
            }
        }
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, turnSpeed);
        telemetry.addData("Turn Speed", turnSpeed);
        robot.outtake.angleDegrees = robot.odoPods.changeHoodAngle(isRed,  robot.odoPods.getPose().getX(DistanceUnit.INCH), robot.odoPods.getPose().getY(DistanceUnit.INCH));


        if (gamepad1.x){
            robot.transfer.storageDown();
        }
        if (gamepad1.b) {
            if (robot.outtake.isReady(telemetry)) {
                robot.transfer.moveToShooter();
            } else {
                gamepad1.rumble(Gamepad.RUMBLE_DURATION_CONTINUOUS);
            }
        } else {
            gamepad1.stopRumble();
            robot.transfer.storeBall();
            robot.transfer.shooterDown();
        }
 //       if (gamepad1.xWasPressed()) {
 //           robot.transfer.moveToStorage(telemetry);
 //
 //       }
        if (gamepad1.y) {
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

        if ((gamepad1.left_trigger > TRIGGER_THRESHOLD)) {
            telemetry.addData("Spin up", "true");
            robot.outtake.spinUp();
        } else if ((gamepad1.right_stick_button)) {
            robot.outtake.stop();
        }


        if (gamepad1.y) {
            robot.controlHub.resetImu();
        }
        angleDegrees = robot.outtake.setAngle(angleDegrees, AngleUnit.DEGREES, telemetry);
        telemetry.addData("Can See AprilTag", robot.camera.isAprilTagVisible());

        if (gamepad1.y) {
            robot.controlHub.resetImu();
        }
        angleDegrees = robot.outtake.setAngle(angleDegrees, AngleUnit.DEGREES, telemetry);
        telemetry.addData("Can See AprilTag", robot.camera.isAprilTagVisible());
    }


    private double lastError = 0;
    private double sumErrors = 0;
    ElapsedTime timer = new ElapsedTime();

    private double calculateTurn(double bearingDegrees) {
        double KP = 0.02;
        double KI = 0;
        double KD = 0.005;
        double maxSpeed = 0.75;
/*
        if (bearingDegrees < -30){
            return -0.5;
        }
        if (bearingDegrees > 30){
            return 0.5;
        }
*/

        double error = bearingDegrees - robot.odoPods.getPose().getHeading(AngleUnit.DEGREES);
        double derivative = (error - lastError) / timer.seconds();
        double speed = (KP * error) + (KD * derivative);
        if (Math.abs(speed) > maxSpeed) {
            speed = maxSpeed * Math.signum(speed);
        }
        if (Math.abs(bearingDegrees) < 3) {
            speed = 0;
        }
        lastError = error;
        return speed;
    }

}



