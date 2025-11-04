package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HoldingCell extends QQMechanism{
    Servo mover;
    Servo lifter;

    final double MOVER_UP_SERVO_POSITION = 0;
    final double MOVER_DOWN_SERVO_POSITION = 1; //needs to be calculated
    @Override
    public void init(HardwareMap hardwareMap) {
        mover = hardwareMap.get(Servo.class, "mover_servo");
        lifter = hardwareMap.get(Servo.class,"lifter_servo");

    }
    public void moverUp() {
        mover.setPosition(MOVER_UP_SERVO_POSITION);
    }

    public void moverDown() {
        mover.setPosition(MOVER_DOWN_SERVO_POSITION);

    }



}
