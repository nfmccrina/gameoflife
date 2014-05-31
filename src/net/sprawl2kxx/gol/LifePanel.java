package net.sprawl2kxx.gol;

import javax.swing.*;
import java.awt.*;

public class LifePanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private boolean[][] cellData;

    public LifePanel()
    {
        super();
        cellData = new boolean[LifeData.BOARD_SIZE][LifeData.BOARD_SIZE];
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.setColor(Color.BLACK);

        if (LifeData.EDIT_MODE)
        {
            drawEditGrid(g);
            drawCells(g);
        }
        else
        {
            drawCells(g);
        }
    }

    public void drawCells(Graphics g)
    {
        for (int row = 0; row < LifeData.BOARD_SIZE; row++)
        {
            for (int col = 0; col < LifeData.BOARD_SIZE; col++)
            {
                if (cellData[row][col])
                {
                    g.fillRect((col * 10), (row * 10), 10, 10);
                }
            }
        }
    }

    public void drawEditGrid(Graphics g)
    {
        // Draw the grid lines
        for (int row = 0; row <= LifeData.BOARD_SIZE; row++)
        {
            g.drawLine(0, row * 10, 650, row * 10);
        }

        for (int col = 0; col < LifeData.BOARD_SIZE; col++)
        {
            g.drawLine(col * 10, 0, col * 10, 605);
        }
    }

    public void setCellData(boolean[][] data)
    {
        cellData = data;
    }
}
