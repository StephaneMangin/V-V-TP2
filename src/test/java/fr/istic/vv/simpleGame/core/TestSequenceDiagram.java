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

    /**
     * Tests the "isGameOver" game's method with mocked board.
     *
     * @see Game#isGameOver()
     * @type Functional
     * @oracle Should return false
     * @passed Yes
     */
    @Test
    public void testIsGameOverFalse() throws Exception {
        Board mockBoard = mock(Board.class);
        Game game = new Game(mockBoard);
        when(mockBoard.numberOfPawns()).thenReturn(2);
        when(mockBoard.maxGold()).thenReturn(2);
        assertFalse(game.isGameOver());
    }

    /**
     * Tests the "isGameOver" game's method with mocked board.
     *
     * @see Game#isGameOver()
     * @type Functional
     * @oracle Should return true
     * @passed Yes
     */
    @Test
    public void testIsGameOverTrue() throws Exception {
        Board mockBoard = mock(Board.class);
        Game game = new Game(mockBoard);
        when(mockBoard.numberOfPawns()).thenReturn(2);
        when(mockBoard.maxGold()).thenReturn(3);
        assertTrue(game.isGameOver());
    }

    /**
     * Tests the "maxGold" board's method with mocked pawn.
     *
     * @see Board#maxGold()
     * @type Functional
     * @oracle Should return 3
     * @passed Yes
     */
    @Test
    public void testMaxGold_WithPawn() throws Exception {
        Board board = new Board(0, 5, 5, 0, 0);
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

        assertEquals(3, board.maxGold());
    }

    /**
     * Tests the "maxGold" board's method with no pawn.
     *
     * @see Board#maxGold()
     * @type Functional
     * @oracle Should return 0
     * @passed Yes
     */
    @Test
    public void testMaxGold_WithoutPawn() throws Exception {
        Board board = new Board(0, 5, 5, 0, 0);
        assertEquals(0, board.maxGold());
    }

    /**
     * Tests the "maxGold" board's method with 10 pawns in constructor.
     *
     * @see Board#maxGold()
     * @type Functional
     * @oracle Should return 0
     * @passed Yes
     */
    @Test
    public void testMaxGold_With10Pawn() throws Exception {
        Board board = new Board(10, 5, 5, 2, 4);
        assertEquals(0, board.maxGold());
    }

}