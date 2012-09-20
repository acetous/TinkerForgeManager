/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.brick.editor;

import com.tinkerforge.BrickMaster;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//de.damaico.brick.editor//BrickEditor//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "MasterBrickEditorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "de.damaico.brick.editor.MasterBrickEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_BrickEditorAction",
preferredID = "BrickEditorTopComponent")
@Messages({
    "CTL_BrickEditorAction=MasterBrickEditor",
    "CTL_BrickEditorTopComponent=MasterBrickEditor Window",
    "HINT_BrickEditorTopComponent=This is a MasterBrickEditor window"
})
public final class MasterBrickEditorTopComponent extends TopComponent implements LookupListener {
    private Lookup.Result<BrickMaster> allDevicesInContext;

    public MasterBrickEditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_BrickEditorTopComponent());
        setToolTipText(Bundle.HINT_BrickEditorTopComponent());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
//        allDevicesInContext = WindowManager.getDefault().findTopComponent("AnagramEditorTopComponent").getLookup().lookupResult(String.class);
        allDevicesInContext = Utilities.actionsGlobalContext().lookupResult(BrickMaster.class);
        
        allDevicesInContext.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        allDevicesInContext.removeLookupListener(this);
    }

    @Override
    public void resultChanged(LookupEvent le) {
        jTextField1.setText("");
        for (BrickMaster brick : allDevicesInContext.allInstances()) {
            jTextField1.setText(jTextField1.getText() + "\n" + brick);
        }
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
}
