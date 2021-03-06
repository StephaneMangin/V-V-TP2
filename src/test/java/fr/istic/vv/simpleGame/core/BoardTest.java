package fr.istic.vv.simpleGame.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by stephane on 30/09/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    Board board;
    Pawn pawn_1_3_z;
    Pawn pawn_5_5_d;
    Pawn pawn_5_5_y;
    Pawn pawn_4_2_a;
    Pawn pawn_2_1_b;
    Pawn pawn_4_1_g;
    Pawn pawn_0_0_n;
    Pawn pawn_8_6_h;

    String toStringValue;

    /*
        Initialize the board with 5 pawns
    */
    private void addPawns() {
        board.addPawn(pawn_1_3_z);
        board.addPawn(pawn_5_5_d);
        board.addPawn(pawn_4_2_a);
        board.addPawn(pawn_2_1_b);
        board.addPawn(pawn_4_1_g);
        toStringValue = ".....d..\n"+
                "........\n"+
                ".c......\n"+
                "....a...\n"+
                "..b.g...\n"+
                "....#...\n";
    }

    @Before
    public void setUp() {
        pawn_1_3_z = mock(Pawn.class);
        pawn_5_5_d = mock(Pawn.class);
        pawn_5_5_y = mock(Pawn.class);
        pawn_4_2_a = mock(Pawn.class);
        pawn_2_1_b = mock(Pawn.class);
        pawn_4_1_g = mock(Pawn.class);
        pawn_0_0_n = mock(Pawn.class); // To be add later
        pawn_8_6_h = mock(Pawn.class); // To be add later. Pawn outside the board
        board = new Board(0, 8, 6, 4, 0);

        toStringValue = "........\n"+
                "........\n"+
                "........\n"+
                "........\n"+
                "........\n"+
                "....#...\n";

        when(pawn_1_3_z.getGold()).thenReturn(0);
        when(pawn_5_5_d.getGold()).thenReturn(1);
        when(pawn_5_5_y.getGold()).thenReturn(0); // Test the unplaced property
        when(pawn_4_2_a.getGold()).thenReturn(3);
        when(pawn_2_1_b.getGold()).thenReturn(2);
        when(pawn_4_1_g.getGold()).thenReturn(0);
        when(pawn_0_0_n.getGold()).thenReturn(0);
        when(pawn_8_6_h.getGold()).thenReturn(0);

        when(pawn_1_3_z.getLetter()).thenReturn('z');
        when(pawn_5_5_d.getLetter()).thenReturn('d');
        when(pawn_5_5_y.getLetter()).thenReturn('y');
        when(pawn_4_2_a.getLetter()).thenReturn('a');
        when(pawn_2_1_b.getLetter()).thenReturn('b');
        when(pawn_4_1_g.getLetter()).thenReturn('g');
        when(pawn_0_0_n.getLetter()).thenReturn('n');
        when(pawn_8_6_h.getLetter()).thenReturn('h');

        when(pawn_1_3_z.isDead()).thenReturn(false);
        when(pawn_5_5_d.isDead()).thenReturn(false);
        when(pawn_5_5_y.isDead()).thenReturn(false);
        when(pawn_4_2_a.isDead()).thenReturn(false);
        when(pawn_2_1_b.isDead()).thenReturn(false);
        when(pawn_4_1_g.isDead()).thenReturn(true);
        when(pawn_0_0_n.isDead()).thenReturn(false);
        when(pawn_8_6_h.isDead()).thenReturn(false);

        when(pawn_1_3_z.getX()).thenReturn(1);
        when(pawn_5_5_d.getX()).thenReturn(5);
        when(pawn_5_5_y.getX()).thenReturn(5);
        when(pawn_4_2_a.getX()).thenReturn(4);
        when(pawn_2_1_b.getX()).thenReturn(2);
        when(pawn_4_1_g.getX()).thenReturn(4);
        when(pawn_0_0_n.getX()).thenReturn(0);
        when(pawn_8_6_h.getX()).thenReturn(8);

        when(pawn_1_3_z.getY()).thenReturn(3);
        when(pawn_5_5_d.getY()).thenReturn(5);
        when(pawn_5_5_y.getY()).thenReturn(5);
        when(pawn_4_2_a.getY()).thenReturn(2);
        when(pawn_2_1_b.getY()).thenReturn(1);
        when(pawn_4_1_g.getY()).thenReturn(1);
        when(pawn_0_0_n.getY()).thenReturn(0);
        when(pawn_8_6_h.getY()).thenReturn(6);

    }

    @After
    public void tearDown() {
        board = null;
    }

    /**
     * Tests the constructor with empty pawns.
     *
     * @see Board#Board(int p, int x, int y, int bx, int by)
     * @type Functional
     * @input p=0, x=8, y=5, bx=4, by=0
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testEmptyPawnsConstructor() {
        board = new Board(0, 8, 5, 4, 0);
        assertEquals(0, board.numberOfPawns());
    }

    /**
     * Tests the constructor with empty values.
     *
     * @see Board#Board(int p, int x, int y, int bx, int by)
     * @type Functional
     * @input p=0, x=0, y=0, bx=0, by=0
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testEmptyConstructor() {
        board = new Board(0, 0, 0, 0, 0);
        assertEquals(0, board.numberOfPawns());
        assertEquals(0, board.getXSize());
        assertEquals(0, board.getYSize());
    }

    /**
     * Tests the constructor with pawns.
     *
     * @see Board#Board(int p, int x, int y, int bx, int by)
     * @type Functional
     * @input p=15, x=8, y=5, bx=4, by=0
     * @oracle It must return true.
     * @passed No
     * @correction <pre>
     * l.42
     *  +    private List<Pawn> unplacedPawns;
     * l.68
     * -        for(int i = 0; i<numberOfPawns; i++) {
     * -            Pawn pawn = new Pawn(Character.forDigit(i, 10),
     * -                                 random.nextInt(xSize),random.nextInt(ySize),this);
     * -            this.addPawn(pawn);
     * -        }
     * -        // Allow to not add pawns at init, but null return on getNextPawn call.
     * +        this.unplacedPawns = new ArrayList<Pawn>();
     * +        if (numberOfPawns > 0) {
     * +            for (int i = 0; i < numberOfPawns; i++) {
     * +                // This method does not garantied that a pawn will not be placed inside an already occupied square
     * +                Pawn pawn = new Pawn(Character.forDigit(i, numberOfPawns),
     * +                        random.nextInt(xSize), random.nextInt(ySize), this);
     * +                if (!this.addPawn(pawn)) {
     * +                    unplacedPawns.add(pawn);
     * +                }
     * +            }
     * +            // Allow to not add pawns at init, but null return on getNextPawn call.
     * +             currentPawn = pawns.get(0);
     * +         }
     * +     }
     * l.106
     * -    public void addPawn(Pawn pawn) {
     * +    public Boolean addPawn(Pawn pawn) {
     * +         // If first pawn, get as current one
     * +        Boolean placed = false;
     * -        if (getSquareContent(pawn.getX(),
     * -                             pawn.getY()) == null)
     * -            this.pawns.add(pawn);
     * +        if (pawn.getX() <= xSize && pawn.getY() <= ySize) {
     * +            if (getSquareContent(pawn.getX(), pawn.getY()) == null) {
     * +                this.pawns.add(pawn);
     * +                placed = true;
     * +            }
     * +        }
     * +        return placed;
     * +    }
     * l.213
     * +
     * +    public List<Pawn> getUnplacedPawns() {
     * +        return unplacedPawns;
     * +    }
     * </pre>
     */
    @Test
    public void testNonEMptyPawnsConstructor() {
        board = new Board(15, 8, 5, 4, 0);
        assertEquals(15 - board.getUnplacedPawns().size(), board.numberOfPawns());
    }

    /**
     * Tests the "getXSize" method.
     *
     * @see Board#getXSize()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testGetXSize() {
        addPawns();
        assertEquals(board.getXSize(), 8);
    }

    /**
     * Tests the "getYSize" method.
     *
     * @see Board#getYSize()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testGetYSize() {
        addPawns();
        assertEquals(board.getYSize(), 6);
    }

    /**
     * Tests the "getSquareContent" method with valid values.
     *
     * @see Board#getSquareContent(int x, int y)
     * @type Functional
     * @input x=1, y=3
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testGetSquareContentNonEmpty() {
        addPawns();
        assertEquals(board.getSquareContent(1, 3), pawn_1_3_z);
    }

    /**
     * Tests the "getSquareContent" method with invalid values.
     *
     * @see Board#getSquareContent(int x, int y)
     * @type Functional
     * @input x=1, y=0
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testGetSquareContentEmpty() {
        addPawns();
        assertEquals(board.getSquareContent(1, 0), null);
    }

    /**
     * Tests the "removePawn" method with the help of the "getSquareContent" method with an added pawn.
     *
     * @see Board#removePawn(Pawn p)
     * @type Functional
     * @input p=new Pawn('z', 1, 3, board)
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testRemovePawn_WithSquareContent() {
        addPawns();
        board.removePawn(pawn_1_3_z);
        assertEquals(board.getSquareContent(1, 3), null);
    }

    /**
     * Tests the "removePawn" method with the help of the "numberOfPawns" method with an unadded pawn.
     *
     * @see Board#removePawn(Pawn p)
     * @type Functional
     * @input p=new Pawn('n', 0, 0, board)
     * @oracle It must return false.
     * @passed Yes
     */
    @Test
    public void testRemovePawn_WithNumberOfPawns() {
        addPawns();
        board.removePawn(pawn_0_0_n);
        assertNotEquals(board.numberOfPawns(), 4);
    }

    /**
     * Tests the "addPawn" method.
     *
     * @see Board#addPawn(Pawn p)
     * @type Functional
     * @oracle It must return true.
     * @passed No
     * @correction <pre>
     * l.76
     * -        currentPawn = pawns.get(0);
     * +        // Allow to not add pawns at init, but null return on getNextPawn call.
     * +        if (numberOfPawns > 0) {
     * +            currentPawn = pawns.get(0);
     * +        }
     * }
     * l.107
     * +        // If first pawn, get as current one
     * +        if (pawns.size() == 0) {
     * +            currentPawn = pawn;
     * +        }
     * l.149
     * -        if (pawns.size() == 1) {
     * +        if (pawns.size() == 0) {
     * +            return null;
     * +        }
     * +        else if (pawns.size() == 1) {
     * l.154
     * +            if (currentPawn == null) {
     * +                currentPawn = pawns.get(0);
     * +            }
     * </pre>
     */
    @Test
    public void testAddPawn() {
        addPawns();
        assertEquals(5, board.numberOfPawns());
    }

    /**
     * Tests the "isBonusSquare" method with right value
     *
     * @see Board#isBonusSquare(int x, int y)
     * @type Functional
     * @input x=4, y=0
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testIsBonusSquare_True() {
        addPawns();
        assertTrue(board.isBonusSquare(4, 0));
    }

    /**
     * Tests the "isBonusSquare" method with false value.
     *
     * @see Board#isBonusSquare(int x, int y)
     * @type Functional
     * @input x=3, y=1
     * @oracle It must return false.
     * @passed Yes
     */
    @Test
    public void testIsBonusSquare_False() {
        addPawns();
        assertFalse(board.isBonusSquare(3, 1));
    }

    /**
     * Tests the "numberOfPawns" method.
     *
     * @see Board#numberOfPawns()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testNumberOfPawns() {
        addPawns();
        assertEquals(5, board.numberOfPawns());
    }

    /**
     * Tests the "mawGold" method.
     *
     * @see Board#maxGold()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testMaxGold() {
        addPawns();
        assertEquals(3, board.maxGold());
    }

    /**
     * Tests the "getNextPawn" method with no pawn added.
     *
     * @see Board#getNextPawn()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testGetNextPawn_Empty() {
        assertEquals(null, board.getNextPawn());
    }

    /**
     * Tests the "getNextPawn" method with only one pawn added.
     *
     * @see Board#getNextPawn()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testGetNextPawn_OnePawn() {
        board.addPawn(pawn_0_0_n);
        assertEquals(pawn_0_0_n, board.getNextPawn());
    }

    /**
     * Tests the "getNextPawn" method with added pawns.
     *
     * @see Board#getNextPawn()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testGetNextPawn() {
        addPawns();
        assertEquals(pawn_1_3_z, board.getNextPawn());
        assertEquals(pawn_5_5_d, board.getNextPawn());
        assertEquals(pawn_4_2_a, board.getNextPawn());
        assertEquals(pawn_2_1_b, board.getNextPawn());
        assertEquals(pawn_4_1_g, board.getNextPawn());
        assertEquals(pawn_1_3_z, board.getNextPawn());
        assertEquals(pawn_5_5_d, board.getNextPawn());
    }

    /**
     * Tests the "squareContentSprite" method by returning the special current pawn sprite.
     *
     * @see Board#squareContentSprite(int x, int y)
     * @type Functional
     * @input x=1, y=3
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testSquareContentSprite_CurrentPawn() {
        addPawns();
        assertEquals('c', board.squareContentSprite(1, 3));
    }
    /**
     * Tests the "squareContentSprite" method bvy returning the sprite for an empty square.
     *
     * @see Board#squareContentSprite(int x, int y)
     * @type Functional
     * @input x=0, y=1
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testSquareContentSprite_EmptySquare() {
        addPawns();
        assertEquals('.', board.squareContentSprite(0, 1));
    }
    /**
     * Tests the "squareContentSprite" method by returning a custom pawn sprite.
     *
     * @see Board#squareContentSprite(int x, int y)
     * @type Functional
     * @input x=5, y=5
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testSquareContentSprite_CustomPawn() {
        addPawns();
        assertEquals('d', board.squareContentSprite(5, 5));
    }
    /**
     * Tests the "squareContentSprite" method by returning the bonus square.
     *
     * @see Board#squareContentSprite(int x, int y)
     * @type Functional
     * @input x=4, y=0
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testSquareContentSprite_BonusSquare() {
        addPawns();
        assertEquals('#', board.squareContentSprite(4, 0));
    }

    /**
     * Tests the "toString" method.
     *
     * @see Board#toString()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testToString() {
        addPawns();
        assertEquals(toStringValue, board.toString());
    }

    /**
     * Tests the "numberOfPawns" method.
     *
     * @see Board#numberOfPawns()
     * @type Functional
     * @oracle It must return true.
     * @passed Yes
     */
    @Test
    public void testRemoveAllPawns() {
        addPawns();
        board.removeAllPawns();
        assertEquals(0, board.numberOfPawns());
    }

    /**
     * Tests the "getUnplacedPawns" method.
     *
     * @see Board#getUnplacedPawns()
     * @type Functional
     * @oracle It must return 2.
     * @passed No
     * @correction <pre>
     * l.80
     * -                if (!this.addPawn(pawn)) {
     * -                    unplacedPawns.add(pawn);
     * -                }
     * +                this.addPawn(pawn);
     * l.115
     * +     * @return True if the pawn has been placed or false either.
     * l.123
     * -        if (pawn.getX() <= xSize && pawn.getY() <= ySize) {
     * +        if (pawn.getX() < xSize && pawn.getY() < ySize) {
     * l.126
     * +            } else {
     * +                unplacedPawns.add(pawn);
     * +            }
     * +        } else {
     * +            unplacedPawns.add(pawn);
     * </pre>
     */
    @Test
    public void testGetUnplacedPawns() {
        addPawns();
        ArrayList<Pawn> unplacedPaws = new ArrayList<>();
        unplacedPaws.add(pawn_5_5_y);
        unplacedPaws.add(pawn_8_6_h);
        board.addPawn(pawn_5_5_y);
        board.addPawn(pawn_8_6_h);
        assertEquals(unplacedPaws, board.getUnplacedPawns());
    }
}
