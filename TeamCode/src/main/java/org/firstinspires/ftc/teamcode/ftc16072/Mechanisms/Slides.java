package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;

public class Slides extends QQMechanism{
    DcMotor pivotMotor;
    DcMotor slideMotor;
    final double TICKS_PER_ROTATION = 2786.2;
    public Slides(){
        tests = Arrays.asList(
                new TestMotor("pivot_motor_up", pivotMotor, 0.1),
                new TestMotor("pivot_motor_down", pivotMotor, -0.1),
                new TestMotor("slide_out",slideMotor, 0.1),
                new TestMotor("slide_in",slideMotor, -0.1)
        );
    }
    @Override
    public void init(HardwareMap hardwareMap) {
        pivotMotor = hardwareMap.get(DcMotor.class,"pivot_motor");
        slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");
    }
    public void gotoAngle(double angle, AngleUnit angleUnit){
        double degrees = angleUnit.toDegrees(angle);
        double motorRotation = degrees/360;
        double motorTicks = motorRotation*TICKS_PER_ROTATION;
        pivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivotMotor.setTargetPosition((int) motorTicks);
        pivotMotor.setPower(double power);
    }
    public void gotoLength(double length, DistanceUnit distanceUnit){

    }
}
