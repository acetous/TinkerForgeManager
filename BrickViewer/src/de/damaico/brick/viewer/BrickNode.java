/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.viewer;

import com.tinkerforge.Device;
import com.tinkerforge.IPConnection;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author herbi
 */
public class BrickNode extends BeanNode {

    public BrickNode(DeviceIdentifier device) throws IntrospectionException {
        super(device, Children.create(new BrickDetailsChildFactory(device), true));
        setDisplayName(device.getName());
    }
    
    private static class BrickDetailsChildFactory extends ChildFactory<DeviceIdentifier> {
        private final DeviceIdentifier deviceIdentifier;

        public BrickDetailsChildFactory(DeviceIdentifier deviceIdentifier) {
            this.deviceIdentifier = deviceIdentifier;
        }

        @Override
        protected Node[] createNodesForKey(DeviceIdentifier key) {
            Node[] nodes = new Node[2];
            
            nodes[0] = new AbstractNode(Children.LEAF);
            nodes[0].setDisplayName("uID: " + key.getuID());
            
            nodes[1] = new AbstractNode(Children.LEAF);
            nodes[1].setDisplayName("StackID: " + key.getStackID());
            
            return nodes;
        }

        @Override
        protected boolean createKeys(List<DeviceIdentifier> list) {
            list.add(deviceIdentifier);
            return true;
        }
        
        
    }
    
}
