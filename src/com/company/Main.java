package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

enum GameMode{
    TwoPlayers, OnePlayer, AI
}

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Введите размер поля");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        System.out.println("Введите количество элементов для победы");
        int k = Integer.parseInt(reader.readLine());
        if (k > n){
            throw new IOException();
        }
        System.out.println("Введите режим игры из 3 возможных: 2 игрока, 1 игрок или боты");
        GameMode mode;
        String game = reader.readLine();
        switch (game){
            case "2 игрока": mode = GameMode.TwoPlayers;
                            break;
            case "1 игрок": mode = GameMode.OnePlayer;
                            break;
            case "боты": mode = GameMode.AI;
                            break;
            default: throw new IOException();
        }
        new TicTacGame(n, k, mode).startNewGame();
    }
}
