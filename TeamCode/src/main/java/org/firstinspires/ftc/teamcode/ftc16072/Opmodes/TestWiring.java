package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.QQMechanism;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;

import java.util.List;

@TeleOp
public class TestWiring extends QQOpmode{
    List<QQMechanism> mechanismList;

    boolean wasDown;
    boolean wasLeft;
    boolean wasRight;
    boolean wasUp;
    int currentMech;
    int currentTest;


    @Override
    public void init() {
        super.init();
        mechanismList = robot.getMechanisms();

    }

    @Override
    public void loop() {
        super.loop();
         if (gamepad1.dpad_up && !wasUp){
             currentMech = currentMech - 1;
             if(currentMech < 0){
                 currentMech = mechanismList.size() - 1;
             }
             currentTest =0;
         }

         if (gamepad1.dpad_down && !wasDown){
             currentMech = currentMech + 1;
             if(currentMech >= mechanismList.size()){
                 currentMech = 0;
             }
             currentTest =0;
         }
        List<QQTest> testList = mechanismList.get(currentMech).getTests();

        if (gamepad1.dpad_left && !wasLeft){
             currentTest = currentTest - 1;
             if(currentTest < 0){
                 currentTest = testList.size() - 1;
             }
         }

         if (gamepad1.dpad_right && !wasRight){
             currentTest = currentTest + 1;
             if (currentTest >= testList.size()){
                 currentTest =  0;
             }
         }

        wasDown = gamepad1.dpad_down;
        wasLeft = gamepad1.dpad_left;
        wasRight = gamepad1.dpad_right;
        wasUp = gamepad1.dpad_up;
        telemetry.addData("Mechanism", mechanismList.get(currentMech).getName());
        telemetry.addData("Test", testList.get(currentTest).name);
        testList.get(currentTest).run(telemetry, gamepad1.a);

    }
}
