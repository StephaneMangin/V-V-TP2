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

    String toStringValue;

    /*
        Initialize the board with 5 pawns
    */
    private void addPawns() {
        board.addPawn(pawn_1_3);
        board.addPawn(pawn_5_5);
        board.addPawn(pawn_4_2);
        board.addPawn(pawn_2_1);
        board.addPawn(pawn_4_1);
        toStringValue = "........\n"+
                        ".c......\n"+
                        "....a...\n"+
                        "..d.g...\n"+
                        "....#...\n";
    }

    @Before
    public void setUp() throws Exception {
        pawn_1_3 = mock(Pawn.class);
        pawn_5_5 = mock(Pawn.class);
        pawn_4_2 = mock(Pawn.class);
        pawn_2_1 = mock(Pawn.class);
        pawn_4_1 = mock(Pawn.class);
        pawn_0_0 = mock(Pawn.class); // To be add later
        board = new Board(0, 8, 5, 4, 0);

        toStringValue = "........\n"+
                        "........\n"+
                        "........\n"+
                        "........\n"+
                        "....#...\n";

        when(pawn_1_3.getGold()).thenReturn(0);
        when(pawn_5_5.getGold()).thenReturn(1);
        when(pawn_4_2.getGold()).thenReturn(3);
        when(pawn_2_1.getGold()).thenReturn(2);
        when(pawn_4_1.getGold()).thenReturn(0);
        when(pawn_0_0.getGold()).thenReturn(0);

        when(pawn_1_3.getLetter()).thenReturn('z');
        when(pawn_5_5.getLetter()).thenReturn('d');
        when(pawn_4_2.getLetter()).thenReturn('a');
        when(pawn_2_1.getLetter()).thenReturn('d');
        when(pawn_4_1.getLetter()).thenReturn('g');
        when(pawn_0_0.getLetter()).thenReturn('n');

        when(pawn_1_3.isDead()).thenReturn(false);
        when(pawn_5_5.isDead()).thenReturn(false);
        when(pawn_4_2.isDead()).thenReturn(false);
        when(pawn_2_1.isDead()).thenReturn(false);
        when(pawn_4_1.isDead()).thenReturn(true);
        when(pawn_0_0.isDead()).thenReturn(false);

        when(pawn_1_3.getX()).thenReturn(1);
        when(pawn_5_5.getX()).thenReturn(5);
        when(pawn_4_2.getX()).thenReturn(4);
        when(pawn_2_1.getX()).thenReturn(2);
        when(pawn_4_1.getX()).thenReturn(4);
        when(pawn_0_0.getX()).thenReturn(0);

        when(pawn_1_3.getY()).thenReturn(3);
        when(pawn_5_5.getY()).thenReturn(5);
        when(pawn_4_2.getY()).thenReturn(2);
        when(pawn_2_1.getY()).thenReturn(1);
        when(pawn_4_1.getY()).thenReturn(1);
        when(pawn_0_0.getY()).thenReturn(0);

    }

    @After
    public void tearDown() throws Exception {
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
    public void testEmptyConstructor() throws Exception {
        board = new Board(0, 8, 5, 4, 0);
        assertEquals(0, board.numberOfPawns());
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
     * +    private List<Pawn> unplacedPawns;
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
    public void testConstructor() throws Exception {
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
    public void testGetXSize() throws Exception {
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
    public void testGetYSize() throws Exception {
        addPawns();
        assertEquals(board.getYSize(), 5);
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
    public void testGetSquareContentNonEmpty() throws Exception {
        addPawns();
        assertEquals(board.getSquareContent(1, 3), pawn_1_3);
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
    public void testGetSquareContentEmpty() throws Exception {
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
    public void testRemovePawn_WithSquareContent() throws Exception {
        addPawns();
        board.removePawn(pawn_1_3);
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
    public void testRemovePawn_WithNumberOfPawns() throws Exception {
        addPawns();
        board.removePawn(pawn_0_0);
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
    public void testAddPawn() throws Exception {
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
    public void testIsBonusSquare_True() throws Exception {
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
    public void testIsBonusSquare_False() throws Exception {
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
    public void testNumberOfPawns() throws Exception {
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
    public void testMaxGold() throws Exception {
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
    public void testGetNextPawn_Empty() throws Exception {
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
    public void testGetNextPawn_OnePawn() throws Exception {
        board.addPawn(pawn_0_0);
        assertEquals(pawn_0_0, board.getNextPawn());
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
    public void testSquareContentSprite_CurrentPawn() throws Exception {
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
    public void testSquareContentSprite_EmptySquare() throws Exception {
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
    public void testSquareContentSprite_CustomPawn() throws Exception {
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
    public void testSquareContentSprite_BonusSquare() throws Exception {
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
    public void testToString() throws Exception {
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
    public void testRemoveAllPawns() throws Exception {
        addPawns();
        board.removeAllPawns();
        assertEquals(0, board.numberOfPawns());
    }
}