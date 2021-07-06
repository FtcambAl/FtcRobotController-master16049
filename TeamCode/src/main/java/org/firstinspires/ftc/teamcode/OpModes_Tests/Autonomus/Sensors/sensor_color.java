package org.firstinspires.ftc.teamcode.OpModes_Tests.Autonomus.Sensors;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="sensor_cor", group="Linear Opmode")
//@Disabled

public class sensor_color extends LinearOpMode {
    
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motor_esquerdo = null;
    private DcMotor motor_direito = null;
    private Servo servo = null;
    private ColorSensor sensorColor = null;

    float hsvValues[] = {0F, 0F, 0F};
    /* hsvValues é um vetor que coleta dados de matiz (H),
    saturação (S) e  valor (V), os quais são identificados
    pelo sensor de cor.  A matiz foca na cor real do objeto,
    a saturação foca na quantidade de cinza em uma cor espe-
    cífica e o valor foca no brilho.*/

    final double SCALE_FACTOR = 255.0;
    /* Quando o RGB é convertido para HSV, os valores finais
    são muito pequenos, por isso, muultiplicamos esses valo-
    res por SCALE_FACTOR. O produto dessa multiplicação são
    valores de 0 a 360, onde todas as cores estão nesse in-
    tervalo de números.*/

    final float values[] = hsvValues;
    // Variável que guarda todas as informações de hsvValues.

    boolean rotacao_celeste = true;
    /* Variável utilizada no ciclo de repetição que faz com
    que o robô ande continuamente até achar a pedra celeste.*/

    boolean rotacao_toque = true;
    /* Variável utilizada no ciclo de repetição que faz com
    que o robô ande continuamente até que o sensor de toque
    seja pressionado.*/
    @Override

    public void runOpMode(){
        telemetry.addData("status", "Initialized");
        telemetry.update();
        // Informando que o algoritmo foi iniciado

        sensorColor = hardwareMap.get(ColorSensor.class, "Sensor_Color_Distance");
        motor_direito = hardwareMap.get(DcMotor.class, "motor_Direito");
        motor_esquerdo = hardwareMap.get(DcMotor.class, "motor_esquerdo");
        servo = hardwareMap.servo.get("servo");
        // Mapeando o hardware dos componentes do modo Op (motores e sensores).

        motor_esquerdo.setDirection(DcMotor.Direction.FORWARD);
        motor_direito.setDirection(DcMotor.Direction.REVERSE);
        /* Definindo o modo de rotação dos motores. */

        waitForStart();
        runtime.reset();
        // Esperando que a programação seja executada

        while (opModeIsActive()) {
            motor_esquerdo.setPower(0.4);
            motor_direito.setPower(0.4);
            telemetry.addData("valor", + values[0]);
            telemetry.update();
            Color.RGBToHSV((int)(sensorColor.red() * SCALE_FACTOR),
                    (int)(sensorColor.green() * SCALE_FACTOR),
                    (int)(sensorColor.blue() * SCALE_FACTOR),
                    hsvValues);
            if (values[0] >= 115 && values[0] <= 160)
            {
                motor_esquerdo.setPower(0);
                motor_direito.setPower(0);
            }
        }

    }
}