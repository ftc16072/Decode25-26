package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;


import java.util.Arrays;
import java.util.List;

public class Slides extends QQMechanism {
    public static final double SLIDE_SPEED = .5;
    DcMotor slideMotor;
    final double DIAMETER_PULLEY_MM = 38.2;
    final double CIRCUMFRENCE_PULLEY_MM = DIAMETER_PULLEY_MM * Math.PI;
    final double SLIDES_TICKS_PER_ROTATION = 384.5;

    public Slides() {
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //slideMotor.setTargetPosition(0);
        //slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public List<QQTest> getTests(){
        return Arrays.asList(
                new TestMotor("slide out",slideMotor,0.2),
                new TestMotor("slide in",slideMotor,-0.2)
        );
    }

    public void gotoLength(double length, DistanceUnit distanceUnit) {
        double length_MM = distanceUnit.toMm(length);
        double rotations = length_MM / CIRCUMFRENCE_PULLEY_MM;
        int ticks = (int) Math.round(rotations * SLIDES_TICKS_PER_ROTATION);
        //there are so many ticks in a rotation and moters use ticks not rotations so u need to translate them.
        slideMotor.setTargetPosition(ticks);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void manualIn() {
        slideMotor.setPower(-SLIDE_SPEED);
    }
    public void manualOut() {
        slideMotor.setPower(SLIDE_SPEED);
    }
    public void stop() {
        slideMotor.setPower(0);
    }
}
