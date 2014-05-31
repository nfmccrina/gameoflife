package net.sprawl2kxx.gol;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame
{
    private static final long serialVersionUID = 1L;
    private MenuBar menuBar;
    private LifePanel panel;
    private StatusBar statusBar; // Panel that holds the label that shows the

    // the status.

    public GameWindow()
    {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(605, 669));
        this.setResizable(false);
        this.setTitle("Conway's Game Of Life");

        // Create menu bar
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        // Create the panel holding the game board
        panel = new LifePanel();
        this.getContentPane().add(BorderLayout.CENTER, this.panel);

        // Create the status bar
        statusBar = new StatusBar();
        statusBar.setPreferredSize(new Dimension(605, 20));
        statusBar.setStatusText("Hello!");
        this.getContentPane().add(BorderLayout.SOUTH, statusBar);
    }

    public MenuBar getLifeMenuBar()
    {
        return menuBar;
    }

    public LifePanel getLifePanel()
    {
        return panel;
    }

    public StatusBar getStatusBar()
    {
        return statusBar;
    }

    public void showWindow()
    {
        this.setVisible(true);
    }
}
