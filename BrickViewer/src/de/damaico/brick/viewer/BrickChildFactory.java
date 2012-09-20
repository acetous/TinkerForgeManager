package de.damaico.brick.viewer;

import com.tinkerforge.Device;
import com.tinkerforge.IPConnection;
import java.awt.Color;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class BrickChildFactory extends ChildFactory<Device> {

    private final String host = "localhost";
    private final int port = 4223;
    DeviceClassifier dc = DeviceClassifier.getInstance();

    //THIS METHOD IS AUTOMATICALLY CALLED IN THE BACKGROUND:
    @Override
    protected boolean createKeys(final List<Device> list) {
        try {
            IPConnection ipc = new IPConnection(host, port);
            ipc.enumerate(new IPConnection.EnumerateListener() {
                @Override
                public void enumerate(String uid, String name, short stackID, boolean isNew) {
                    list.add(DeviceClassifier.getInstance().classifyDevice(new DeviceIdentifier(name, uid, stackID, Color.BLUE, new File(""))));
                }
            });
        } catch (IOException ex) {
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(Device key) {
        BrickNode node = null;
        try {
            node = new BrickNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
    
}
