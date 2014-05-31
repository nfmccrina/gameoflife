package net.sprawl2kxx.gol;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;

public class LifeController
{
    private GameWindow window;
    private GameBoard board;
    private Thread drawThread;

    public LifeController(GameWindow newWindow, GameBoard newBoard)
    {
        window = newWindow;
        board = newBoard;

        // Set up events
        window.getLifeMenuBar().addExitMenuItemListener(
                    new ExitMenuItemHandler());

        window.getLifeMenuBar().addEditCheckBoxListener(
                    new EditCheckBoxHandler());

        window.getLifeMenuBar().addAboutMenuItemListener(
                    new AboutMenuItemHandler());

        window.getLifeMenuBar().addSlowButtonListener(
                    new SlowRadioButtonHandler());

        window.getLifeMenuBar().addMediumButtonListener(
                    new MediumRadioButtonHandler());

        window.getLifeMenuBar().addFastButtonListener(
                    new FastRadioButtonHandler());

        window.getLifeMenuBar().addLoadMenuItemListener(
                    new LoadMenuItemHandler());

        window.getLifeMenuBar().addClearMenuItemListener(
                    new ClearMenuItemHandler());

        window.getLifeMenuBar().addStartMenuItemListener(
                    new StartMenuItemHandler());

        window.getLifeMenuBar().addStopMenuItemListener(
                    new StopMenuItemHandler());

        window.getLifeMenuBar().addSaveMenuItemListener(
                    new SaveMenuItemHandler());

        window.getLifePanel().addMouseListener(new LifePanelMouseHandler());
    }

    // Event handlers for all the GUI elements
    class ExitMenuItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            LifeData.RUNNING = false;

            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException ex)
            {
            }

            System.exit(0);
        }
    }

    class EditCheckBoxHandler implements ItemListener
    {
        public void itemStateChanged(ItemEvent e)
        {
            window.getLifeMenuBar().toggleStartMenuItem();
            window.getLifeMenuBar().toggleStopMenuItem();
            window.getLifeMenuBar().toggleSaveMenuItem();
            window.getLifeMenuBar().toggleClearMenuItem();
            LifeData.EDIT_MODE = !LifeData.EDIT_MODE;
            window.getLifePanel().repaint();
        }
    }

    class AboutMenuItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(
                        window,
                        "Copyright (c) 2010, Ocelot Software\nAll rights reserved.",
                        "About", JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    class SlowRadioButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            window.getLifeMenuBar().getSlowButton().setSelected(true);
            window.getLifeMenuBar().getMediumButton().setSelected(false);
            window.getLifeMenuBar().getFastButton().setSelected(false);
            LifeData.TICK_DELAY = 1000;
        }
    }

    class MediumRadioButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            window.getLifeMenuBar().getSlowButton().setSelected(false);
            window.getLifeMenuBar().getMediumButton().setSelected(true);
            window.getLifeMenuBar().getFastButton().setSelected(false);
            LifeData.TICK_DELAY = 400;
        }
    }

    class FastRadioButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            window.getLifeMenuBar().getSlowButton().setSelected(false);
            window.getLifeMenuBar().getMediumButton().setSelected(false);
            window.getLifeMenuBar().getFastButton().setSelected(true);
            LifeData.TICK_DELAY = 100;
        }
    }

    class LoadMenuItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        ".gol files", "gol");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION)
            {
                board.clear();
                board.applyConfiguration(FileParser
                            .getConfiguration(fileChooser.getSelectedFile()));
            }

            window.getLifePanel().setCellData(board.getBoard());
            window.getLifePanel().repaint();
        }
    }

    class ClearMenuItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            board.clear();
            window.getLifePanel().setCellData(board.getBoard());
            window.getLifePanel().repaint();
        }
    }

    class StartMenuItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            LifeData.RUNNING = true;
            window.getStatusBar().setStatusText("Simulation running");
            window.getLifeMenuBar().disableEditMenu();
            window.getLifeMenuBar().disableLoadMenuItem();
            drawThread = new Thread(new Simulate());
            drawThread.start();
        }
    }

    class StopMenuItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            LifeData.RUNNING = false;
            window.getStatusBar().setStatusText("Simulation stopped");
            window.getLifeMenuBar().enableEditMenu();
            window.getLifeMenuBar().enableLoadMenuItem();
        }
    }

    class SaveMenuItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            File f;

            if (fileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION)
            {
                f = fileChooser.getSelectedFile();

                try
                {
                    FileWriter fout = new FileWriter(f);

                    for (int row = 0; row < LifeData.BOARD_SIZE; row++)
                    {
                        for (int col = 0; col < LifeData.BOARD_SIZE; col++)
                        {
                            if (board.getBoard()[row][col])
                            {
                                String s = "";
                                s += row;
                                s += " ";
                                s += col;
                                fout.write(s + "\n");
                            }
                        }
                    }

                    fout.close();
                }
                catch (IOException ex)
                {
                }
            }
        }
    }

    class LifePanelMouseHandler implements MouseListener
    {
        public void mouseClicked(MouseEvent e)
        {
            if (LifeData.EDIT_MODE)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    int x = e.getX() / 10;
                    int y = e.getY() / 10;

                    board.toggleSquare(y, x);
                    window.getLifePanel().setCellData(board.getBoard());
                    window.getLifePanel().repaint();
                }
            }
        }

        public void mouseEntered(MouseEvent e)
        {
        }

        public void mouseExited(MouseEvent e)
        {
        }

        public void mousePressed(MouseEvent e)
        {
        }

        public void mouseReleased(MouseEvent e)
        {
        }
    }

    class Simulate implements Runnable
    {
        public void run()
        {
            while (LifeData.RUNNING)
            {
                board.tick();
                window.getLifePanel().setCellData(board.getBoard());
                window.getLifePanel().repaint();

                try
                {
                    Thread.sleep(LifeData.TICK_DELAY);
                }
                catch (InterruptedException ex)
                {
                }
            }
        }
    }
}
