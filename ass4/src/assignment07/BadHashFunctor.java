package assignment07;

public class BadHashFunctor implements HashFunctor{
    /**
     * @param item
     * @return
     */
    @Override
    public int hash(String item) {
        int hash = 0;
        for (int i = 0; i < item.length(); i++) {
            char c = item.charAt(i);
            hash += c;
        }
        return hash;

    }
}
