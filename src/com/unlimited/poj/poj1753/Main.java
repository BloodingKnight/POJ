package com.unlimited.poj.poj1753;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by unlimited on 14-4-3.
 *
 * POJ 1753 Flip Game
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        int count = BFS(null, null);
        if (count >= 0) {
            System.out.println(count);
        } else {
            System.out.println("Impossible");
        }
    }

    public static int BFS(Graph g, Visitor v) {
        Queue<Graph> queue = new LinkedList<Graph>();
        int count = 0;


        return count;
    }
}

class Visitor {
    public boolean visit(Graph g) {
        return false;
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
    private int visited;
    public List<Graph> children;

    public Graph() {

    }

    public Graph flip(int transfer) {
        return null;
    }
}
