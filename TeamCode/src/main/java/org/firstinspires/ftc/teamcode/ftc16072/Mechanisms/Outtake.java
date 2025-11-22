package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
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
    public double angleDegrees;
    TouchSensor limitSwitch;
    final double TEST_SPEED = 0.2;

    final double MIN_LEFT_SERVO_POSITION = 0.70;
    final double MAX_LEFT_SERVO_POSITION = .97;
    final double MIN_RIGHT_SERVO_POSITION = 0;
    final double MAX_RIGHT_SERVO_POSITION = MIN_RIGHT_SERVO_POSITION + (MAX_LEFT_SERVO_POSITION - MIN_LEFT_SERVO_POSITION);

    final double SHOOTING_SPEED_DEGREES_PER_SECOND = 2500;
    int ballsShot;
    boolean wasLimitSwitchPressed;

    @Override
    public void init(HardwareMap hardwareMap) {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtake_left_motor");
        rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "outtake_right_motor");
        rightOuttakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        limitSwitch = hardwareMap.get(TouchSensor.class, "outtake_switch");

        leftOuttakeServo = hardwareMap.get(Servo.class, "outtake_left_servo");
        rightOuttakeServo = hardwareMap.get(Servo.class, "outtake_right_servo");

        leftOuttakeServo.scaleRange(MIN_LEFT_SERVO_POSITION, MAX_LEFT_SERVO_POSITION);
        leftOuttakeServo.setDirection(Servo.Direction.REVERSE);

        rightOuttakeServo.scaleRange(MIN_RIGHT_SERVO_POSITION, MAX_RIGHT_SERVO_POSITION);

    }

    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestMotor("leftMotor", leftOuttakeMotor, TEST_SPEED),
                new TestMotor("rightMotor", rightOuttakeMotor, TEST_SPEED),
                new TestServo("leftServo", leftOuttakeServo, 1.0, 0),
                new TestServo("rightServo", rightOuttakeServo, 1.0, 0),
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

    public boolean isReady(Telemetry telemetry) {
        telemetry.addData("LeftMotor", leftOuttakeMotor.getVelocity());
        telemetry.addData("RightMotor", rightOuttakeMotor.getVelocity());
        telemetry.addData("Left fast enough", leftOuttakeMotor.getVelocity() >= .9 * SHOOTING_SPEED_DEGREES_PER_SECOND);
        telemetry.addData("Right fast enough", rightOuttakeMotor.getVelocity() >= .9 * SHOOTING_SPEED_DEGREES_PER_SECOND);

        return ((leftOuttakeMotor.getVelocity() >= (.9 * SHOOTING_SPEED_DEGREES_PER_SECOND)) &&
                (rightOuttakeMotor.getVelocity() >= (.9 * SHOOTING_SPEED_DEGREES_PER_SECOND)));
    }

    /**
     * setAngle - sets the angle of the shooting wheels
     * @param angle - needs to be between 0 and 75 degrees
     * @param angleUnit - DEGREES or RADIANS
     * @param telemetry - where to send the telemetry
     */
    public void setAngle(double angle, AngleUnit angleUnit, Telemetry telemetry){
        double MIN_DEGREES = 0;
        double MAX_DEGREES = 75;
        angleDegrees = angleUnit.toDegrees(angle);
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
