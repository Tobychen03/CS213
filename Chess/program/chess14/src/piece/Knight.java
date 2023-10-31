package src.piece;

import java.util.Objects;

/**
 * Knight class
 * @author Tianle Chen tc822, Yanchen Fan
 */
public class Knight extends Piece{


    /**
     * constructor to create a Knight
     * @param camp camp
     * @param x x
     * @param y y
     */
    public Knight(String camp, int x, int y){super(camp, "N", x, y);}

    /**
     * override move method
     * determine whether the Knight can move to new location
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if it can move, false if not
     */
    @Override
    public boolean move(int newx, int newy, Piece[][] pane) {
        if(x == newx && y == newy){
            return false;
        }
        if(newx < 0 || newy < 0 || newx >7 || newy > 7){
            return false;
        }
        if(!((Math.abs(x-newx) == 1 && Math.abs(y-newy) == 2) || (Math.abs(x-newx) == 2 && Math.abs(y-newy) == 1))){
            return false;
        }
        if(Objects.equals(camp, "w") && Objects.equals(pane[newx][newy].getCamp(), "w")){
            return false;
        }
        if(Objects.equals(camp, "b") && Objects.equals(pane[newx][newy].getCamp(), "b")){
            return false;
        }
        return true;
    }

    /**
     * override noObstruct method
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true
     */
    @Override
    public boolean noObstruct(int newx, int newy, Piece[][] pane) {
        return true;
    }
}
