/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.viewer;

import com.tinkerforge.Device;
import java.awt.Color;
import java.io.File;

/**
 *
 * @author herbi
 */
public class DeviceIdentifier {

    private String uID;
    private String name;
    private short stackID;
    private Device deviceTyp;
    private String imageURL;
    private Color color;
    private File file;

    public DeviceIdentifier(String uID, String name, short stackID) {
        this.uID = uID;
        this.name = name;
        this.stackID = stackID;
    }

    public String getName() {
        return name;
    }

    public short getStackID() {
        return stackID;
    }

    public String getuID() {
        return uID;
    }

    public Device getDeviceTyp() {
        return deviceTyp;
    }

    public void setDeviceTyp(Device deviceTyp) {
        this.deviceTyp = deviceTyp;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
