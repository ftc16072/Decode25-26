package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;

public class LeadScrew extends QQMechanism {
    DcMotor leadScrewMotor;
    final double LEAD_SCREW_PITCH_MM = 2;
    final double LEAD_SCREW_PULSES_PER_ROTATION = 145.1; // 1150 RPM from GoBilda

    public LeadScrew() {
        tests = Arrays.asList(
                new TestMotor("slide_out", leadScrewMotor, 0.1),
                new TestMotor("slide_in", leadScrewMotor, -0.1)
        );
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //changed run mode to run_to_position to set to target position.
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        leadScrewMotor = hardwareMap.get(DcMotor.class, "lead_screw_motor");
    }

    public void gotoLength(double length, DistanceUnit distanceUnit) {
        double length_MM = distanceUnit.toMm(length);
        double rotations = length_MM / LEAD_SCREW_PITCH_MM;
        int ticks = (int) Math.round(rotations * LEAD_SCREW_PULSES_PER_ROTATION);
        //there are so many ticks in a rotation and moters use ticks not rotations so u need to translate them.
        leadScrewMotor.setTargetPosition(ticks);
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
