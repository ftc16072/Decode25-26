package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.QQMechanism;

import java.util.Arrays;
import java.util.List;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();


    public List<QQMechanism> mechanisms = Arrays.asList(mecanumDrive);

    public void init(HardwareMap hardwareMap) {
        for (QQMechanism mechanism : mechanisms) {
            mechanism.init(hardwareMap);
        }
    }

    public void update(Telemetry telemetry) {
        for (QQMechanism mechanism : mechanisms) {
            mechanism.update(telemetry);
        }
    }
}