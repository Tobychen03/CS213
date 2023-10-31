package src.piece;

import java.util.Objects;

/**
 * Rook class
 * @author Tianle Chen tc822, Yanchen Fan
 */
public class Rook extends Piece{

    /**
     * create Rook construction
     * @param camp camp
     * @param x x
     * @param y y
     */
    public Rook(String camp, int x, int y){super(camp,"R", x, y);}

    /**
     * default is moved
     */
    boolean isMove = true;


    /**
     * Construction. Set Rook is not moved in the beginning
     * @param camp camp
     * @param x x
     * @param y y
     * @param isMove isMove
     */
    public Rook(String camp, int x, int y,boolean isMove)
    {
        super(camp, "R", x , y);
        this.isMove = isMove;
    }


    /**
     * get isMove
     * @return isMove
     */
    @Override
    public boolean getIsMove()
    {
        return isMove;
    }

    /**
     * set isMove
     */
    @Override
    public void setIsMove(){
        this.isMove = true;
    }

    /**
     * override move method
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if Rook can move to new location, false if not
     */
    @Override
    public boolean move(int newx, int newy, Piece[][] pane) {

        if(x == newx && y == newy){
            return false;
        }
        if(newx < 0 || newy < 0 || newx >7 || newy > 7)
        {
            return false;
        }
        if(x != newx && y != newy){
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
     * override noObstruct method
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if it has no obstruction, false if it has obstruction
     */
    @Override
    public boolean noObstruct(int newx, int newy, Piece[][] pane) {
        int step = 0;
        if(x != newx){
            step = Math.abs(newx -x)-1;
        }else {
            step = Math.abs(newy -y)-1;
        }

        if(newx == x && newy > y)
        {
            for(int i = 1; i <=step;i++)
            {
                if(pane[x][y+i].getType() != null )
                {
                    return false;
                }
            }
        }
        if(newx == x && newy < y)
        {
            for(int i = 1; i <=step;i++)
            {
                if(pane[x][y-i].getType() != null)
                {
                    return false;
                }
            }
        }
        if(newx > x && newy == y)
        {
            for(int i = 1; i <=step;i++)
            {
                if(pane[x+i][y].getType() != null)
                {
                    return false;
                }
            }
        }
        if(newx < x && newy == y)
        {
            for(int i = 1; i <=step;i++)
            {
                if(pane[x-i][y].getType() != null)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
