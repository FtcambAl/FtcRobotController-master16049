
package org.firstinspires.ftc.teamcode.OpModes_Tests.Teleoperated.Motors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name="crservo", group="Autonomus")
//@Disabled
public class crservo extends LinearOpMode {
    private CRServo servoTeste = null;
    @Override

    public void runOpMode() throws InterruptedException {
        servoTeste = hardwareMap.crservo.get("crservo");
        waitForStart();

        servoTeste.setPower(1);
        sleep(1000);
        
    }
}
