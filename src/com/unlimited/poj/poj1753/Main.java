package com.unlimited.poj.poj1753;

import java.util.*;

/**
 * Created by unlimited on 14-4-3.
 *
 * POJ 1753 Flip Game
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = 0;
        List<String> strings = new ArrayList<String>();
        while (i++ < 4) {
            String text = in.nextLine();
            strings.add(text);
        }
        Graphics g = new Graphics(strings);

        long start = System.currentTimeMillis();
        GraphicsNode node = BFS(g, new Visitor());
        long end = System.currentTimeMillis();
        System.out.println(end - start);

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
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(g.root.id);

        while (!queue.isEmpty()) {
            GraphicsNode node = g.getNode(queue.poll());
            if (node.accept(v)) {
                return node;
            }

            List<Integer> nodes = node.getChildren();
            for (int _id : nodes) {
                GraphicsNode n = g.getNode(_id);
                if (!n.visited) {
                    queue.offer(_id);
                }
            }
        }

        return null;
    }
}

class Visitor {
    public boolean visit(GraphicsNode g) {
        return (g.id == 0xFFFF || g.id == 0);
    }
}

class Graphics {
    public Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
    public Map<Integer, Integer> newcome = new HashMap<Integer, Integer>();
    public GraphicsNode root;

    public GraphicsNode getNode(int id) {
        root.id = id;
        if (visited.get(id) != null) {
            root.length = visited.get(id);
            root.visited = true;
        } else {
            root.length = newcome.get(id);
            root.visited = false;
        }

        return root;
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

        root = new GraphicsNode(id, this);
        root.length = 0;

        newcome.put(id, 0);
    }
}

class GraphicsNode {
    public int id;
    public int length;
    public Graphics owner;
    public boolean visited;
    private List<Integer> children;
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

    public boolean accept(Visitor v) {
        if (owner.newcome.get(id) != null) {
            owner.visited.put(id, owner.newcome.get(id));
            owner.newcome.remove(id);
            return v.visit(this);
        }

        return false;
    }

    public List<Integer> getChildren() {
        if (children == null) {
            children = new ArrayList<Integer>();
        }

        children.clear();
        int _id;
        for (int i : TRANSFER) {
            _id = id ^ i;
            if (owner.visited.get(_id) == null || owner.newcome.get(_id) == null) {
                owner.newcome.put(_id, length + 1);
            }
            children.add(_id);
        }

        return children;
    }
}
