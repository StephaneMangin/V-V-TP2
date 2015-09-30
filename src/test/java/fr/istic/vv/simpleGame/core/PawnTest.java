package fr.istic.vv.simpleGame.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by stephane on 30/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
// This is required for mocks to work
public class PawnTest {

    Board board;

    @Before
    public void setUp() throws Exception {
        Pawn pawn_1_3 = mock(Pawn.class);
        Pawn pawn_5_5 = mock(Pawn.class);
        Pawn pawn_4_2 = mock(Pawn.class);
        Pawn pawn_2_1 = mock(Pawn.class);
        Pawn pawn_4_1 = mock(Pawn.class);
        board = new Board(0, 5, 5, 1, 3);
        board.addPawn(pawn_1_3);
        board.addPawn(pawn_5_5);
        board.addPawn(pawn_4_2);
        board.addPawn(pawn_2_1);
        board.addPawn(pawn_4_1);

        when(pawn_1_3.isDead()).thenReturn(true);
        when(pawn_5_5.isDead()).thenReturn(true);
        when(pawn_4_2.isDead()).thenReturn(true);
        when(pawn_2_1.isDead()).thenReturn(true);
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
    public void testGetX() throws Exception {

    }

    @Test
    public void testGetY() throws Exception {

    }

    @Test
    public void testGetLetter() throws Exception {

    }

    @Test
    public void testGetGold() throws Exception {

    }

    @Test
    public void testMove() throws Exception {

    }

    @Test
    public void testIsDead() throws Exception {

    }
}