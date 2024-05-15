
public class BST<K extends Comparable<K>, V>{
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    public int size() {
        return size;
    }
    public BST() {
        root = null;
    }
    // Inserts a key-value pair into the BST.
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * Recursive helper method for insertion.
     *
     * @param node The current node in the recursive descent
     * @param key  The key of the pair to insert
     * @param value The value associated with the key
     * @return The updated root node after insertion
     */
    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        // Compare the key with the key of the current node
        int cmp = key.compareTo(node.key);

        // Recursive cases based on the comparison result
        if (cmp < 0) {
            node.left = put(node.left, key, value); // Insert into the left subtree
        } else if (cmp > 0) {
            node.right = put(node.right, key, value); // Insert into the right subtree
        } else {
            node.value = value; // Update the value if the key already exists
        }

        return node; // Return the modified node
    }

    public V get(K key) {
        // Call the private recursive get method starting from the root of the tree
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }

        // Compare the key with the key of the current node
        int cmp = key.compareTo(node.key);

        // Recursive cases based on the comparison result
        if (cmp < 0) {
            // Key is smaller, search in the left subtree
            return get(node.left, key);
        } else if (cmp > 0) {
            // Key is larger, search in the right subtree
            return get(node.right, key);
        } else {
            // Key matches, return the value associated with the key in the current node
            return node.value;
        }
    }

    public void delete(K key) {
        // Call the private recursive delete method starting from the root of the tree
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null; // Key not found, return null
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key); // Delete from the left subtree
        } else if (cmp > 0) {
            node.right = delete(node.right, key); // Delete from the right subtree
        } else {
            // Node to be deleted found

            // Case 1: Node has only one child or no child
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            // Case 2: Node has two children
            Node temp = node; // Store reference to the node to be deleted
            node = min(temp.right); // Find the minimum node in the right subtree
            node.right = deleteMin(temp.right); // Delete the minimum node from the right subtree
            node.left = temp.left;
        }
        size--; // Decrease size since a node is deleted
        return node; // Return the modified node or unchanged node if no modifications
    }

    // Helper method to find the minimum node in a subtree
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    // Helper method to delete the minimum node in a subtree
    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.getKey() + ",  "+ node.getValue());
            inOrder(node.right);
        }
    }

}