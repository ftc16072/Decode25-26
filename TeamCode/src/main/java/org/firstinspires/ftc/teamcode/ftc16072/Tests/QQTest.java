package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;

abstract public class QQTest {
    final String name;
    QQTest(String name){
        this.name = name;
    }
    abstract void run(Telemetry telemetry,boolean on);
}
