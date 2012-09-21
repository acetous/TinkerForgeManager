/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.visualizer;

import com.tinkerforge.BrickMaster;
import com.tinkerforge.BrickletLinearPoti;
import com.tinkerforge.Device;
import com.tinkerforge.IPConnection.TimeoutException;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectProvider;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.vmd.VMDPinWidget;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//de.damaico.brick.visualizer//BrickVisualizer//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "BrickVisualizerTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "output", openAtStartup = true)
@ActionID(category = "Window", id = "de.damaico.brick.visualizer.BrickVisualizerTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_BrickVisualizerAction",
preferredID = "BrickVisualizerTopComponent")
@Messages({
    "CTL_BrickVisualizerAction=BrickVisualizer",
    "CTL_BrickVisualizerTopComponent=BrickVisualizer Window",
    "HINT_BrickVisualizerTopComponent=This is a BrickVisualizer window"
})
public final class BrickVisualizerTopComponent extends TopComponent {

    private List<Device> uniqueDevices = new ArrayList<Device>();
    final Scene scene = new Scene();
    final LayerWidget baseLayer = new LayerWidget(scene);
    final LayerWidget connectionLayer = new LayerWidget(scene);

    public BrickVisualizerTopComponent() {
        initComponents();
        setName(Bundle.CTL_BrickVisualizerTopComponent());
        setToolTipText(Bundle.HINT_BrickVisualizerTopComponent());

        // add baseLayer to scene
        scene.addChild(baseLayer);
        scene.addChild(connectionLayer);

        // make the scene accept things
        scene.getActions().addAction(ActionFactory.createAcceptAction(new AcceptProvider() {
            @Override
            public ConnectorState isAcceptable(Widget widget, Point point, Transferable t) {
                Node node = NodeTransfer.node(t, NodeTransfer.DND_COPY_OR_MOVE);
                if (node != null) {
                    Device device = node.getLookup().lookup(Device.class);
                    if (device != null) {
                        if (!uniqueDevices.contains(device)) {
                            return ConnectorState.ACCEPT;
                        }
                    }
                }
                return ConnectorState.REJECT;
            }

            @Override
            public void accept(Widget widget, Point point, Transferable t) {
                Node node = NodeTransfer.node(t, NodeTransfer.DND_COPY_OR_MOVE);
                if (node != null) {
                    Device device = node.getLookup().lookup(Device.class);

                    if (device != null) {
                        // add device
                        uniqueDevices.add(device);

                        // create widget
                        VMDNodeWidget simpleWidget;
                        simpleWidget = new VMDNodeWidget(scene);
                        simpleWidget.setNodeName(device.getClass().getSimpleName());
                        simpleWidget.setToolTipText(device.getClass().getSimpleName());
                        simpleWidget.setNodeImage(node.getIcon(BeanInfo.ICON_COLOR_16x16));
                        simpleWidget.setPreferredLocation(point);
                        simpleWidget.setCheckClipping(true);
                        baseLayer.addChild(simpleWidget);
                        
                        // add the ability to connect devices
                        simpleWidget.getActions().addAction(ActionFactory.createExtendedConnectAction(connectionLayer, new DeviceConnectorProvider()));
                        // add the ability to move the device
                        simpleWidget.getActions().addAction(ActionFactory.createMoveAction());

                        // show device-specific information
                        try {
                            if (device instanceof BrickMaster) {
                                BrickMaster bm = (BrickMaster) device;
                                VMDPinWidget pin = new VMDPinWidget(scene);
                                pin.setPinName("Temperature: " + bm.getChipTemperature());
                                simpleWidget.addChild(pin);
                            } else if (device instanceof BrickletLinearPoti) {
                                final BrickletLinearPoti poti = (BrickletLinearPoti) device;
                                final VMDPinWidget pin = new VMDPinWidget(scene);
                                pin.setPinName("init");
                                pin.setPinName("Swag: " + poti.getPosition());
                                simpleWidget.addChild(pin);

                                poti.setPositionCallbackPeriod(100);
                                poti.addListener(new BrickletLinearPoti.PositionListener() {
                                    @Override
                                    public void position(int position) {
                                        pin.setPinName("Swag: " + position);
                                    }
                                });
                            }
                        } catch (TimeoutException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }));

        // add it to our scrollpane
        jScrollPane1.setViewportView(scene.createView());
        
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scene.createSatelliteView(), BorderLayout.CENTER);

        associateLookup(new AbstractLookup(ic));
    }
    
    private InstanceContent ic = new InstanceContent();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private class DeviceConnectorProvider implements ConnectProvider {

        @Override
        public boolean isSourceWidget(Widget source) {
            return source instanceof VMDNodeWidget;
        }

        @Override
        public ConnectorState isTargetWidget(Widget source, Widget target) {
            return !source.equals(target)
                    && target instanceof VMDNodeWidget ? ConnectorState.ACCEPT : ConnectorState.REJECT;
        }

        @Override
        public boolean hasCustomTargetWidgetResolver(Scene scene) {
            return false;
        }

        @Override
        public Widget resolveTargetWidget(Scene scene, Point point) {
            return null;
        }

        @Override
        public void createConnection(Widget source, Widget target) {
            ConnectionWidget connection = new ConnectionWidget(scene);
            connection.setTargetAnchorShape(AnchorShape.TRIANGLE_HOLLOW);
            connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(source));
            connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(target));
            
            connectionLayer.addChild(connection);
            
            ic.add(connection);
        }
    }
}
