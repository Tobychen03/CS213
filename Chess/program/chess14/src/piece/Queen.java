package src.piece;

import java.util.Objects;

/**
 * Queen class
 * @author Tianle Chen tc822, Yanchen Fan
 */
public class Queen extends Piece
{
    /**
     * constructor to create Queen
     * @param camp camp
     * @param x x
     * @param y y
     */
    public Queen(String camp, int x, int y){super(camp, "Q", x, y);}

    /**
     * override move method
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if queen can move to new location, false if not
     */
    @Override
    public boolean move (int newx, int newy,Piece[][]pane)
    {
        if(x == newx && y == newy){
            return false;
        }
        if(newx < 0 || newy < 0 || newx >7 || newy > 7)
        {
            return false;
        }
        if((Math.abs(x-newx) != Math.abs(y-newy)) && (x != newx && y != newy))
        {
            return false;
        }
        if(!noObstruct(newx,newy,pane))
        {
            return false;
        }
        if(Objects.equals(camp, "w") && Objects.equals(pane[newx][newy].getCamp(), "w"))
        {
            return false;
        }
        if(Objects.equals(camp, "b") && Objects.equals(pane[newx][newy].getCamp(), "b"))
        {
            return false;
        }
        return true;
    }

    /**
     * Override noObstruct method
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if no obstruct, false if it has obstructed
     */
    @Override
    public boolean noObstruct(int newx, int newy, Piece[][] pane) {

        int stepx = Math.abs(x-newx)-1;
        int stepy = Math.abs(y-newy)-1;

        if(newx == x && newy > y)
        {
            for(int i = 1; i <= stepy;i++)
            {
                if(pane[x][y+i].getType() != null)
                {
                    return false;
                }
            }
        }
        if(newx == x && newy < y)
        {
            for(int i = 1; i <= stepy;i++)
            {
                if(pane[x][y-i].getType() != null)
                {
                    return false;
                }
            }
        }
        if(newx > x && newy == y)
        {
            for(int i = 1; i <=stepx;i++)
            {
                if(pane[x+i][y].getType() != null)
                {
                    return false;
                }
            }
        }
        if(newx < x && newy == y)
        {
            for(int i = 1; i <=stepx;i++)
            {
                if(pane[x-i][y].getType() != null)
                {
                    return false;
                }
            }
        }
        if(newx > x && newy > y){
            for(int i = 1; i <= stepx; i++){
                if(!(pane[x+i][y+i].getType() == null)){
                    return false;
                }
            }
        }
        if(newx > x && newy < y){
            for(int i = 1; i <= stepx; i++){
                if(!(pane[x+i][y-i].getType() == null)){
                    return false;
                }
            }
        }
        if(newx < x && newy > y){
            for(int i = 1; i <= stepx; i++){
                if(!(pane[x-i][y+i].getType() == null)){
                    return false;
                }
            }
        }
        if(newx < x && newy < y){
            for(int i = 1; i <= stepx; i++){
                if(!(pane[x-i][y-i].getType() == null)){
                    return false;
                }
            }
        }
        return true;
    }
}


