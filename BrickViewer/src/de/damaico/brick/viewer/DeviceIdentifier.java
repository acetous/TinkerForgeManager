package de.damaico.brick.viewer;

import com.tinkerforge.Device;
import java.awt.Color;
import java.io.File;

public class DeviceIdentifier {
    String name;
    String uid;
    short stackId;
    Device deviceType;
    
    Color color;
    File file;

    public DeviceIdentifier(String name, String uid, short stackId, Color color, File file) {
        this.name = name;
        this.uid = uid;
        this.stackId = stackId;
        this.color = color;
        this.file = file;
        DeviceClassifier.getInstance().classifyDevice(this);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public short getStackId() {
        return stackId;
    }

    public void setStackId(short stackId) {
        this.stackId = stackId;
    }

    public Device getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Device deviceType) {
        this.deviceType = deviceType;
    }
    

}
