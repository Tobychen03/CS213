package src.piece;


import java.util.Objects;

/**
 * King class
 * @author Tianle Chen tc822, Yanchen Fan
 */
public class King extends Piece {

    /**
     *  Assume King is moved
     */
    boolean isMove = true;

    /**
     * create a constructor for King
     * @param camp camp
     * @param x x
     * @param y y
     */
    public King(String camp, int x, int y) {super(camp, "K", x , y);}

    /**
     * inherit method from Piece
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true
     */
    @Override
    public boolean noObstruct(int newx, int newy, Piece[][] pane)
    {
        return true;
    }


    /**
     * create King in the beginning with not move
     * @param camp camp
     * @param x x
     * @param y y
     * @param isMove determine whether is moved
     */
    public King(String camp, int x, int y,boolean isMove)
    {
        super(camp, "K", x , y);
        this.isMove = isMove;
    }

    /**
     * get isMove
     * @return isMove which can know whether King move
     */
    @Override
    public boolean getIsMove()
    {
        return isMove;
    }

    /**
     * set isMove if King move
     */
    @Override
    public void setIsMove(){
        this.isMove = true;
    }


    /**
     * determine whether King can move to a new location
     * @param newx new x
     * @param newy new y
     * @param pane checkerboard
     * @return true if it can move to new location, false if not
     */
    @Override
    public boolean move(int newx, int newy, Piece[][] pane)
    {
        if(x == newx && y == newy){
            return false;
        }
        if(newx < 0 || newy < 0 || newx >7 || newy > 7){
            return false;
        }
        if(!isMove && Objects.equals(camp, "w") && newx == 0 && newy == 2 && !pane[0][0].getIsMove()){
            if(pane[0][1].getType() == null && pane[0][2].getType() == null && pane[0][3].getType() == null){
                return true;
            }
        }
        if(!isMove && Objects.equals(camp, "w") && newx == 0 && newy == 6 && !pane[0][7].getIsMove()){
            if(pane[0][5].getType() == null && pane[0][6].getType() == null){
                return true;
            }
        }
        if(!isMove && Objects.equals(camp, "b") && newx == 7 && newy == 6 && !pane[7][7].getIsMove()){
            if(pane[7][5].getType() == null && pane[7][6].getType() == null){
                return true;
            }
        }
        if(!isMove && Objects.equals(camp, "b") && newx == 7 && newy == 2 && !pane[7][0].getIsMove()){
            if(pane[7][1].getType() == null && pane[7][2].getType() == null && pane[7][3].getType() == null){
                return true;
            }
        }
        if(Math.abs(newx - x) > 1 || Math.abs(newy - y) > 1)
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




}
