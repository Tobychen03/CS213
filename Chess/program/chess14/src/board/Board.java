package src.board;
import src.piece.*;

/**
 * Board class contain checkerboard
 * You can print checkerboard
 *
 * @author Tianle Chen tc822, Chenyan Fan
 */
public class Board {

    /**
     * create a checkerboard
     */
    Piece[][] pane = new Piece[8][8];

    /**
     * constructor: the default start of the board
     */
    public Board(){
        pane[0][0] = new Rook("w", 0,0,false);
        pane[0][1] = new Knight("w",0,1);
        pane[0][2] = new Bishop("w",0,2);
        pane[0][3] = new Queen("w",0,3);
        pane[0][4] = new King("w",0,4,false);
        pane[0][5] = new Bishop("w",0,5);
        pane[0][6] = new Knight("w",0,6);
        pane[0][7] = new Rook("w", 0,7, false);
        for(int i = 0; i < 8; i++){
            pane[1][i] = new Pawn("w",1,i);
        }
        for(int i = 0; i < 8; i++){
            pane[6][i] = new Pawn("b",6,i);
        }
        pane[7][0] = new Rook("b",7,0,false);
        pane[7][1] = new Knight("b",7,1);
        pane[7][2] = new Bishop("b",7,2);
        pane[7][3] = new Queen("b",7,3);
        pane[7][4] = new King("b",7,4,false);
        pane[7][5] = new Bishop("b",7,5);
        pane[7][6] = new Knight("b",7,6);
        pane[7][7] = new Rook("b", 7,7,false);
        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                pane[i][j] = new Emp(i,j);
            }
        }
    }

    /**
     * get the pane which is checkerboard
     * @return pane
     */
    public Piece[][] getPane(){
        return pane;
    }

    /**
     * change the pane
     * @param pane checkerboard
     */
    public void setPane(Piece[][] pane){
        this.pane = pane;
    }

    /**
     * print the checkerboard and all pieces in it
     */
    public void printPane(){
        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < 8; j++){
                System.out.print(pane[i][j] + " ");
            }
            System.out.println(i+1);
        }
        System.out.println(" a  b  c  d  e  f  g  h\n");

    }


}
