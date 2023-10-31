package src.chess;
import src.board.*;
import src.piece.*;

import java.util.Objects;
import java.util.Scanner;

/**
 * Chess class
 * main function to run the application
 * @author Tianle Chen tc822, Chenyan Fan
 */
public class Chess {

    /**
     * main function
     * contain resign function
     * scan input
     * contain piece move
     * contain draw function
     * contain castling move
     * contain pawn promotion
     * Re-enter if invalid input
     * @param args
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Board board = new Board();
        int count = 0;
        boolean isCon = true;
        while(true) {
            if (isCon) {
                board.printPane();
            } else {
                isCon = true;
            }


            if(check(board,count)){
                System.out.println("Check");
            }

            if (count % 2 == 0) {
                System.out.print("White's move: ");
            } else {
                System.out.print("Black's move: ");
            }
            String line = s.nextLine();
            String[] input = line.split(" ");
            if (input.length == 1 && Objects.equals(input[0], "resign")) {
                if (count % 2 == 0) {
                    System.out.print("Black wins");
                    break;
                } else {
                    System.out.print("White wins");
                    break;
                }
            }
            if (input.length != 2 && input.length != 3) {
                System.out.println("Illegal move, try again");
                isCon = false;
                continue;
            }




            int ox = 0;
            int oy = 0;
            int newx = 0;
            int newy = 0;
            if (input[0].length() != 2) {
                System.out.println("Illegal move, try again");
                isCon = false;
                continue;
            } else {
                oy = getColumn(input[0].charAt(0));
                ox = Character.getNumericValue(input[0].charAt(1)) - 1;
            }
            if (input[1].length() != 2) {
                System.out.println("Illegal move, try again");
                isCon = false;
                continue;
            } else {
                newy = getColumn(input[1].charAt(0));
                newx = Character.getNumericValue(input[1].charAt(1)) - 1;
            }


            if(ox == newx && oy == newy){
                System.out.println("Illegal move, try again");
                isCon = false;
                continue;
            }

            if(checkme(ox, oy, newx, newy, count, input, board)){
                System.out.println("Illegal move, try again");
                isCon = false;
                continue;
            }



            Piece[][] pane = board.getPane();



            if (((Objects.equals(pane[ox][oy].getCamp(), "w") && count % 2 == 0) || (Objects.equals(pane[ox][oy].getCamp(), "b") && count % 2 == 1)) && pane[ox][oy].move(newx, newy, pane)) {
                if(input.length == 3){
                    if (Objects.equals(input[2], "draw?")) {
                        System.out.println("draw");
                        break;
                    }else if(Objects.equals(pane[ox][oy].getType(), "p")){
                        if(Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 7){
                            if(Objects.equals(input[2], "R")){
                                pane[newx][newy] = new Rook("w",newx,newy);
                            }else if(Objects.equals(input[2], "N")){
                                pane[newx][newy] = new Knight("w",newx,newy);
                            }else if(Objects.equals(input[2], "B")){
                                pane[newx][newy] = new Bishop("w",newx,newy);
                            }else if(Objects.equals(input[2], "Q")){
                                pane[newx][newy] = new Queen("w",newx,newy);
                            }
                        }else if(Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 0){
                            if(Objects.equals(input[2], "R")){
                                pane[newx][newy] = new Rook("b",newx,newy);
                            }else if(Objects.equals(input[2], "N")){
                                pane[newx][newy] = new Knight("b",newx,newy);
                            }else if(Objects.equals(input[2], "B")){
                                pane[newx][newy] = new Bishop("b",newx,newy);
                            }else if(Objects.equals(input[2], "Q")){
                                pane[newx][newy] = new Queen("b",newx,newy);
                            }
                        }else{
                            System.out.println("Illegal move, try again");
                            isCon = false;
                            continue;
                        }
                    }else{
                        System.out.println("Illegal move, try again");
                        isCon = false;
                        continue;
                    }
                }else{
                    if(Objects.equals(pane[ox][oy].getType(), "p") && Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 7){
                        pane[newx][newy] = new Queen("w",newx,newy);
                    }else if(Objects.equals(pane[ox][oy].getType(), "p") && Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 0){
                        pane[newx][newy] = new Queen("b",newx,newy);
                    }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 0 && newy == 2 && !check(board,count)){
                        pane[newx][newy] = new King("w",newx,newy);
                        pane[0][3] = new Rook("w", 0,3);
                        pane[0][0] = new Emp(0,0);
                    }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 0 && newy == 6 && !check(board,count)){
                        pane[newx][newy] = new King("w",newx,newy);
                        pane[0][5] = new Rook("w", 0,5);
                        pane[0][7] = new Emp(0,7);
                    }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 7 && newy == 2 && !check(board,count)){
                        pane[newx][newy] = new King("b",newx,newy);
                        pane[7][3] = new Rook("b", 7,3);
                        pane[7][0] = new Emp(7,0);
                    }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 7 && newy == 6 && !check(board,count)){
                        pane[newx][newy] = new King("b",newx,newy);
                        pane[7][5] = new Rook("b", 7,5);
                        pane[7][7] = new Emp(7,7);
                    }else{
                        if(Objects.equals(pane[ox][oy].getType(), "R") || Objects.equals(pane[ox][oy].getType(), "K")){
                            pane[ox][oy].setIsMove();
                        }
                        pane[newx][newy] = pane[ox][oy];
                        pane[newx][newy].setX(newx);
                        pane[newx][newy].setY(newy);
                    }
                }
                pane[ox][oy] = new Emp(ox, oy);
                board.setPane(pane);
            } else {
                System.out.println("Illegal move, try again");
                isCon = false;
                continue;
            }
            count++;
            if(checkmate(board, count)){
                System.out.println("Checkmate");
                if(count % 2 == 0){
                    System.out.println("Black wins");
                    break;
                }else {
                    System.out.println("White wins");
                    break;
                }
            }
            System.out.println();

        }


    }


    /**
     * convert character to number
     * @param c column
     * @return column in checkerboard
     */
    public static int getColumn(char c){
        return switch (c) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> -1;
        };
    }

    /**
     * copy checkerboard
     * @param temp original checkerboard
     * @param plate copy to new checkerboard
     */
    public static void copy(Piece[][] temp, Piece[][] plate){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(Objects.equals(temp[i][j].getType(), "B")){
                    plate[i][j] = new Bishop(temp[i][j].getCamp(),i,j);
                }
                if(Objects.equals(temp[i][j].getType(), "K")){
                    plate[i][j] = new King(temp[i][j].getCamp(),i,j,temp[i][j].getIsMove());
                }
                if(Objects.equals(temp[i][j].getType(), "N")){
                    plate[i][j] = new Knight(temp[i][j].getCamp(),i,j);
                }
                if(Objects.equals(temp[i][j].getType(), "p")){
                    plate[i][j] = new Pawn(temp[i][j].getCamp(),i,j);
                }
                if(Objects.equals(temp[i][j].getType(), "Q")){
                    plate[i][j] = new Queen(temp[i][j].getCamp(),i,j);
                }
                if(Objects.equals(temp[i][j].getType(), "R")){
                    plate[i][j] = new Rook(temp[i][j].getCamp(),i,j,temp[i][j].getIsMove());
                }
                if(temp[i][j].getType() == null){
                    plate[i][j] = new Emp(i,j);
                }
            }
        }
    }

    /**
     * determine whether is checked
     * @param board checkerboard
     * @param count use to count whose term
     * @return true if check, false if not check
     */
    public static boolean check(Board board, int count){
        Piece[][] plate = new Piece[8][8];
        copy(board.getPane(), plate);
        if(count % 2 == 1){
            int bx = 0;
            int by = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(plate[i][j].getType(), "K") && Objects.equals(plate[i][j].getCamp(), "b")){
                        bx = i;
                        by = j;
                    }
                }
            }
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(plate[i][j].getCamp(), "w") && plate[i][j].move(bx,by,plate)){
                        return true;
                    }
                }
            }
        }else{
            int wx = 0;
            int wy = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(plate[i][j].getType(), "K") && Objects.equals(plate[i][j].getCamp(), "w")){
                        wx = i;
                        wy = j;
                    }
                }
            }
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(plate[i][j].getCamp(), "b") && plate[i][j].move(wx,wy,plate)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * determine whether is illegal move due to check itself
     * @param ox original x
     * @param oy original y
     * @param newx new x
     * @param newy new y
     * @param count count whose term
     * @param input input array
     * @param board checkerboard
     * @return true if illegal move, false if not
     */
    public static boolean checkme(int ox, int oy, int newx, int newy, int count, String[] input,Board board){
        Piece[][] pane = new Piece[8][8];
        copy(board.getPane(), pane);
        if (((Objects.equals(pane[ox][oy].getCamp(), "w") && count % 2 == 0) || (Objects.equals(pane[ox][oy].getCamp(), "b") && count % 2 == 1)) && pane[ox][oy].move(newx, newy, pane)) {
            if(input.length == 3){
                if (Objects.equals(input[2], "draw?")) {
                    return false;
                }else if(Objects.equals(pane[ox][oy].getType(), "p")){
                    if(Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 7){
                        if(Objects.equals(input[2], "R")){
                            pane[newx][newy] = new Rook("w",newx,newy);
                        }else if(Objects.equals(input[2], "N")){
                            pane[newx][newy] = new Knight("w",newx,newy);
                        }else if(Objects.equals(input[2], "B")){
                            pane[newx][newy] = new Bishop("w",newx,newy);
                        }else if(Objects.equals(input[2], "Q")){
                            pane[newx][newy] = new Queen("w",newx,newy);
                        }
                    }else if(Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 0){
                        if(Objects.equals(input[2], "R")){
                            pane[newx][newy] = new Rook("b",newx,newy);
                        }else if(Objects.equals(input[2], "N")){
                            pane[newx][newy] = new Knight("b",newx,newy);
                        }else if(Objects.equals(input[2], "B")){
                            pane[newx][newy] = new Bishop("b",newx,newy);
                        }else if(Objects.equals(input[2], "Q")){
                            pane[newx][newy] = new Queen("b",newx,newy);
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                if(Objects.equals(pane[ox][oy].getType(), "p") && Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 7){
                    pane[newx][newy] = new Queen("w",newx,newy);
                }else if(Objects.equals(pane[ox][oy].getType(), "p") && Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 0){
                    pane[newx][newy] = new Queen("b",newx,newy);
                }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 0 && newy == 2 && !check(board,count)){
                    pane[newx][newy] = new King("w",newx,newy);
                    pane[0][3] = new Rook("w", 0,3);
                    pane[0][0] = new Emp(0,0);
                }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 0 && newy == 6 && !check(board,count)){
                    pane[newx][newy] = new King("w",newx,newy);
                    pane[0][5] = new Rook("w", 0,5);
                    pane[0][7] = new Emp(0,7);
                }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 7 && newy == 2 && !check(board,count)){
                    pane[newx][newy] = new King("b",newx,newy);
                    pane[7][3] = new Rook("b", 7,3);
                    pane[7][0] = new Emp(7,0);
                }else if(Objects.equals(pane[ox][oy].getType(), "K") && Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 7 && newy == 6 && !check(board,count)){
                    pane[newx][newy] = new King("b",newx,newy);
                    pane[7][5] = new Rook("b", 7,5);
                    pane[7][7] = new Emp(7,7);
                }else{
                    if(Objects.equals(pane[ox][oy].getType(), "R") || Objects.equals(pane[ox][oy].getType(), "K")){
                        pane[ox][oy].setIsMove();
                    }
                    pane[newx][newy] = pane[ox][oy];
                    pane[newx][newy].setX(newx);
                    pane[newx][newy].setY(newy);
                }
            }
            pane[ox][oy] = new Emp(ox, oy);
        } else {
            return false;
        }
        if(count % 2 == 0){
            int wx = 0;
            int wy = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(pane[i][j].getType(), "K") && Objects.equals(pane[i][j].getCamp(), "w")){
                        wx = i;
                        wy = j;
                        break;
                    }
                }
            }
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(pane[i][j].getCamp(), "b") && pane[i][j].move(wx,wy,pane)){
                        return true;
                    }
                }
            }
        }else{
            int bx = 0;
            int by = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(pane[i][j].getType(), "K") && Objects.equals(pane[i][j].getCamp(), "b")){
                        bx = i;
                        by = j;
                    }
                }
            }
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Objects.equals(pane[i][j].getCamp(), "w") && pane[i][j].move(bx,by,pane)){
                        return true;
                    }
                }
            }
        }
        return false;

    }

    /**
     * check whether is still check after moving
     * @param ox original x
     * @param oy orinal y
     * @param newx new x
     * @param newy new y
     * @param count count whose term
     * @param temp checkerboard
     * @return true if not check after moving, false if check after moving
     */
    public static boolean moving(int ox, int oy, int newx, int newy, int count, Piece[][] temp){
        Piece[][] pane = new Piece[8][8];
        copy(temp, pane);
        if (((Objects.equals(pane[ox][oy].getCamp(), "w") && count % 2 == 0) || (Objects.equals(pane[ox][oy].getCamp(), "b") && count % 2 == 1)) && pane[ox][oy].move(newx, newy, pane)) {
            if(Objects.equals(pane[ox][oy].getType(), "p") && Objects.equals(pane[ox][oy].getCamp(), "w") && newx == 7){
                pane[newx][newy] = new Queen("w",newx,newy);
            }else if(Objects.equals(pane[ox][oy].getType(), "p") && Objects.equals(pane[ox][oy].getCamp(), "b") && newx == 0){
                pane[newx][newy] = new Queen("b",newx,newy);
            }else{
                if(Objects.equals(pane[ox][oy].getType(), "R") || Objects.equals(pane[ox][oy].getType(), "K")){
                    pane[ox][oy].setIsMove();
                }
                pane[newx][newy] = pane[ox][oy];
                pane[newx][newy].setX(newx);
                pane[newx][newy].setY(newy);
            }
            pane[ox][oy] = new Emp(ox, oy);
        }
        Board board = new Board();
        board.setPane(pane);
        return !check(board, count);
    }

    /**
     * check if checkmate
     * Is there any way to stop checkmate, return false
     * @param board checkerboard
     * @param count count whose term
     * @return true if is checkmate, false if not
     */
    public static boolean checkmate(Board board, int count){
        if(!check(board,count)){
            return false;
        }
        Piece[][] pane = new Piece[8][8];
        copy(board.getPane(), pane);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(pane[i][j].getType() != null){
                    for(int a = 0; a < 8; a++){
                        for(int b = 0; b < 8; b++){
                            if(pane[i][j].move(a,b,pane) && moving(i,j,a,b,count,pane)){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}