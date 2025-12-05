package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestServo;

import java.util.Arrays;
import java.util.List;

public class Transfer extends QQMechanism{
    double SHOOTER_POSITION = 0.5; //need to figure out position
    double STORAGE_POSITION = 0.7; //need to figure out position
    double SHOOTER_DOWN_POSITION = 0.55; //need to figure out position
    double STORAGE_DOWN_POSITION = 0.45; //need to figure out position


    Servo shooterTransferServo;
    Servo storageTransferServo;

    @Override
    public void init(HardwareMap hardwareMap) {
        shooterTransferServo = hardwareMap.get(Servo.class, "shooter_transfer");
        storageTransferServo = hardwareMap.get(Servo.class, "storage_transfer");


    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestServo("shooterServo up", shooterTransferServo, SHOOTER_POSITION, SHOOTER_DOWN_POSITION),
                new TestServo("storageServo up", storageTransferServo, STORAGE_POSITION, STORAGE_DOWN_POSITION)
        );
    }
    public void moveBallToShooter(){
        shooterTransferServo.setPosition(SHOOTER_POSITION);
        storageTransferServo.setPosition(STORAGE_POSITION);
    }
    public void storeBall(){
        storageTransferServo.setPosition(STORAGE_POSITION);
    }

    public void shooterDown(){
        shooterTransferServo.setPosition((SHOOTER_DOWN_POSITION));
    }

    public void storageDown(){
        storageTransferServo.setPosition((STORAGE_DOWN_POSITION));
    }

    public void resetBothDown(){
        shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
        storageTransferServo.setPosition(STORAGE_DOWN_POSITION);
    }

    public void startAutoPosition(){
        shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
        storageTransferServo.setPosition(STORAGE_POSITION);
    }


}

