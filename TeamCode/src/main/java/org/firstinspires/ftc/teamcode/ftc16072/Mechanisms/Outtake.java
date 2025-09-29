package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestServo;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestTouchSensor;

import java.util.Arrays;
import java.util.List;

public class Outtake extends QQMechanism {
    DcMotorEx leftOuttakeMotor;
    DcMotorEx rightOuttakeMotor;
    Servo leftOuttakeServo;
    Servo rightOuttakeServo;

    TouchSensor limitSwitch;
    final double TEST_SPEED = 0.2;
    final double ON_POSITION = 0.2; //todo: figure out number
    final double OFF_POSITION = 0.7; //todo: figure out number

    final double MIN_SERVO_POSITION = 0.4; // todo find number
    final double MAX_SERVO_POSITION = 0.8; // todo fund number

    final double SHOOTING_SPEED_DEGREES_PER_SECOND = 4000; //todo: figure out number
    int ballsShot;
    boolean wasLimitSwitchPressed;

    @Override
    public void init(HardwareMap hardwareMap) {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "left_Outtake");
        rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "right_Outtake");
        leftOuttakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        limitSwitch = hardwareMap.get(TouchSensor.class, "outtake_switch");

        leftOuttakeServo = hardwareMap.get(Servo.class, "left_Angle");
        rightOuttakeServo = hardwareMap.get(Servo.class, "right_Angle");
        //leftOuttakeServo.setPosition();
        rightOuttakeServo.setDirection(Servo.Direction.REVERSE);
        leftOuttakeServo.scaleRange(MIN_SERVO_POSITION, MAX_SERVO_POSITION);
        rightOuttakeServo.scaleRange(MIN_SERVO_POSITION, MAX_SERVO_POSITION);

    }

    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestMotor("leftMotor", leftOuttakeMotor, TEST_SPEED),
                new TestMotor("rightMotor", rightOuttakeMotor, TEST_SPEED),
                new TestServo("leftServo", leftOuttakeServo, ON_POSITION, OFF_POSITION),
                new TestServo("rightServo", rightOuttakeServo, ON_POSITION, OFF_POSITION),
                new TestTouchSensor("outtakeSwitch", limitSwitch)
        );
    }

    public void spinUp() {
        leftOuttakeMotor.setVelocity(SHOOTING_SPEED_DEGREES_PER_SECOND, AngleUnit.DEGREES);
        rightOuttakeMotor.setVelocity(SHOOTING_SPEED_DEGREES_PER_SECOND, AngleUnit.DEGREES);
    }

    public void stop() {
        leftOuttakeMotor.setPower(0);
        rightOuttakeMotor.setPower(0);
    }

    public boolean isReady() {
        return ((leftOuttakeMotor.getVelocity() >= .9 * SHOOTING_SPEED_DEGREES_PER_SECOND) &&
                (rightOuttakeMotor.getVelocity() >= .9 * SHOOTING_SPEED_DEGREES_PER_SECOND));
    }

    public void setAngle(double angle, AngleUnit angleUnit, Telemetry telemetry){
        double MIN_DEGREES = 40; //todo find number
        double MAX_DEGREES = 80; // todo find number
        double angleDegrees = angleUnit.toDegrees(angle);
        if ((angleDegrees < MIN_DEGREES) ||
                (angleDegrees > MAX_DEGREES)){
            telemetry.addData("angle is not included in range",angleDegrees);
        }
        angleDegrees = Range.clip(angleDegrees,MIN_DEGREES,MAX_DEGREES);

        leftOuttakeServo.setPosition(Range.scale(angleDegrees, MIN_DEGREES, MAX_DEGREES, 0, 1.0));
        rightOuttakeServo.setPosition(Range.scale(angleDegrees, MIN_DEGREES, MAX_DEGREES, 0, 1.0));
    }
    public int numberOfBallsShot(){
        if(!limitSwitch.isPressed() && wasLimitSwitchPressed){
            ballsShot++;
        }
        wasLimitSwitchPressed = limitSwitch.isPressed();
        return ballsShot;
    }

}
