package fr.istic.vv.simpleGame.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by stephane on 30/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    Board board;
    Pawn pawn_1_3;
    Pawn pawn_5_5;
    Pawn pawn_4_2;
    Pawn pawn_2_1;
    Pawn pawn_4_1;
    Pawn pawn_0_0;

    private void addPawns() {
        board.addPawn(pawn_1_3);
        board.addPawn(pawn_5_5);
        board.addPawn(pawn_4_2);
        board.addPawn(pawn_2_1);
        board.addPawn(pawn_4_1);
    }

    @Before
    public void setUp() throws Exception {
        pawn_1_3 = mock(Pawn.class);
        pawn_5_5 = mock(Pawn.class);
        pawn_4_2 = mock(Pawn.class);
        pawn_2_1 = mock(Pawn.class);
        pawn_4_1 = mock(Pawn.class);
        pawn_0_0 = mock(Pawn.class); // To be add later
        board = new Board(0, 5, 8, 4, 2);

        when(pawn_1_3.getGold()).thenReturn(0);
        when(pawn_5_5.getGold()).thenReturn(1);
        when(pawn_4_2.getGold()).thenReturn(3);
        when(pawn_2_1.getGold()).thenReturn(2);
        when(pawn_4_1.getGold()).thenReturn(0);

        when(pawn_1_3.getLetter()).thenReturn('z');
        when(pawn_5_5.getLetter()).thenReturn('d');
        when(pawn_4_2.getLetter()).thenReturn('a');
        when(pawn_2_1.getLetter()).thenReturn('d');
        when(pawn_4_1.getLetter()).thenReturn('g');

        when(pawn_1_3.isDead()).thenReturn(false);
        when(pawn_5_5.isDead()).thenReturn(false);
        when(pawn_4_2.isDead()).thenReturn(false);
        when(pawn_2_1.isDead()).thenReturn(false);
        when(pawn_4_1.isDead()).thenReturn(true);

        when(pawn_1_3.getX()).thenReturn(1);
        when(pawn_5_5.getX()).thenReturn(5);
        when(pawn_4_2.getX()).thenReturn(4);
        when(pawn_2_1.getX()).thenReturn(2);
        when(pawn_4_1.getX()).thenReturn(4);

        when(pawn_1_3.getY()).thenReturn(3);
        when(pawn_5_5.getY()).thenReturn(5);
        when(pawn_4_2.getY()).thenReturn(2);
        when(pawn_2_1.getY()).thenReturn(1);
        when(pawn_4_1.getY()).thenReturn(1);

    }

    @After
    public void tearDown() throws Exception {
        board = null;
    }

    @Test
    public void testGetXSize() throws Exception {
        addPawns();
        assertEquals(board.getXSize(), 5);
    }

    @Test
    public void testGetYSize() throws Exception {
        addPawns();
        assertEquals(board.getYSize(), 8);
    }

    @Test
    public void testGetSquareContent() throws Exception {
        addPawns();
        assertEquals(board.getSquareContent(1, 3), pawn_1_3);
        assertEquals(board.getSquareContent(5, 5), pawn_5_5);
        assertEquals(board.getSquareContent(1, 0), null);
    }

    @Test
    public void testRemovePawn() throws Exception {
        addPawns();
        board.removePawn(pawn_1_3);
        assertEquals(board.getSquareContent(1, 3), null);
        assertEquals(board.numberOfPawns(), 4);
    }

    @Test
    public void testAddPawn() throws Exception {
        addPawns();
        assertEquals(5, board.numberOfPawns());
        board.addPawn(pawn_0_0);
        assertEquals(pawn_0_0, board.getSquareContent(0, 0));
        assertEquals(6, board.numberOfPawns());
    }

    @Test
    public void testIsBonusSquare() throws Exception {
        addPawns();
        assertTrue(board.isBonusSquare(4, 2));
        assertFalse(board.isBonusSquare(3, 1));
    }

    @Test
    public void testNumberOfPawns() throws Exception {
        addPawns();
        assertEquals(4, board.numberOfPawns());
    }

    @Test
    public void testMaxGold() throws Exception {
        addPawns();
        assertEquals(3, board.maxGold());
    }

    @Test
    public void testGetNextPawn() throws Exception {
        addPawns();
        assertEquals(pawn_1_3, board.getNextPawn());
        assertEquals(pawn_5_5, board.getNextPawn());
        assertEquals(pawn_4_2, board.getNextPawn());
        assertEquals(pawn_2_1, board.getNextPawn());
        assertEquals(pawn_4_1, board.getNextPawn());
        assertEquals(pawn_1_3, board.getNextPawn());
        assertEquals(pawn_5_5, board.getNextPawn());
    }

    @Test
    public void testSquareContentSprite() throws Exception {
        addPawns();
        assertEquals('c', board.getSquareContent(1, 3));
        assertEquals('.', board.getSquareContent(0, 1));
        assertEquals('d', board.getSquareContent(5, 5));
        assertEquals('#', board.getSquareContent(4, 2));
        assertEquals('g', board.getSquareContent(4, 1));
    }

    @Test
    public void testToString() throws Exception {
        addPawns();
    }

    @Test
    public void testRemoveAllPawns() throws Exception {
        addPawns();
        assertEquals(4, board.numberOfPawns());
        board.removeAllPawns();
        assertEquals(0, board.numberOfPawns());
    }
}