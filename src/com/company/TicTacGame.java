package com.company;

import java.util.Random;
import java.util.Scanner;

public class TicTacGame {
    public char[][] field;
    int size;
    int row_to_win;
    GameMode gameMode;


    TicTacGame(int n, int row_to_win, GameMode gameMode){
        field = new char[n][n];
        size = n;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                field[i][j] = '.';
            }
        }
        this.row_to_win = row_to_win;
        this.gameMode = gameMode;
    }

    void startNewGame(){
        switch (gameMode){
            case TwoPlayers: startTwoPlayerGame();
                            break;
            case OnePlayer: startOnePlayerGame();
                            break;
            case AI: startBotGame();
                            break;
        }
    }

    void startTwoPlayerGame(){
        System.out.println("Первый игрок играет крестиками, второй ноликами");
        while(true){
            System.out.println("Ход первого игрока");
            playerTurn('x');
            printMap();
            if (checkWin('x')){
                System.out.println("Первый игрок выиграл");
                break;
            }
            if (fieldIsFull()){
                System.out.println("Ходов больше не осталось, ничья");
                break;
            }
            System.out.println("Ход второго игрока");
            playerTurn('o');
            printMap();
            if (checkWin('o')){
                System.out.println("Второй игрок выиграл");
                break;
            }
            if (fieldIsFull()){
                System.out.println("Ходов больше не осталось, ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    void startOnePlayerGame(){
        System.out.println("Бот играет ноликами, а игрок крестиками");
        while(true){
            System.out.println("Ход игрока");
            playerTurn('x');
            printMap();
            if (checkWin('x')){
                System.out.println("Игрок выиграл");
                break;
            }
            if (fieldIsFull()){
                System.out.println("Ходов больше не осталось, ничья");
                break;
            }
            System.out.println("Ход бота");
            botTurn('o');
            printMap();
            if (checkWin('o')){
                System.out.println("Бот выиграл");
                break;
            }
            if (fieldIsFull()){
                System.out.println("Ходов больше не осталось, ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    void startBotGame(){
        System.out.println("Первый бот играет крестиками, второй ноликами");
        while(true){
            System.out.println("Ход первого бота");
            botTurn('x');
            printMap();
            if (checkWin('x')){
                System.out.println("Первый бот выиграл");
                break;
            }
            if (fieldIsFull()){
                System.out.println("Ходов больше не осталось, ничья");
                break;
            }
            System.out.println("Ход второго бота");
            botTurn('o');
            printMap();
            if (checkWin('o')){
                System.out.println("Второй бот выиграл");
                break;
            }
            if (fieldIsFull()){
                System.out.println("Ходов больше не осталось, ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }


    void botTurn(char sym){
        int x,y;
        Random rand = new Random();
        do{
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        }while(!isValidCoord(x, y));
        field[x][y] = sym;
    }

    void playerTurn(char sym){
        int x,y;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Введите корректные координаты");
            x = sc.nextInt();
            y = sc.nextInt();
        }while(!isValidCoord(x, y));
        field[x][y] = sym;
    }

    boolean isValidCoord(int x, int y){
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
        if (field[x][y] == '.') return true;
        return false;
    }

    void printMap(){
        for (int i = 0; i < size; i++){
            System.out.print("| ");
            for (int j = 0; j < size; j++){
                System.out.print(field[i][j]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    boolean checkWin(char sym){
        for (int i = 0; i < size - row_to_win + 1; i++){
            for (int j = 0; j < size - row_to_win + 1; j++){
                if (checkDiag(sym, i, j) || checkLines(sym, i, j)) return true;
            }
        }
        return false;
    }

    boolean checkDiag(char sym, int x, int y){
        boolean rightDiag, leftDiag;
        rightDiag = true;
        leftDiag = true;
        for (int i = 0; i < row_to_win; i++){
            rightDiag &= (field[x + i][y + i] == sym);
            leftDiag &= (field[row_to_win - i - 1 + x][y + i] == sym);
        }
        return (rightDiag || leftDiag);
    }

    boolean checkLines(char sym, int x, int y){
        boolean row, col;
        for (int i = x; i < row_to_win + x; i++){
            row = true;
            col = true;
            for (int j = y; j < row_to_win + y; j++){
                row &= (field[i][j] == sym);
                col &= (field[j][i] == sym);
            }
            if (row || col) return true;
        }
        return false;
    }


    boolean fieldIsFull(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (field[i][j] == '.'){
                    return false;
                }
            }
        }
        return true;
    }
}
