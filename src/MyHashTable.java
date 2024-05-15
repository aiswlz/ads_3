public class MyHashTable<K,V> {
    private class HashNode<K,V> {
        private K key;
        private V value;
        private HashNode<K,V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
    // The hash table itself, represented as an array of HashNode linked lists
    private HashNode<K,V>[] chainArray;
    private static int M = 11;// The initial size of the hash table
    private int size;
    public MyHashTable() {
        this(M);
    }
    public MyHashTable(int M) {
        this.M=M;
        this.chainArray = new HashNode[M];  // Create array of HashNode to represent the buckets
        this.size = 0;
    }

    /**
     * Hashes a key using the key's built-in hashCode() method and takes the modulo of the initial size (M)
     * @param key The key to be hashed
     * @return The hash index within the chainArray
     */
    public int hash(K key) {
        return key.hashCode() % M;
    }

    /**
     * Adds a key-value pair to the hash table.
     * @param key The key to be added
     * @param value The value to be associated with the key
     * @throws IllegalArgumentException if either key or value is null
     */
    public void put(K key, V value)  {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Null key or value not allowed.");
        }

        int index = hash(key); // Calculate the hash index for the key
        HashNode<K, V> newNode = new HashNode<>(key, value); // Create a new node

        if (chainArray[index] == null) {
            chainArray[index] = newNode; // If the bucket is empty, set the head to the new node
        } else {
            HashNode<K, V> current = chainArray[index]; // Start at the head of the linked list in the bucket
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update the value if the key already exists
                    return;
                }
                if (current.next == null) {
                    break; // Reached the end of the linked list
                }
                current = current.next; // Move to the next node in the linked list
            }
            current.next = newNode; // Insert the new node at the end of the linked list
        }
        size++; // Increment size since a new element was added
    }

    public V get(K key) {
        int index = hash(key);  // get index
        HashNode<K,V> current = chainArray[index];  // Get the head of the linked list at the calculated index

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;  // Return the value if the key is found
            }
            current = current.next;  // Move to the next node in the linked list
        }

        return null;  // Return null if the key is not found in the hash table
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];// initialize node at this index
        HashNode<K,V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // Check if the node to be removed is the head of the linked list
                if (prev == null) {
                    // Update the head of the linked list
                    chainArray[index] = current.next;
                } else {
                    // Skip the current node by updating the previous node's next pointer
                    prev.next = current.next;
                }
                size--;  // Decrease size since a node is removed
                return current.value;  // Return the value of the removed node
            }
            prev = current;
            current = current.next;
        }

        return null;  // Key not found, return null
    }


    // Checks for if value contains in the chainArray if yes returns true and vice versa
    public boolean contains(V value) {

        for(int i = 0; i < M; i++){ // iterate through chainArray
            HashNode<K, V> current = chainArray[i]; // for each index initialize current
            while(current != null){ // iterate
                if(current.value.equals(value)){ // If find such value return true
                    return true;
                }
                current = current.next; // move next
            }
        }

        return false; // no value case
    }

    public K getKey(V value) {

        for(int i = 0; i < M; i++){ // iterate through chainArray
            HashNode<K, V> current = chainArray[i]; // for each index initialize current
            while(current != null){
                if(current.value.equals(value)){ // If find such value
                    return current.key; // return key
                }
                current = current.next; // move next
            }
        }

        return null;  // Return null if the value is not found in the hash table
    }

}