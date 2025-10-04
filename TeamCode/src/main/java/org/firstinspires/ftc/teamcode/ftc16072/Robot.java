package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Camera;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.ControlHub;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Outtake;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.QQMechanism;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Transfer;

import java.util.Arrays;
import java.util.List;

public class Robot {
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public ControlHub controlHub = new ControlHub();
    public Intake intake = new Intake();
    public Camera camera = new Camera();
    public Outtake outtake = new Outtake();
    public Transfer transfer = new Transfer();

  final private List<QQMechanism> mechanisms = Arrays.asList(
            mecanumDrive,
            controlHub,
            camera,
            intake,
            outtake,
            transfer
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
