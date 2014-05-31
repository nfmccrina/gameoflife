package net.sprawl2kxx.gol;

public class GameDriver
{
    private GameWindow window;
    private GameBoard board;
    private LifeController controller;

    public static void main(String[] args)
    {
        GameDriver game = new GameDriver();
        game.run();
    }

    public void run()
    {
        this.window = new GameWindow();
        board = new GameBoard();
        controller = new LifeController(window, board);
        this.window.showWindow();
    }
}
