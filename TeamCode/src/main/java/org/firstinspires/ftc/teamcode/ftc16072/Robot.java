package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.ControlHub;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.LeadScrew;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Pivot;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.QQMechanism;

import java.util.Arrays;
import java.util.List;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public ControlHub controlHub = new ControlHub();
    public Intake intake = new Intake();
    public Pivot pivot = new Pivot();
    public LeadScrew leadScrew = new LeadScrew();

    final private List<QQMechanism> mechanisms = Arrays.asList(
            mecanumDrive,
            controlHub,
             intake,
            leadScrew,
             pivot
            );

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
    public List<QQMechanism> getMechanisms(){
        return mechanisms;
    }
}