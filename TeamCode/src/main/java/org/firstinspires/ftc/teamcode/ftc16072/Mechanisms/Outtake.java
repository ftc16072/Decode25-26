package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
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
    DcMotorEx outtakeMotor;
    Servo hoodServo;
    public double angleDegrees;
    TouchSensor limitSwitch;
    final double TEST_SPEED = 0.2;

    final double MIN_HOOD_SERVO_POSITION = 0.5;
    final double MAX_HOOD_SERVO_POSITION = .35;
    final double HOOD_POSITION = 0;


    final double SHOOTING_SPEED_DEGREES_PER_SECOND = 2500;
    int ballsShot;
    boolean wasLimitSwitchPressed;

    @Override
    public void init(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake_motor");

        limitSwitch = hardwareMap.get(TouchSensor.class, "outtake_switch");

        hoodServo = hardwareMap.get(Servo.class, "hood_servo");

        hoodServo.scaleRange(MIN_HOOD_SERVO_POSITION, MAX_HOOD_SERVO_POSITION);
        hoodServo.setDirection(Servo.Direction.REVERSE);





    }

    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestMotor("OuttakeMotor", outtakeMotor, TEST_SPEED),
                new TestServo("HoodServo", hoodServo, 1.0, 0), //fix numbers,
                new TestTouchSensor("outtakeSwitch", limitSwitch)
        );
    }

    public void spinUp() {
        outtakeMotor.setVelocity(SHOOTING_SPEED_DEGREES_PER_SECOND, AngleUnit.DEGREES);
    }

    public void stop() {
        outtakeMotor.setPower(0);
    }

    public boolean isReady(Telemetry telemetry) {
        telemetry.addData("OuttakeMotor", outtakeMotor.getVelocity());
        telemetry.addData("Left fast enough", outtakeMotor.getVelocity() >= .9 * SHOOTING_SPEED_DEGREES_PER_SECOND);

        return (outtakeMotor.getVelocity() >= (.9 * SHOOTING_SPEED_DEGREES_PER_SECOND));
    }

    /**
     * setAngle - sets the angle of the shooting wheels
     * @param angle - needs to be between 0 and 75 degrees
     * @param angleUnit - DEGREES or RADIANS
     * @param telemetry - where to send the telemetry
     */
    public double setAngle(double angle, AngleUnit angleUnit, Telemetry telemetry){
        double MIN_DEGREES = 0;
        double MAX_DEGREES = 75; //need to calculate
        angleDegrees = angleUnit.toDegrees(angle);
        if (angleDegrees < MIN_DEGREES){
            angleDegrees = MIN_DEGREES;
            telemetry.addData("angle change to", angleDegrees);
        } else if (angleDegrees > MAX_DEGREES){
            angleDegrees = MAX_DEGREES;
            telemetry.addData("angle change to", angleDegrees);
        }
        double servoPosiition = Range.scale(angleDegrees, MIN_DEGREES, MAX_DEGREES, MIN_HOOD_SERVO_POSITION, MAX_HOOD_SERVO_POSITION);


        hoodServo.setPosition(servoPosiition);
        return angleDegrees;
    }


    public int numberOfBallsShot(){
        if(!limitSwitch.isPressed() && wasLimitSwitchPressed){
            ballsShot++;
        }
        wasLimitSwitchPressed = limitSwitch.isPressed();
        return ballsShot;
    }

}
