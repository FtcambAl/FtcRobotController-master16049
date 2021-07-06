package org.firstinspires.ftc.teamcode.OpModes_Tests.Teleoperated.Motors;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="EncoderTest", group="Linear Opmode")
//@Disabled

public class encoderTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    double COUNTS_PER_MOTOR_REV = 2240;
    double DRIVE_GEAR_REDUCTION = 1;
    double wheelDiameterInhces = 3.54331;
    double turnsInches = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (wheelDiameterInhces * 3.1415);

    DcMotor leftEngine;
    DcMotor rightEngine;

    @Override
    public void runOpMode() {
        leftEngine = hardwareMap.dcMotor.get("leftEngine");
        rightEngine = hardwareMap.dcMotor.get("rightEngine");

        rightEngine.setDirection(DcMotor.Direction.REVERSE);

        leftEngine.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEngine.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftEngine.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightEngine.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        // Encoder method in centimeters
        encoder(1, 120, 120, 10);
        
    }
    // Creating the method and its parameters.
    public void encoder(double velocity, double leftDistance, double rightDistance, double timeoutS){

        // Target Variables
        int rightPos;
        int leftPos;

        // Resetting Encoder Motors to Avoid Accumulated Data.
        leftEngine.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEngine.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Starting the encoder.
        leftEngine.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightEngine.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Transforming quantity in centimeters to inches.
        rightPos = (int) (rightDistance / 2.54  * turnsInches);
        leftPos = (int) (leftDistance / 2.54 * turnsInches);

        // Defined the Target with the value obtained in the CM to Inch conversion.
        leftEngine.setTargetPosition(leftPos);
        rightEngine.setTargetPosition(rightPos);

        // Starting the target.
        leftEngine.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightEngine.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        // Engines move while occupied (Until it finds target)
        while (opModeIsActive() && (runtime.seconds() < timeoutS) &&
                (leftEngine.isBusy() && rightEngine.isBusy())) {
            //Potência em que irá se mover para achar o alvo.
            leftEngine.setPower(Math.abs(velocity));
            rightEngine.setPower(Math.abs(velocity));
        }
        // After reaching the desired position, the loop ends and movement stops.
        leftEngine.setPower(0);
        rightEngine.setPower(0);

        // Terminating the code.
        leftEngine.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightEngine.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}