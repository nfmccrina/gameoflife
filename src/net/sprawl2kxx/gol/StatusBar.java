package net.sprawl2kxx.gol;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JLabel statusLabel;

    public StatusBar()
    {
        super();
        FlowLayout fl = (FlowLayout) this.getLayout();
        fl.setAlignment(FlowLayout.LEFT);
        statusLabel = new JLabel(" ");
        this.add(statusLabel);
    }

    public void setStatusText(String newText)
    {
        statusLabel.setText(newText);
    }
}
