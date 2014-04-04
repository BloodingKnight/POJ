package com.unlimited.poj.poj1753;

import java.util.*;

/**
 * Created by unlimited on 14-4-3.
 *
 * POJ 1753 Flip Game
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Scanner in = new Scanner(System.in);
        int i = 0;
        List<String> strings = new ArrayList<String>();
        while (i++ < 4) {
            String text = in.nextLine();
            strings.add(text);
        }
        Graphics g = new Graphics(strings);

        GraphicsNode node = BFS(g, new Visitor());
        if (node != null) {
            System.out.println(node.length);
        } else {
            System.out.println("Impossible");
        }
    }

    /**
     * Broaden-First-Search
     * @param g the root node of the graph
     * @param v a visitor
     * @return the node we found, null if not found
     */
    public static GraphicsNode BFS(Graphics g, Visitor v) {
        Queue<GraphicsNode> queue = new LinkedList<GraphicsNode>();
        queue.offer(g.root);

        while (!queue.isEmpty()) {
            GraphicsNode node = queue.poll();
            if (v.visit(node)) {
                return node;
            }

            List<GraphicsNode> nodes = node.getChildren();
            for (GraphicsNode n : nodes) {
                if (!n.visited) {
                    queue.offer(n);
                }
            }
        }

        return null;
    }
}

class Visitor {
    public boolean visit(GraphicsNode g) {
        if (g.visited) {
            return (g.id == 0xFFFF || g.id == 0);
        } else {
            g.visited = true;
            return false;
        }
    }
}

class Graphics {
    public Map<Integer, GraphicsNode> exists = new HashMap<Integer, GraphicsNode>();
    public GraphicsNode root;

    public GraphicsNode getNode(int id) {
        GraphicsNode node = exists.get(id);
        if (node == null) {
            node = new GraphicsNode(id, this);
            exists.put(id, node);
        }

        return node;
    }

    public Graphics(List<String> strings) {
        int tmp, id = 0;
        int j = 0;
        for (String str : strings) {
            while (j < 4) {
                tmp = str.charAt(j) == 'b' ? 1 : 0;
                id = (id << 1) + tmp;
                ++j;
            }
            j = 0;
        }

        root = getNode(id);
        root.length = 0;
    }
}

class GraphicsNode {
    public int id;
    public int length;
    public Graphics owner;
    public boolean visited;
    private List<GraphicsNode> children;
    private static int TRANSFER[] = {
            0xC800, 0xE400, 0x7200, 0x3100,
            0x8C80, 0x4E40, 0x2720, 0x1310,
            0x08C8, 0x04E4, 0x0272, 0x0131,
            0x008C, 0x004E, 0x0027, 0x0013
    };

    public GraphicsNode(int id, Graphics owner) {
        this.id = id;
        this.visited = false;
        this.owner = owner;
    }

    public GraphicsNode flip(int transfer, int id) {
        return owner.getNode(id ^ transfer);
    }

    public List<GraphicsNode> getChildren() {
        if (children == null) {
            children = new ArrayList<GraphicsNode>();
            for (int i : TRANSFER) {
                GraphicsNode node = this.flip(i, id);
                node.length = this.length + 1;
                children.add(node);
            }
        }

        return children;
    }
}
