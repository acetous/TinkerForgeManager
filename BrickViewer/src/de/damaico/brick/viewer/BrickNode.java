package de.damaico.brick.viewer;

import com.tinkerforge.Device;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;

public class BrickNode extends BeanNode {

    public BrickNode(Device di) throws IntrospectionException {
        super(di);
    }
    
}
