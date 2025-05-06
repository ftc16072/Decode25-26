package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;
import java.util.List;

public class MecanumDrive extends QQMechanism{
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    double speedMultiplier = 1.0;
    public enum Speed {
        SLOW,
        NORMAL,
        FAST,
        TURBO
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        List<DcMotor> motors = Arrays.asList(frontLeft, frontRight, backLeft, backRight);
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
    public void setSpeed (Speed speed){
        switch(speed){
            case NORMAL:
                speedMultiplier = 0.5;
                break;
            case SLOW:
                speedMultiplier = 0.1;
                break;
            case FAST:
                speedMultiplier = 0.75;
                break;
            case TURBO:
                speedMultiplier = 1;
                break;


        }
    }
    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestMotor("back_right", backRight, 0.1),
                new TestMotor("back_left",backLeft, 0.1),
                new TestMotor("front_right",frontRight, 0.1),
                new TestMotor("front_left", frontLeft, 0.1)
        );
    }
    public void move (double forwardSpeed, double strafeRightSpeed, double turnClockwiseSpeed){
        double frontLeftPower = forwardSpeed + strafeRightSpeed + turnClockwiseSpeed;
        double frontRightPower = forwardSpeed - strafeRightSpeed - turnClockwiseSpeed;
        double backRightPower = forwardSpeed + strafeRightSpeed - turnClockwiseSpeed;
        double backLeftPower = forwardSpeed - strafeRightSpeed + turnClockwiseSpeed;

        setPowers(frontLeftPower,frontRightPower,backRightPower,backLeftPower);

    }
    public void stop(){
        move(0,0,0);
    }

    private void setPowers( double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower){
        double maxSpeed = 1.0;

        frontLeftPower *= speedMultiplier;
        frontRightPower *= speedMultiplier;
        backLeftPower *= speedMultiplier;
        backRightPower *= speedMultiplier;

        maxSpeed = Math.max(Math.abs(frontLeftPower), maxSpeed);
        maxSpeed = Math.max(Math.abs(frontRightPower), maxSpeed);
        maxSpeed = Math.max(Math.abs(backRightPower), maxSpeed);
        maxSpeed = Math.max(Math.abs(backLeftPower), maxSpeed);

        backLeftPower /= maxSpeed;
        backRightPower /= maxSpeed;
        frontLeftPower /= maxSpeed;
        frontRightPower /= maxSpeed;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);


    }


}
