package com.vitco.layout.content;

import com.vitco.settings.VitcoSettings;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JScrollPane that unifies the style for the program.
 */
public class JCustomScrollPane extends JScrollPane {

    // initialize the scroll pane
    private void init() {
        this.setBackground(VitcoSettings.DEFAULT_SCROLL_PANE_BG_COLOR);
        this.setBorder(BorderFactory.createLineBorder(VitcoSettings.DEFAULT_BORDER_COLOR));
    }

    // ================
    // below are constructors

    public JCustomScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
        init();
    }

    public JCustomScrollPane(Component view) {
        super(view);
        init();
    }

    public JCustomScrollPane(int vsbPolicy, int hsbPolicy) {
        super(vsbPolicy, hsbPolicy);
        init();
    }

    public JCustomScrollPane() {
        init();
    }
}
