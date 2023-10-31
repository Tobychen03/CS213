package src.piece;

/**
 * abstract class for piece
 * @author Tianle Chen tc822, Yanchen Fan
 */
 public abstract class Piece {
    /**
     * black or white chess
     */
    public String camp;
    /**
     * type of chess
     */
    public String type;
    /**
     * location of chess, x is row, y is column
     */
    public int x;
    /**
     * location of chess
     */
    public int y;


    /**
     * create a Piece
     * @param type type of piece
     * @param x x
     * @param y y
     */
    public Piece(String camp,String type, int x, int y){
        this.camp = camp;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /**
     * chess move to newx and newy
     * if there has a chess in path, return false
     * Use right way to move for each type of chess
     * @param newx new x
     * @param newy new y
     * @return true if piece can move to new location, false if not
     */
    public abstract boolean move(int newx, int newy, Piece[][] pane);

    /**
     * get the camp of piece
     * @return the camp of piece
     */
    public String getCamp(){
        return camp;
    }

    /**
     * get the type of piece
     * @return the type of piece
     */
    public String getType() {
        return type;
    }

    /**
     * get the row of piece
     * @return x which is row of piece
     */
    public int getX(){
        return x;
    }

    /**
     * get the column of piece
     * @return y which is the column of piece
     */
    public int getY(){
        return y;
    }

    /**
     * set the camp of piece
     * @param camp camp
     */
    public void setCamp(String camp){
        this.camp = camp;
    }

    /**
     * set the type of piece
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * set the row of piece
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * set the column of piece
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * method to determine no obstruct in path
     * @return true if it has not obstructed in the path, false if it has
     */
    public abstract boolean noObstruct(int newx, int newy, Piece[][] pane);

    /**
     * override toString method
     * @return the name of piece
     */
    @Override
    public String toString(){
        if(camp == null){
            if((x+y)%2 == 1){
                return "  ";
            }else{
                return "##";
            }
        }
        return camp+type;
    }

    /**
     * determine whether the piece was moved
     * @return isMove
     */
    public boolean getIsMove() {
        return true;
    }

    /**
     * set whether the piece was moved
     */
    public void setIsMove(){
    }
}
