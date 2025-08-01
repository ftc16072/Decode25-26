package org.firstinspires.ftc.teamcode.ftc16072.pedroPathing.auto_paths;

import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Opmodes.QQOpmode;

@TeleOp
public class Three_Sample_3_6_Randomization extends QQOpmode {
    public static PathBuilder builder = new PathBuilder();

    public static PathChain line1 = builder
            .addPath(
                    new BezierLine(
                            new Point(9.000, 105.000, Point.CARTESIAN),
                            new Point(21.000, 113.500, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(23))
            .build();

    public static PathChain line2 = builder
            .addPath(
                    new BezierLine(
                            new Point(21.000, 113.500, Point.CARTESIAN),
                            new Point(20.000, 124.000, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(23), Math.toRadians(-45))
            .build();

    public static PathChain line3 = builder
            .addPath(
                    new BezierLine(
                            new Point(20.000, 124.000, Point.CARTESIAN),
                            new Point(21.000, 123.500, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(23))
            .build();

    public static PathChain line4 = builder
            .addPath(
                    new BezierLine(
                            new Point(21.000, 123.500, Point.CARTESIAN),
                            new Point(20.000, 124.000, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(23), Math.toRadians(-45))
            .build();

    public static PathChain line5 = builder
            .addPath(
                    new BezierLine(
                            new Point(20.000, 124.000, Point.CARTESIAN),
                            new Point(21.000, 130.000, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(34))
            .build();

    public static PathChain line6 = builder
            .addPath(
                    new BezierLine(
                            new Point(21.000, 130.000, Point.CARTESIAN),
                            new Point(20.000, 124.000, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(34), Math.toRadians(-45))
            .build();

    public static PathChain line7 = builder
            .addPath(
                    new BezierLine(
                            new Point(20.000, 124.000, Point.CARTESIAN),
                            new Point(37.000, 22.500, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0))
            .build();

    public static PathChain line8 = builder
            .addPath(
                    new BezierLine(
                            new Point(37.000, 22.500, Point.CARTESIAN),
                            new Point(60.000, 12.000, Point.CARTESIAN)
                    )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
            .build();
}

//Picking up third sample to the park position for this randomization
