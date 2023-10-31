package src.piece;

import java.util.Objects;

/**
 * Bishop class
 * @author Tianle Chen tc822, Yanchen Fan
 */
public class Bishop extends Piece{

    /**
     * constructor of Bishop
     * @param camp camp
     * @param x x
     * @param y y
     */
    public Bishop(String camp, int x, int y){
        super(camp, "B", x, y);
    }

    /**
     * move to a new location
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
        if(Math.abs(x-newx) != Math.abs(y-newy)){
            return false;
        }

        if(!noObstruct(newx,newy,pane)){
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
     * determine whether there is obstructed in the path
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if it has not obstructed, false if it has
     */
    @Override
    public boolean noObstruct(int newx, int newy, Piece[][] pane) {
        int step = Math.abs(x-newx)-1;
        if(step == 0){
            return true;
        }

        if(newx > x && newy > y){
            for(int i = 1; i <= step; i++){
                if(!(pane[x+i][y+i].getType() == null)){
                    return false;
                }
            }
        }

        if(newx > x && newy < y){
            for(int i = 1; i <= step; i++){
                if(!(pane[x+i][y-i].getType() == null)){
                    return false;
                }
            }
        }

        if(newx < x && newy > y){
            for(int i = 1; i <= step; i++){
                if(!(pane[x-i][y+i].getType() == null)){
                    return false;
                }
            }
        }

        if(newx < x && newy < y){
            for(int i = 1; i <= step; i++){
                if(!(pane[x-i][y-i].getType() == null)){
                    return false;
                }
            }
        }

        return true;
    }
}
