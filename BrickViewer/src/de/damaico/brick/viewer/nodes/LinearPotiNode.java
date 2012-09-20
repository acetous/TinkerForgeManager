package de.damaico.brick.viewer.nodes;

import com.tinkerforge.Device;
import java.beans.IntrospectionException;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.nodes.BeanNode;

public class LinearPotiNode extends BeanNode {
    
    @StaticResource
    private static final String IMAGE = "de/damaico/brick/viewer/resources/linearpoti.png";

    public LinearPotiNode(Device di) throws IntrospectionException {
        super(di);
        setIconBaseWithExtension(IMAGE);
    }
    
}
