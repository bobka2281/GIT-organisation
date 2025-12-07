import java.util.*;
public class BSTTree {

     class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }
    public Node root;

    public void insert(int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }
        Node curr = root;
        while (true) {
            if (val < curr.val) {
                if (curr.left == null) { curr.left = new Node(val); return; }
                curr = curr.left;
            } else if (val > curr.val) {
                if (curr.right == null) { curr.right = new Node(val); return; }
                curr = curr.right;
            } else return;
        }
    }

    public boolean search(int val) {
        Node curr = root;
        while (curr != null) {
            if (val == curr.val) return true;
            curr = val < curr.val ? curr.left : curr.right;
        }
        return false;
    }

    public void delete(int val) {
        Node parent = null;
        Node curr = root;

        while (curr != null && curr.val != val) {
            parent = curr;
            curr = val < curr.val ? curr.left : curr.right;
        }

        if (curr == null) return;

        if (curr.left != null && curr.right != null) {
            Node minParent = curr;
            Node min = curr.right;

            while (min.left != null) {
                minParent = min;
                min = min.left;
            }

            curr.val = min.val;
            parent = minParent;
            curr = min;
        }

        Node child = (curr.left != null) ? curr.left : curr.right;

        if (parent == null) {
            root = child;
        } else if (parent.left == curr) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    public int getMin() {
        if (root == null) throw new NoSuchElementException();
        Node curr = root;
        while (curr.left != null) curr = curr.left;
        return curr.val;
    }

    public int getMax() {
        if (root == null) throw new NoSuchElementException();
        Node curr = root;
        while (curr.right != null) curr = curr.right;
        return curr.val;
    }

    public void preOrder(Node n) {
        if (n == null) return;
        System.out.print(n.val + " ");
        preOrder(n.left);
        preOrder(n.right);
    }

    public void inOrder(Node n) {
        if (n == null) return;
        inOrder(n.left);
        System.out.print(n.val + " ");
        inOrder(n.right);
    }

    public void postOrder(Node n) {
        if (n == null) return;
        postOrder(n.left);
        postOrder(n.right);
        System.out.print(n.val + " ");
    }

    public void bfs() {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.print(n.val + " ");
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
        System.out.println();
    }
    public int getHeight() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    public void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 10000, 25000, 50000, 100000, 200000};

        System.out.println("N (Nodes)\tAvg Height");

        Random rand = new Random();

        for (int n : sizes) {
            BSTTree tree = new BSTTree();

            Set<Integer> uniqueKeys = new HashSet<>();
            while (uniqueKeys.size() < n) {
                uniqueKeys.add(rand.nextInt(n * 10));
            }
            List<Integer> shuffledKeys = new ArrayList<>(uniqueKeys);
            Collections.shuffle(shuffledKeys);

            for (int val : shuffledKeys) {
                tree.insert(val);
            }

            int h = tree.getHeight();
            System.out.println(n + "\t\t" + h);
        }
        BSTTree tree = new BSTTree();

        int[] values = {50, 30, 20, 40, 70, 60, 80};
        System.out.println("Вставка элементов: " + Arrays.toString(values));

        for (int val : values) {
            tree.insert(val);
        }

        System.out.print("Прямой обход: ");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.print("Центрированный обход: ");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.print("Обратный обход: ");
        tree.postOrder(tree.root);
        System.out.println();

        System.out.print("Обход в ширину: ");
        tree.bfs();
    }
}