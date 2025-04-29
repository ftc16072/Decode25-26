package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;

public class MecanumDrive extends QQMechanism{
    DcMotor backRight;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor frontLeft;
    public MecanumDrive(){
        tests = Arrays.asList(
                new TestMotor("back_right", backRight, 0.1),
                new TestMotor("back_left",backLeft, 0.1),
                new TestMotor("front_right",frontRight, 0.1),
                new TestMotor("front_left", frontLeft, 0.1)
                );
    }
    @Override
    public void init(HardwareMap hardwareMap) {
        backRight = hardwareMap.get(DcMotor.class,"back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
    }
}
