package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import android.graphics.Color;

import com.qualcomm.hardware.sparkfun.SparkFunLEDStick;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestCRServo;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestLEDs;

import java.util.Arrays;
import java.util.List;

public class LEDs extends QQMechanism {

    private SparkFunLEDStick ledStick;
    int brightness = 10;

    @Override
    public void init(HardwareMap hardwareMap) {
        ledStick = hardwareMap.get(SparkFunLEDStick.class, "back_leds");
        ledStick.setBrightness(brightness);
        ledStick.turnAllOff();
    }
    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
            new TestLEDs("LEDs", ledStick)
                //            new TestColorSensorV3("color", intakeColorSensor)
        );
    }
}
