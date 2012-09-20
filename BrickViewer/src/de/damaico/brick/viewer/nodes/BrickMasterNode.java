package de.damaico.brick.viewer.nodes;

import com.tinkerforge.Device;
import java.beans.IntrospectionException;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.nodes.BeanNode;

public class BrickMasterNode extends BeanNode {
    
    @StaticResource
    private static final String IMAGE = "de/damaico/brick/viewer/resources/master.png";

    public BrickMasterNode(Device di) throws IntrospectionException {
        super(di);
        setIconBaseWithExtension(IMAGE);
    }
    
}
