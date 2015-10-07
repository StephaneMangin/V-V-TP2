package fr.istic.vv.simpleGame.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by stephane on 06/10/15.
 */
public class TestSequenceDiagram {

    Game game;
    Board board;
    Board mockBoard;

    @Before
    public void setUp() throws Exception {
        // Prepare for maxGold test
        board = new Board(0, 5, 5, 0, 0);
        Pawn mockPawn0 = mock(Pawn.class);
        Pawn mockPawn1 = mock(Pawn.class);
        when(mockPawn0.getGold()).thenReturn(1);
        when(mockPawn1.getGold()).thenReturn(3);
        when(mockPawn0.getX()).thenReturn(3);
        when(mockPawn0.getY()).thenReturn(3);
        when(mockPawn1.getX()).thenReturn(4);
        when(mockPawn1.getY()).thenReturn(4);
        board.addPawn(mockPawn0);
        board.addPawn(mockPawn1);

        // Test isGameOver with a mocked Board
        mockBoard = mock(Board.class);
        game = new Game(mockBoard);
        when(mockBoard.numberOfPawns()).thenReturn(2);
        when(mockBoard.maxGold()).thenReturn(3);

    }

    @After
    public void tearDown() throws Exception {
        game = null;
        board = null;
        mockBoard = null;
    }

    @Test
    public void testIsGameOver() throws Exception {
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void testMaxGold() throws Exception {
        assertEquals(2, board.numberOfPawns());
        assertEquals(3, board.maxGold());
    }

}