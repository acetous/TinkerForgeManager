package de.damaico.brick.viewer.nodes;

import com.tinkerforge.BrickletIO16;
import com.tinkerforge.Device;
import java.beans.IntrospectionException;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

public class IO16Node extends BeanNode {
    
    @StaticResource
    private static final String IMAGE = "de/damaico/brick/viewer/resources/io16.png";

    public IO16Node(BrickletIO16 brick) throws IntrospectionException {
        super(brick, Children.LEAF, Lookups.singleton(brick));
        setIconBaseWithExtension(IMAGE);
    }
    
}
