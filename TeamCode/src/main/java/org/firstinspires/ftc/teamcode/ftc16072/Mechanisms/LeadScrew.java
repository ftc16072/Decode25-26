package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;

public class LeadScrew extends QQMechanism {
    DcMotor leadScrewMotor;
    TouchSensor leadScrewResetSwitch;
    final double LEAD_SCREW_PITCH_MM = 2;
    final double LEAD_SCREW_PULSES_PER_ROTATION = 145.1; // 1150 RPM from GoBilda
    final double MAX_EXTENSION = 1000;//NEEDS TO BE TUNED

    public LeadScrew() {

        //changed run mode to run_to_position to set to target position.
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        leadScrewResetSwitch = hardwareMap.get(TouchSensor.class, "lead screw reset switch");
        leadScrewMotor = hardwareMap.get(DcMotor.class, "lead_screw_motor");
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tests = Arrays.asList(
                new TestMotor("slide_out", leadScrewMotor, 0.1),
                new TestMotor("slide_in", leadScrewMotor, -0.1)
        );
    }

    public void gotoLength(double length, DistanceUnit distanceUnit) {
        double length_MM = distanceUnit.toMm(length);
        double rotations = length_MM / LEAD_SCREW_PITCH_MM;
        int ticks = (int) Math.round(rotations * LEAD_SCREW_PULSES_PER_ROTATION);
        //there are so many ticks in a rotation and moters use ticks not rotations so u need to translate them.
        leadScrewMotor.setTargetPosition(ticks);
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public boolean limitswitchpressed(){
        return leadScrewResetSwitch.isPressed();
    }
    public void resetencoder(){
        leadScrewMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void in(){if (leadScrewMotor.getCurrentPosition()>=0){leadScrewMotor.setPower(-1.0);}}
    public void out(){if(leadScrewMotor.getCurrentPosition()>=MAX_EXTENSION){leadScrewMotor.setPower(1.0);}}
    public void stop(){leadScrewMotor.setPower(0);}}
