package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestOdo;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestTouchSensor;

import java.util.Arrays;
import java.util.List;

public class Pivot extends QQMechanism{
    DcMotor pivot;
    TouchSensor limitSwitch;
    int desiredPosition = 0;
    int offset;
    final double TICKS_PER_MOTOR_ROTATION = 751.8; // for 223 RPM motor from GoBilda website
    final double TICKS_PER_SHAFT_ROTATION = 28 * TICKS_PER_MOTOR_ROTATION;

    @Override
    public void init(HardwareMap hardwareMap) {
        pivot = hardwareMap.get(DcMotor.class, "pivot");
        limitSwitch = hardwareMap.get(TouchSensor.class, "pivot limit switch");
    }
    @Override
    public List<QQTest> getTests(){
        return Arrays.asList(
                new TestMotor("pivot",pivot,0.5),
                new TestTouchSensor("switch",limitSwitch)
        );
    }
    public void moveToSwitch() {
        if (limitSwitch.isPressed()) {
            offset = pivot.getCurrentPosition();
            desiredPosition = offset;
        }else{
            desiredPosition = offset - 100;
        }
    }
    public void setDestinationAngleDegrees(double angle){
        double rotations = angle/360;
        desiredPosition  =  ((int) (rotations*TICKS_PER_SHAFT_ROTATION));
    }

    @Override
    public void update(Telemetry telemetry) {
        double kP = .1;
        super.update(telemetry);
        int currentPosition = pivot.getCurrentPosition() - offset;
        int error = desiredPosition - currentPosition;
        double power = kP * error;
        pivot.setPower(power);
    }

    public void stop (){
        desiredPosition = pivot.getCurrentPosition() - offset;
    }

}
