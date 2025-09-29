package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class TestCamera extends QQTest {
    WebcamName webcamName;
    @Override
    public void run(Telemetry telemetry, boolean on) {
        telemetry.addData("Attached", webcamName.isAttached());
    }
    public TestCamera (String name, WebcamName webcamName){
        super(name);
        this.webcamName = webcamName;
    }
}
