package de.damaico.brick.viewer.nodes;

import com.tinkerforge.BrickletLinearPoti;
import com.tinkerforge.Device;
import java.beans.IntrospectionException;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

public class LinearPotiNode extends BeanNode {
    
    @StaticResource
    private static final String IMAGE = "de/damaico/brick/viewer/resources/linearpoti.png";

    public LinearPotiNode(BrickletLinearPoti brick) throws IntrospectionException {
        super(brick, Children.LEAF, Lookups.singleton(brick));
        setIconBaseWithExtension(IMAGE);
    }
    
}
