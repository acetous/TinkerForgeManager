/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.viewer;

import com.tinkerforge.Device;

/**
 *
 * @author herbi
 */
public class DeviceIdentifier {
    private String name;
    private String uid;
    private short stackId;
    private Device deviceType;

    public DeviceIdentifier(String name, String uid, short stackId) {
        this.name = name;
        this.uid = uid;
        this.stackId = stackId;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public short getStackId() {
        return stackId;
    }

    public Device getDeviceType() {
        return deviceType;
    }
    
    
}
