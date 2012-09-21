/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.damaico.connection.analyzer;

import de.damaico.api.ConnectionAnalyzerCapability;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "Tools",
id = "de.damaico.connection.analyzer.AnalyzeConnectionsActionListener")
@ActionRegistration(
    iconBase = "de/damaico/connection/analyzer/icon.png",
displayName = "#CTL_AnalyzeConnectionsActionListener")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0),
    @ActionReference(path = "Toolbars/File", position = 300)
})
@Messages("CTL_AnalyzeConnectionsActionListener=Analyze Connections")
public final class AnalyzeConnectionsActionListener implements ActionListener {

    private final List<ConnectionAnalyzerCapability> contextList;

    public AnalyzeConnectionsActionListener(List<ConnectionAnalyzerCapability> context) {
        this.contextList = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        for (ConnectionAnalyzerCapability cac : contextList) {
            ConnectionWidget widget = cac.analyze();
            String source = widget.getSourceAnchor().getRelatedWidget().getToolTipText();
            String target = widget.getTargetAnchor().getRelatedWidget().getToolTipText();

            JOptionPane.showMessageDialog(null, "Connection from " + source + " to " + target + ".");
        }
    }
}
