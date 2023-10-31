package src.piece;

import java.util.Objects;

/**
 * Pawn class
 * @author Tianle Chen tc822, Yanchen Fan
 */
public class Pawn extends Piece{
    /**
     * constructor to create a Pawn
     * @param camp camp
     * @param x x
     * @param y y
     */
    public Pawn(String camp, int x, int y){
        super(camp, "p", x, y);
    }

    /**
     * determine whether there has obstruction in the path
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if it has no obstruction, false if it has obstruction
     */
    @Override
    public boolean noObstruct(int newx, int newy, Piece[][] pane){
        if(Objects.equals(camp, "w")){
            for(int i = x+1; i <= newx; i++){
                if(!(pane[i][newy] instanceof Emp)){
                    return false;
                }
            }
        }
        if(Objects.equals(camp, "b")){
            for(int i = x-1; i >= newx; i--){
                if(!(pane[i][newy] instanceof Emp)){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * override move method
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if it can move, false if not
     */
    @Override
    public boolean move(int newx, int newy, Piece[][] pane){
        if(x == newx && y == newy){
            return false;
        }
        if(newx < 0 || newy < 0 || newx >7 || newy > 7){
            return false;
        }
        if(Objects.equals(camp, "w") && x == 1){
            if(y == newy && noObstruct(newx,newy, pane)){
                if(newx == 2 || newx == 3){
                    return true;
                }
            }
        }
        if(Objects.equals(camp, "b") && x == 6){
            if(y == newy && noObstruct(newx,newy, pane)){
                if(newx == 4 || newx == 5){
                    return true;
                }
            }
        }
        if(Objects.equals(camp, "w") && y == newy && newx == x+1 && noObstruct(newx,newy,pane)){
            return true;
        }
        if(Objects.equals(camp, "b") && y == newy && newx == x-1 && noObstruct(newx,newy,pane)){
            return true;
        }
        if(Objects.equals(camp, "w") && newx == x +1 && newy == y -1 && Objects.equals(pane[newx][newy].getCamp(), "b")){
            return true;
        }
        if(Objects.equals(camp, "w") && newx == x +1 && newy == y +1 && Objects.equals(pane[newx][newy].getCamp(), "b")){
            return true;
        }
        if(Objects.equals(camp, "b") && newx == x -1 && newy == y -1 && Objects.equals(pane[newx][newy].getCamp(), "w")){
            return true;
        }
        if(Objects.equals(camp, "b") && newx == x +1 && newy == y -1 && Objects.equals(pane[newx][newy].getCamp(), "w")){
            return true;
        }
        return false;
    }


}
