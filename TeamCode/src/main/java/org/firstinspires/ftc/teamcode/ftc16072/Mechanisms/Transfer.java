package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestCRServo;

import java.util.Arrays;
import java.util.List;

public class Transfer extends QQMechanism{
    double MOVE_FORWARD_SPEED = 1;
    double MOVE_BACKWARD_SPEED = -1;

    CRServo bottomTransferServo;
    CRServo topTransferServo;

    @Override
    public void init(HardwareMap hardwareMap) {
        bottomTransferServo = hardwareMap.get(CRServo.class, "bottom_transfer");
        topTransferServo = hardwareMap.get(CRServo.class, "top_transfer");

    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestCRServo("bottomServo forward", bottomTransferServo, MOVE_FORWARD_SPEED),
                new TestCRServo("topServo forward", topTransferServo, MOVE_FORWARD_SPEED),
                new TestCRServo("bottomServo backward", bottomTransferServo, MOVE_BACKWARD_SPEED),
                new TestCRServo("topServo backward", topTransferServo, MOVE_BACKWARD_SPEED)
        );
    }

    public void moveUp(){
        bottomTransferServo.setPower(MOVE_FORWARD_SPEED);
        topTransferServo.setPower(MOVE_FORWARD_SPEED);
    }

    public void moveDown(){
        bottomTransferServo.setPower(MOVE_BACKWARD_SPEED);
        topTransferServo.setPower(MOVE_BACKWARD_SPEED);
    }

    public void stop(){
        bottomTransferServo.setPower(0);
        topTransferServo.setPower(0);

    }
}

