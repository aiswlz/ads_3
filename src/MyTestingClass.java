public class MyTestingClass {
    private String key1;
    private String key2;

    public MyTestingClass(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        // Combine hash codes of both key1 and key2 for better distribution
        for (int i = 0; i < key1.length(); i++) {
            hash = 31 * hash + key1.charAt(i);
        }
        for (int i = 0; i < key2.length(); i++) {
            hash = 31 * hash + key2.charAt(i);
        }
        return hash ;
    }

    /**
     * Compares this object with another object for equality.
     * Two MyTestingClass objects are considered equal if both their key1 and key2 strings are equal.
     * @param o The object to compare with
     * @return True if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyTestingClass that = (MyTestingClass) o;
        return key1.equals(that.key1) && key2.equals(that.key2);
    }

    @Override
    public String toString() {
        return "MyTestingClass{" +
                "key1='" + key1 + '\'' +
                ", key2='" + key2 + '\'' +
                '}';
    }
}