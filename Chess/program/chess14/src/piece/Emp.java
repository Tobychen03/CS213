package src.piece;

/**
 * Empty class
 * @author Tianle Chen tc822, Yanchen Fan
 */
public class Emp extends Piece{

    /**
     * constructor to create an empty entity
     * @param x
     * @param y
     */
    public Emp(int x, int y){
        super(null,null,x,y);
    }

    /**
     * override move function
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return false
     */
    @Override
    public boolean move(int newx, int newy, Piece[][] pane) {
        return false;
    }

    /**
     * override noObstruct method
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return false
     */
    @Override
    public boolean noObstruct(int newx, int newy, Piece[][] pane) {
        return false;
    }
}
