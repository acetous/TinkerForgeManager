/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.viewer;

import com.tinkerforge.BrickDC;
import com.tinkerforge.BrickIMU;
import com.tinkerforge.BrickMaster;
import com.tinkerforge.BrickServo;
import com.tinkerforge.BrickStepper;
import com.tinkerforge.BrickletAmbientLight;
import com.tinkerforge.BrickletAnalogIn;
import com.tinkerforge.BrickletAnalogOut;
import com.tinkerforge.BrickletCurrent12;
import com.tinkerforge.BrickletCurrent25;
import com.tinkerforge.BrickletDistanceIR;
import com.tinkerforge.BrickletDualRelay;
import com.tinkerforge.BrickletHumidity;
import com.tinkerforge.BrickletIO16;
import com.tinkerforge.BrickletIO4;
import com.tinkerforge.BrickletJoystick;
import com.tinkerforge.BrickletLCD16x2;
import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.BrickletLinearPoti;
import com.tinkerforge.BrickletPiezoBuzzer;
import com.tinkerforge.BrickletRotaryPoti;
import com.tinkerforge.BrickletTemperature;
import com.tinkerforge.BrickletTemperatureIR;
import com.tinkerforge.BrickletVoltage;
import com.tinkerforge.Device;
import java.util.ArrayList;

/**
 *
 * @author herbi
 */
public class DeviceClassifier {
    
    private static DeviceClassifier instance = null;
    ArrayList<DeviceIdentifier> stackDevices;
    ArrayList<Object> classifiedDevices = new ArrayList<>();
    DeviceIdentifier model;
    
    private DeviceClassifier() {
        
    }
    
    public static DeviceClassifier getInstance() {
        if (instance == null) {
            instance = new DeviceClassifier();
        }
        return instance;
    }

    public Device classifyDevice(DeviceIdentifier model) {

        Device device = null;
        DeviceIdentifier tmpDM = model;
        String cutted = tmpDM.getName().substring(0, tmpDM.getName().trim().length() - 4).trim();

        switch (cutted) {
            case "Ambient Light Bricklet":
                device = new BrickletAmbientLight(tmpDM.getuID());
                break;
            case "AnalogIn Bricklet":
                device = new BrickletAnalogIn(tmpDM.getuID());
                break;
            case "AnalogOut Bricklet":
                device = new BrickletAnalogOut(tmpDM.getuID());
                break;
            case "Buzzer Bricklet":
                device = new BrickletPiezoBuzzer(tmpDM.getuID());
                break;
            case "Current12 Bricklet":
                device = new BrickletCurrent12(tmpDM.getuID());
                break;
            case "Current25 Bricklet":
                device = new BrickletCurrent25(tmpDM.getuID());
                break;
            case "DC Brick":
                device = new BrickDC(tmpDM.getuID());
                break;
            case "Distance IR Bricklet":
                device = new BrickletDistanceIR(tmpDM.getuID());
                break;
            case "Humidity Bricklet":
                device = new BrickletHumidity(tmpDM.getuID());
                break;
            case "IMU Brick":
                device = new BrickIMU(tmpDM.getuID());
                break;
            case "IO-16 Bricklet":
                device = new BrickletIO16(tmpDM.getuID());
                break;
            case "IO-4 Bricklet":
                device = new BrickletIO4(tmpDM.getuID());
                break;
            case "Joystick Bricklet":
                device = new BrickletJoystick(tmpDM.getuID());
                break;
            case "LCD 16x2 Bricklet":
                device = new BrickletLCD16x2(tmpDM.getuID());
                break;
            case "LCD 20x4 Bricklet":
                device = new BrickletLCD20x4(tmpDM.getuID());
                break;
            case "Master Brick":
                device = new BrickMaster(tmpDM.getuID());
                break;
            case "Linear Poti Bricklet":
                device = new BrickletLinearPoti(tmpDM.getuID());
                break;
            case "Rotary Poti Bricklet":
                device = new BrickletRotaryPoti(tmpDM.getuID());
                break;
            case "Relay Dual Bricklet":
                device = new BrickletDualRelay(tmpDM.getuID());
                break;
            case "Servo Brick":
                device = new BrickServo(tmpDM.getuID());
                break;
            case "Stepper Brick":
                device = new BrickStepper(tmpDM.getuID());
                break;
            case "Temperature AmbientBricklet":
                device = new BrickletTemperature(tmpDM.getuID());
                break;
            case "Temperature IR Bricklet":
                device = new BrickletTemperatureIR(tmpDM.getuID());
                break;
            case "Voltage Bricklet":
                device = new BrickletVoltage(tmpDM.getuID());
                break;
// Diese werden nicht erkannt:
            case "Chibi Extension":
            case "StepDown Brick":
            case "RS485 Extension":
        }
//        System.out.println("Device: " + device.toString());

        model.setDeviceTyp(device);

        return device;
    }
}
