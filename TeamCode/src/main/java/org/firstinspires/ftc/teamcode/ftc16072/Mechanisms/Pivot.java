package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestTouchSensor;

import java.util.Arrays;
import java.util.List;

public class Pivot extends QQMechanism{
    DcMotor pivot;
    TouchSensor limitSwitch;
    int desiredPosition = 0;
    final double TICKS_PER_MOTOR_ROTATION = 751.8; // for 223 RPM motor from GoBilda website
    final double TICKS_PER_SHAFT_ROTATION = 28 * TICKS_PER_MOTOR_ROTATION;

    @Override
    public void init(HardwareMap hardwareMap) {
        pivot = hardwareMap.get(DcMotor.class, "pivot");
        limitSwitch = hardwareMap.get(TouchSensor.class, "pivot limit switch");
        pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivot.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public List<QQTest> getTests(){
        return Arrays.asList(
                new TestMotor("pivot up",pivot,0.5),
                new TestMotor("pivot down",pivot,-0.5),

                new TestTouchSensor("switch",limitSwitch)
        );
    }
    public void moveToSwitch() {
        desiredPosition = (int)-TICKS_PER_SHAFT_ROTATION;
    }
    public void setDestinationAngleDegrees(double angle){
        double rotations = angle/360;
        desiredPosition  =  ((int) (rotations*TICKS_PER_SHAFT_ROTATION));
    }
    public void manualPositionChange (int changeAmount){
        desiredPosition += changeAmount;
    }

    @Override
    public void update(Telemetry telemetry) {
/*
        double kP = .0005;
        super.update(telemetry);

        if(limitSwitch.isPressed()  && desiredPosition <= 0){
            pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            pivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            desiredPosition = 0;
        }
        int currentPosition = pivot.getCurrentPosition();
        int error = desiredPosition - currentPosition;
        double power = kP * error;
        if (Math.abs(error) < 50){
            power = 0;
        }
        pivot.setPower(power);
        telemetry.addData("power",power);
        telemetry.addData("current_pos", currentPosition);
        telemetry.addData("desrired", desiredPosition);

 */
    }

    public void stop (){
        //desiredPosition = pivot.getCurrentPosition();
        pivot.setPower(0);
    }
    public void up (){
        pivot.setPower(1.0);
    }
    public void down (){
        pivot.setPower(-1.0);
    }

}
