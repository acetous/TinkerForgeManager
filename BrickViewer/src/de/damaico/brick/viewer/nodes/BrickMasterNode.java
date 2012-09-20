package de.damaico.brick.viewer.nodes;

import com.tinkerforge.BrickMaster;
import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
import org.openide.windows.WindowManager;

public class BrickMasterNode extends BeanNode {
    
    @StaticResource
    private static final String IMAGE = "de/damaico/brick/viewer/resources/master.png";

    public BrickMasterNode(BrickMaster brick) throws IntrospectionException {
        super(brick, Children.LEAF, Lookups.singleton(brick));
        setIconBaseWithExtension(IMAGE);
        
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                WindowManager.getDefault().findTopComponent("MasterBrickEditorTopComponent").open();
            }
            
        };
    }
    
}
