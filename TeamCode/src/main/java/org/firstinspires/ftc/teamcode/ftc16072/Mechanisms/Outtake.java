package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
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
    DcMotorEx outtakeMotor;
    Servo hoodServo;
    private double angleDegrees;
    TouchSensor limitSwitch;
    final double TEST_SPEED = 0.2;

    final double MIN_HOOD_SERVO_POSITION = 0.70;
    final double MAX_HOOD_SERVO_POSITION = .35;
    final double HOOD_POSITION = 0;


    final double SHOOTING_SPEED_TICKS_PER_SECOND = 1600;
    int ballsShot;
    boolean wasLimitSwitchPressed;

    @Override
    public void init(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake_motor");
        outtakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        limitSwitch = hardwareMap.get(TouchSensor.class, "outtake_switch");

        hoodServo = hardwareMap.get(Servo.class, "hood_servo");

        hoodServo.scaleRange(MAX_HOOD_SERVO_POSITION,MIN_HOOD_SERVO_POSITION);
        hoodServo.setDirection(Servo.Direction.REVERSE);





    }

    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestMotor("OuttakeMotor", outtakeMotor, TEST_SPEED),
                new TestServo("HoodServo", hoodServo, MIN_HOOD_SERVO_POSITION, MAX_HOOD_SERVO_POSITION),
                new TestTouchSensor("outtakeSwitch", limitSwitch)
        );
    }

    public void spinUp() {
        outtakeMotor.setVelocity(SHOOTING_SPEED_TICKS_PER_SECOND);
    }

    public void spinDown() {
        if (outtakeMotor.getVelocity() < 100) {
            outtakeMotor.setPower(-0.2);
        }
    }

    public void spinUpIfStopped() {
        if (outtakeMotor.getVelocity() > -100) {
            spinUp();
        }
    }

    public void stop() {
        outtakeMotor.setPower(0);
    }

    public boolean isReady(Telemetry telemetry) {
        boolean fastEnough = outtakeMotor.getVelocity() >= .97 * SHOOTING_SPEED_TICKS_PER_SECOND;
        boolean notTooFast = outtakeMotor.getVelocity() <= 1.03 * SHOOTING_SPEED_TICKS_PER_SECOND;
        telemetry.addData("Speed fast enough", fastEnough);
        telemetry.addData("Speed not too fast", notTooFast);

        return fastEnough && notTooFast;
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
        double servoPosition = Range.scale(angleDegrees, MIN_DEGREES, MAX_DEGREES, 0, 1);

        telemetry.addData("angle", angleDegrees);
        hoodServo.setPosition(servoPosition);
        return angleDegrees;
    }


    public int numberOfBallsShot(){
        if(!limitSwitch.isPressed() && wasLimitSwitchPressed){
            ballsShot++;
        }
        wasLimitSwitchPressed = limitSwitch.isPressed();
        return ballsShot;
    }

    @Override
    public void update(Telemetry telemetry) {
        super.update(telemetry);
        telemetry.addData("OuttakeSpeed", outtakeMotor.getVelocity());
    }
}
