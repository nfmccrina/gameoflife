package net.sprawl2kxx.gol;

import javax.swing.*;
import java.awt.event.*;

public class MenuBar extends JMenuBar
{
    private static final long serialVersionUID = 1L;
    private JMenu fileMenu;
    private JMenu gameMenu;
    private JMenu helpMenu;
    private JMenu speedMenu;
    private JMenu editMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem startMenuItem;
    private JMenuItem stopMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem clearMenuItem;
    private JRadioButtonMenuItem slowButton;
    private JRadioButtonMenuItem mediumButton;
    private JRadioButtonMenuItem fastButton;
    private JCheckBoxMenuItem editCheckBox;

    public MenuBar()
    {
        super();
        setupMenuBar();
    }

    private void setupMenuBar()
    {
        // create file menu
        fileMenu = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);

        // create game menu
        gameMenu = new JMenu("Game");
        loadMenuItem = new JMenuItem("Load");
        startMenuItem = new JMenuItem("Start");
        stopMenuItem = new JMenuItem("Stop");

        // create speed menu
        speedMenu = new JMenu("Speed");
        slowButton = new JRadioButtonMenuItem("Slow");
        mediumButton = new JRadioButtonMenuItem("Medium");
        fastButton = new JRadioButtonMenuItem("Fast");
        speedMenu.add(slowButton);
        speedMenu.add(mediumButton);
        speedMenu.add(fastButton);

        // create edit menu
        editMenu = new JMenu("Edit");
        editCheckBox = new JCheckBoxMenuItem("Edit Mode");
        saveMenuItem = new JMenuItem("Save");
        clearMenuItem = new JMenuItem("Clear");
        editMenu.add(editCheckBox);
        editMenu.add(clearMenuItem);
        editMenu.add(saveMenuItem);

        // finish game menu
        gameMenu.add(loadMenuItem);
        gameMenu.add(startMenuItem);
        gameMenu.add(stopMenuItem);
        gameMenu.add(speedMenu);
        gameMenu.add(editMenu);

        // create help menu
        helpMenu = new JMenu("Help");
        aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        this.add(fileMenu);
        this.add(gameMenu);
        this.add(helpMenu);

        if (!LifeData.EDIT_MODE)
        {
            saveMenuItem.setEnabled(false);
            clearMenuItem.setEnabled(false);
        }

        if (LifeData.TICK_DELAY == 1000)
        {
            slowButton.setSelected(true);
        }
        else if (LifeData.TICK_DELAY == 400)
        {
            mediumButton.setSelected(true);
        }
        else if (LifeData.TICK_DELAY == 100)
        {
            fastButton.setSelected(true);
        }
    }

    // Do this when in edit mode
    public void toggleStartMenuItem()
    {
        if (startMenuItem.isEnabled())
        {
            startMenuItem.setEnabled(false);
        }
        else
        {
            startMenuItem.setEnabled(true);
        }
    }

    public void toggleStopMenuItem()
    {
        if (stopMenuItem.isEnabled())
        {
            stopMenuItem.setEnabled(false);
        }
        else
        {
            stopMenuItem.setEnabled(true);
        }
    }

    public void toggleSaveMenuItem()
    {
        if (saveMenuItem.isEnabled())
        {
            saveMenuItem.setEnabled(false);
        }
        else
        {
            saveMenuItem.setEnabled(true);
        }
    }

    public void toggleClearMenuItem()
    {
        if (clearMenuItem.isEnabled())
        {
            clearMenuItem.setEnabled(false);
        }
        else
        {
            clearMenuItem.setEnabled(true);
        }
    }

    public void disableLoadMenuItem()
    {
        loadMenuItem.setEnabled(false);
    }

    public void disableEditMenu()
    {
        editMenu.setEnabled(false);
    }

    public void enableLoadMenuItem()
    {
        loadMenuItem.setEnabled(true);
    }

    public void enableEditMenu()
    {
        editMenu.setEnabled(true);
    }

    public JRadioButtonMenuItem getSlowButton()
    {
        return slowButton;
    }

    public JRadioButtonMenuItem getMediumButton()
    {
        return mediumButton;
    }

    public JRadioButtonMenuItem getFastButton()
    {
        return fastButton;
    }

    public void addExitMenuItemListener(ActionListener l)
    {
        exitMenuItem.addActionListener(l);
    }

    public void addEditCheckBoxListener(ItemListener l)
    {
        editCheckBox.addItemListener(l);
    }

    public void addAboutMenuItemListener(ActionListener l)
    {
        aboutMenuItem.addActionListener(l);
    }

    public void addSlowButtonListener(ActionListener l)
    {
        slowButton.addActionListener(l);
    }

    public void addMediumButtonListener(ActionListener l)
    {
        mediumButton.addActionListener(l);
    }

    public void addFastButtonListener(ActionListener l)
    {
        fastButton.addActionListener(l);
    }

    public void addLoadMenuItemListener(ActionListener l)
    {
        loadMenuItem.addActionListener(l);
    }

    public void addClearMenuItemListener(ActionListener l)
    {
        clearMenuItem.addActionListener(l);
    }

    public void addStartMenuItemListener(ActionListener l)
    {
        startMenuItem.addActionListener(l);
    }

    public void addStopMenuItemListener(ActionListener l)
    {
        stopMenuItem.addActionListener(l);
    }

    public void addSaveMenuItemListener(ActionListener l)
    {
        saveMenuItem.addActionListener(l);
    }
}
