package org.firstinspires.ftc.teamcode.OpModes_Tests.Teleoperated.Motors.Mecanum;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@TeleOp
public class MecanumDrive extends LinearOpMode {

    // Component declaration
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFrontLeft = null;
    private DcMotor motorBackLeft = null;
    private DcMotor motorFrontRight = null;
    private DcMotor motorBackRight = null;
    private DcMotor hoopArm = null;
    private ColorSensor sensorColor = null;
    private CRServo servo = null;

    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    private static final String VUFORIA_KEY = "AXUZl/H/////AAABmf9CIbw+rEVNuWNYmN1nJiYyIMNy6ulSlXaA1ImkjwvyTUPmDx+HyHy/Ya0ayuWCCTP16EHFv3gV9Q7TWOgHF4OVqo23KrdBUG92z1uQJOa+cktEhvOFyp9b0d+VN417lX6o12yUD3SsB7sPytUplLR0FblEuQLpnkmnhqKS/86Nm/F6OLD4OsLYECtYUITTOE9VhIcm7t8mFyT8sDDx8UblyCIboAhua4EellGEwsN+lTT7Pp7b80nL70jCk2RJMhowYptdMNmrNo+qFPlM3XmSw7ZudDFyl5rNGhCFsRq4IregwfJGAwAaFA0AOVvaDDkl06gwq78DMbol8R4xemiqLLyLLKBiLoAM/85EObsi";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    @Override

    public void runOpMode() {

        // Component Hardware Mapping
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        hoopArm = hardwareMap.dcMotor.get("hoopArm");
        servo = hardwareMap.crservo.get("crservo");

        // Component operation mode
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        hoopArm.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            // Variables for defining the acceleration force and curves
            double accelerator_y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double bend_x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double ab_rx = gamepad1.right_stick_x;

            double frontLeftPower = accelerator_y + bend_x + ab_rx;
            double backLeftPower = accelerator_y - bend_x + ab_rx;
            double frontRightPower = accelerator_y - bend_x - ab_rx;
            double backRightPower = accelerator_y + bend_x - ab_rx;

            if (Math.abs(frontLeftPower) > 1.0 || Math.abs(backLeftPower) > 1.0 ||
                    Math.abs(frontRightPower) > 1.0 || Math.abs(backRightPower) > 1.0) {

                double max = 0.0;
                max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
                max = Math.max(Math.abs(frontRightPower), max);
                max = Math.max(Math.abs(backRightPower), max);

                frontLeftPower /= max;
                backLeftPower /= max;
                frontRightPower /= max;
                backRightPower /= max;
            }

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
}
