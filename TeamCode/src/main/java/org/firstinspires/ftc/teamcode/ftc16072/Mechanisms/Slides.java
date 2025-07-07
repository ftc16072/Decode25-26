package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;

public class Slides extends QQMechanism {
    DcMotor slideMotor;
    final double DIAMETER_PULLEY_MM = 38.2;
    final double CIRCUMFRENCE_PULLEY_MM = DIAMETER_PULLEY_MM * Math.PI;
    final double SLIDES_TICKS_PER_ROTATION = 384.5;

    public Slides() {
        tests = Arrays.asList(
                new TestMotor("slide_out", slideMotor, 0.1),
                new TestMotor("slide_in", slideMotor, -0.1)
        );
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //changed run mode to run_to_position to set to target position.
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");
    }

    public void gotoLength(double length, DistanceUnit distanceUnit) {
        double length_MM = distanceUnit.toMm(length);
        double rotations = length_MM / CIRCUMFRENCE_PULLEY_MM;
        int ticks = (int) Math.round(rotations * SLIDES_TICKS_PER_ROTATION);
        //there are so many ticks in a rotation and moters use ticks not rotations so u need to translate them.
        slideMotor.setTargetPosition(ticks);

    }
}
