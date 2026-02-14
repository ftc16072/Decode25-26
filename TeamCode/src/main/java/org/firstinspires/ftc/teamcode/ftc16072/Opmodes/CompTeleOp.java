package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

@TeleOp
public class CompTeleOp extends QQOpmode {
    public static final double TRIGGER_THRESHOLD = 0.5;
    public double angleDegrees = 35;

    boolean storageUp;
    boolean shooterUp;

    @Override
    public void start(){
        //FIXME: remove line
        robot.odoPods.setPose(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
        super.start();
        shooterUp=false;
        storageUp=false;
        robot.transfer.setPosition(shooterUp,storageUp);
    }

    @Override
    public void loop() {
        telemetry.addData("X Inch", robot.odoPods.getXInches());
        telemetry.addData("Y Inch", robot.odoPods.getYInches());
        telemetry.addData("Heading (deg)", robot.odoPods.getHeadingDegrees());
        if(robot.camera.isAprilTagVisible()){
            telemetry.addLine("OdoPod Reset");
//            telemetry.addData("AprilTag X", robot.camera.getPosXInches());
//            telemetry.addData("AprilTag Y", robot.camera.getPosYInches());
//            telemetry.addData("AprilTag H", robot.camera.getHeadingDegrees());
//            telemetry.addData("AprilTag bearing", robot.camera.getBearingToTargetDegrees(isRed));
            //robot.odoPods.setPose(new Pose2D(DistanceUnit.INCH, robot.camera.getPosXInches(), robot.camera.getPosYInches(), AngleUnit.DEGREES, robot.camera.getHeadingDegrees()));
        }
        super.loop();
        if ((gamepad1.right_trigger > TRIGGER_THRESHOLD)) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.TURBO);
        } else if ((gamepad1.right_bumper)) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.SLOW);
        } else {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.FAST);
        }




        telemetry.addData("Alliance", isRed ? "Red" : "Blue");
        //telemetry.addData("Target Angle", robot.odoPods.turnToGoal(isRed, robot.odoPods.getPose().getX(DistanceUnit.INCH), robot.odoPods.getPose().getY(DistanceUnit.INCH)));

        double bearingToTargetDegrees = robot.camera.getBearingToTargetDegrees(isRed);
        double distanceToTargetInches = robot.camera.getDistanceToTargetInches(isRed);
        telemetry.addData("Bearing Degrees", bearingToTargetDegrees);
        telemetry.addData("Distance to Target", distanceToTargetInches);
        double turnSpeed = calculateTurn(bearingToTargetDegrees);
//        double turnSpeed = calculateTurn(robot.odoPods.turnToGoal(isRed, robot.odoPods.getPose().getX(DistanceUnit.INCH), robot.odoPods.getPose().getY(DistanceUnit.INCH)));
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
        // set angle to robot.odoPods.changeHoodAngle(isRed,  robot.odoPods.getPose().getX(DistanceUnit.INCH), robot.odoPods.getPose().getY(DistanceUnit.INCH));

        if (gamepad1.triangle) {
            storageUp=true;
        }
        if (gamepad1.cross) {
            storageUp=false;
        }
        if (gamepad1.left_trigger > TRIGGER_THRESHOLD) {
            if (robot.outtake.isReady(telemetry)) {
                shooterUp=true;
            } else {
                gamepad1.rumble(Gamepad.RUMBLE_DURATION_CONTINUOUS);
            }
        } else {
            gamepad1.stopRumble();
            shooterUp=false;
        }
        if ((gamepad1.left_stick_button)) {
            telemetry.addData("Spin up", "true");
            robot.outtake.spinUp();
        } else if ((gamepad1.right_stick_button)) {
            robot.outtake.stop();
        }


        if (gamepad1.right_stick_button) {
            robot.controlHub.resetImu();
        }
        angleDegrees = robot.outtake.setAngle(angleDegrees, AngleUnit.DEGREES, telemetry);
        telemetry.addData("Hood Target Angle", robot.outtake.setAngle(angleDegrees, AngleUnit.DEGREES, telemetry));
        telemetry.addData("Can See Target AprilTag", robot.camera.canSeeTargetAprilTag(isRed));


        if (gamepad1.square) {
            robot.intake.intake();
        } else if (gamepad1.circle) {
            robot.intake.eject();
        }else {
            robot.intake.stop();
        }
        if(gamepad1.touchpad){
            robot.outtake.stop();
        }

        robot.transfer.setPosition(shooterUp,storageUp);
        manipulatorLoop();

    }
    public void manipulatorLoop(){
        if (gamepad2.b) {
            robot.outtake.stop();
        }
        else if (gamepad2.dpad_down) {
            robot.outtake.spinDown();
        }
        else if (gamepad2.dpad_up) {
            robot.outtake.spinUpIfStopped();
        }

    }

    private double lastError = 0;
    private double sumErrors = 0;
    ElapsedTime timer = new ElapsedTime();

    private double calculateTurn(double bearingDegrees) {
        double KP = 0.02;
        double KI = 0;
        double KD = 0.005;
        double maxSpeed = 0.25;  //0.75
/*
        if (bearingDegrees < -30){
            return -0.5;
        }
        if (bearingDegrees > 30){
            return 0.5;
        }
*/


        double error = bearingDegrees + 3;
        double derivative = (error - lastError) / timer.seconds();
        timer.reset();
        double speed = (KP * error) + (KD * derivative);
        if (Math.abs(speed) > maxSpeed) {
            speed = maxSpeed * Math.signum(speed);
        }
        if (Math.abs(bearingDegrees) < 1) {
            speed = 0;
        }
        lastError = error;
        telemetry.addData("bearingDegrees", bearingDegrees);
        telemetry.addData("heading", robot.odoPods.getPose().getHeading(AngleUnit.DEGREES));
        telemetry.addData("error", error);

        return speed;
    }

}



