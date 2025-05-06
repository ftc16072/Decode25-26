package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;

abstract public class QQTest {
    public final String name;
    QQTest(String name){
        this.name = name;
    }
   public abstract void run(Telemetry telemetry,boolean on);
}
