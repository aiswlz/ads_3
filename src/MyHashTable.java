public class MyHashTable {
    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "HashNode{" + "key=" + key + ", value=" + value + ", next=" + next + '}';
        }

        private HashNode<K, V>[] chainArray;
        private int M = 11;
        private int size;

        public MyHashTable(){}

        public MyHashTable(int M){}

        private int hash(K key){}

        public void put(K key, V value){}

        public V get(K key){}

        private V remove(K key){}

        public boolean contains(V value){}

        public K getKey(V value){}

    }
}