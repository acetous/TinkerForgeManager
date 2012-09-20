/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.viewer;

import com.tinkerforge.Device;
import com.tinkerforge.IPConnection;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;

/**
 *
 * @author herbi
 */
public class BrickNode extends BeanNode {

    public BrickNode(DeviceIdentifier device) throws IntrospectionException {
        super(device);
        setDisplayName(device.getName());
    }
    
}
