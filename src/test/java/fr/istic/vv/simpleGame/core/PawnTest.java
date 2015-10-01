package fr.istic.vv.simpleGame.core;

import fr.istic.vv.simpleGame.exception.OutOfBoardException;
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
     * Tests the "GetGold" method with two pawns.
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
        try {
            pawn2.move(Direction.Up);
            pawn2.move(Direction.Up);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertEquals(1, pawn2.getGold());
    }

    /**
     * Tests the "move" method for OutOfBoardException throw
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input Direction=Direction.UP
     * @oracle The oracle should return True
     * @passed Yes
     */
    @Test
    public void testMoveOutOfBoardException() {
        Pawn pawn = new Pawn('z', 5, 5, board);
        board.addPawn(pawn);
        Throwable e = null;

        try {
            pawn.move(Direction.Up);
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof OutOfBoardException);
    }

    /**
     * Tests the "isDead" method with two contiguous pawns and the attacker in the bonus square.
     *
     * @see Pawn#isDead()
     * @type Functional
     * @oracle The value should be true
     * @passed Yes but errors on test construction permits to fix bug on CLIMain ;)
     * @correction <pre>
     * l.19 on CLIMain
     * -            int chosenint;
     * -            for (chosenint = -10;
     * -                    chosenint >= Direction.values().length
     * -                    || chosenint <0; chosenint=scanner.nextInt()) {
     * +            int maxValues = Direction.values().length;
     * +            int choice = -10;
     * +            do {
     * l.25 on CLIMain
     * -            }
     * +                choice = scanner.nextInt();
     * +            } while (choice >= maxValues || choice < 0);
     * l.28 on CLIMain
     * -                                       Direction.values()[chosenint]));
     * +                                       Direction.values()[choice]));
     * </pre>
     */
    @Test
    public void testIsDead_BonusSquare() {
        Pawn pawn1 = new Pawn('z', 3, 4, board);
        Pawn pawn2 = new Pawn('y', 3, 3, board);
        board.addPawn(pawn1);
        board.addPawn(pawn2);
        try {
            pawn2.move(Direction.Up);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertTrue(pawn1.isDead());
    }

    /**
     * Tests the "Move" method.
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input Direction=Direction.Right
     * @oracle The pawn should be located one case at right of his position
     * @passed Yes
     */
    @Test
    public void testMove_Right() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        try {
            pawn1.move(Direction.Right);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertEquals(4, pawn1.getX());
    }
    /**
     * Tests the "Move" method.
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input Direction=Direction.Left
     * @oracle The pawn should be located one case at left of his position
     * @passed Yes
     */
    @Test
    public void testMove_Left() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        try {
            pawn1.move(Direction.Left);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertEquals(2, pawn1.getX());
    }


    /**
     * Tests the "Move" method.
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input DIrection=Direction.Down
     * @oracle The pawn should be located one case under his position
     * @passed Yes
     */
    @Test
    public void testMove_Down() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        try {
            pawn1.move(Direction.Down);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertEquals(1, pawn1.getY());
    }

    /**
     * Tests the "Move" method.
     *
     * @see Pawn#move(Direction)
     * @type Functional
     * @input DIrection=Direction.Up
     * @oracle The pawn should be located one case above his position
     * @passed Yes
     */
    @Test
    public void testMove_Up() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        try {
            pawn1.move(Direction.Up);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertEquals(3, pawn1.getY());
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
        try {
            pawn1.move(Direction.Up);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertEquals(3, pawn1.getY());
    }

    /**
     * Tests the "isDead" method a sample pawn.
     *
     * @see Pawn#isDead()
     * @type Functional
     * @input A new pawn which has received 5 hits
     * @oracle isDead should return true
     * @passed Yes
     */
    @Test
    public void testIsTotallyDead() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        Pawn pawn2 = new Pawn('y', 3, 1, board);
        board.addPawn(pawn1);
        board.addPawn(pawn2);
        try {
            pawn2.move(Direction.Up);
            pawn2.move(Direction.Up);
            pawn2.move(Direction.Up);
            pawn2.move(Direction.Up);
            pawn2.move(Direction.Up);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertTrue(pawn1.isDead());
    }

    /**
     * Tests the "isDead" method with two attacking each other.
     *
     * @see Pawn#isDead()
     * @type Functional
     * @input A pawn which has received 2 hits and an other 1
     * @oracle  should return true for pawn 1 and false for pawn 2
     * @passed Yes
     */
    @Test
    public void testIsDead() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        Pawn pawn2 = new Pawn('y', 3, 1, board);
        board.addPawn(pawn1);
        board.addPawn(pawn2);
        try {
            pawn2.move(Direction.Up);
            pawn1.move(Direction.Down);
            pawn2.move(Direction.Up);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertTrue(pawn1.isDead());
        assertFalse(pawn2.isDead());
    }

    /**
     * Tests the "isDead" method with a sample pawn
     *
     * @see Pawn#isDead()
     * @type Functional
     * @input A new pawn which has received 1 hits
     * @oracle isDead should return false
     * @passed Yes
     */
    @Test
    public void testIsDeadNot() {
        Pawn pawn1 = new Pawn('z', 3, 2, board);
        Pawn pawn2 = new Pawn('y', 3, 1, board);
        board.addPawn(pawn1);
        board.addPawn(pawn2);
        try {
            pawn2.move(Direction.Up);
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }
        assertFalse(pawn1.isDead());
    }
}