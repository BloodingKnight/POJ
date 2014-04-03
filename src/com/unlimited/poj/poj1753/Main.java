package com.unlimited.poj.poj1753;

import java.util.List;

/**
 * Created by unlimited on 14-4-3.
 *
 * POJ 1753 Flip Game
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

class Graph {
    private static int TRANSFER[] = {
        0xC800, 0xE400, 0x7200, 0x3100,
        0x8C80, 0x4E40, 0x2720, 0x1310,
        0x08C8, 0x04E4, 0x0272, 0x0131,
        0x008C, 0x004E, 0x0027, 0x0013
    };

    private int id;
    private List<Graph> children;


}
