/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.viewer;

import com.tinkerforge.IPConnection;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author herbi
 */
public class BrickChildFactory extends ChildFactory<String> {
    
    private final String HOST = "localhost";
    private final int PORT = 4223;

    // will be called automatically in the background
    @Override
    protected boolean createKeys(final List<String> list) {
        try {
            IPConnection ipc = new IPConnection(HOST, PORT);
            ipc.enumerate(new IPConnection.EnumerateListener() {

                @Override
                public void enumerate(String uid, String name, short stackID, boolean isNew) {
                    list.add(name);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(BrickChildFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(String key) {
        Node node = null;
        try {
            node = new BrickNode(key);
        } catch (IntrospectionException ex) {
            Logger.getLogger(BrickChildFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return node;
    }
    
}