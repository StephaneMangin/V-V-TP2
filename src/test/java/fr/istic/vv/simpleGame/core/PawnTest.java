package fr.istic.vv.simpleGame.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by stephane on 30/09/15.
 */
// This is required for mocks to work
public class PawnTest {

    Board board;


    @Before
    public void setUp() {
        board = new Board(0, 5, 5, 3, 3);

    }

    @After
    public void tearDown() {
        board = null;
    }

    /**
     * Tests the "GetX" method with a sample pawn
     *
     * @see Pawn#getX()
     * @type Functional
     * @input A pawn located in 3, 2
     * @oracle The X coordinate should be 3
     * @passed Yes
     */
    @Test
    public void testGetX() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        assertEquals(3, pawn1.getX());
    }

    /**
     * Tests the "GetY" method with a sample pawn
     *
     * @see Pawn#getY()
     * @type Functional
     * @input A pawn located in 3, 2
     * @oracle The Y coordinate should be 2
     * @passed Yes
     */
    @Test
    public void testGetY() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        assertEquals(2, pawn1.getY());

    }

    /**
     * Tests the "GetLetter" method with a sample pawn
     *
     * @see Pawn#getLetter()
     * @type Functional
     * @input A pawn with associated letter z
     * @oracle The letter should be z
     * @passed Yes
     */
    @Test
    public void testGetLetter() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        assertEquals('z', pawn1.getLetter());
    }

    /**
     * Tests the "GetGold" method with a sample pawn
     *
     * @see Pawn#getGold()
     * @type Functional
     * @input A new pawn with no gold
     * @oracle The value of gold returned should be 0
     * @passed Yes
     */
    @Test
    public void testGetGold_0() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        assertEquals(0, pawn1.getGold());
    }

    /**
     * Tests the "GetGold" method with a sample pawn
     *
     * @see Pawn#getGold()
     * @type Functional
     * @input A new pawn which has won 1 gold
     * @oracle The value of gold returned should be 1
     * @passed Yes
     */
    @Test
    public void testGetGold_1() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        Pawn pawn2 = new Pawn('y', 3, 1, board);
        board.addPawn(pawn1);
        board.addPawn(pawn2);
        assertEquals(0, pawn1.getGold());
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        assertEquals(1, pawn2.getGold());
    }

    /**
     * Tests the "Move" method with a sample pawn and direction
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input A sample pawn and the direction up
     * @oracle The pawn should be located one case above
     * @passed Yes
     */
    @Test
    public void testMove() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        pawn1.move(Direction.Up);
        assertEquals(3, pawn1.getY());
    }

    /**
     * Tests the "isDead" method with a sample pawn
     *
     * @see Pawn#isDead()
     * @type Functional
     * @input A new pawn which has received 5 hits
     * @oracle isDead should return true
     * @passed Yes
     */
    @Test
    public void testIsDead() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        Pawn pawn2 = new Pawn('y', 3, 1, board);
        board.addPawn(pawn1);
        board.addPawn(pawn2);
        assertEquals(0, pawn1.getGold());
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        pawn2.move(Direction.Up);
        assertTrue(pawn1.isDead());

    }
}