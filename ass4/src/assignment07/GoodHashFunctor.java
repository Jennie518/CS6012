package assignment07;

public class GoodHashFunctor implements HashFunctor{
    /**
     * @param item
     * @return
     */
    @Override
    public int hash(String item) {
        int hash = 7;
        for (int i = 0; i < item.length(); i++) {
            hash = hash * 31 + item.charAt(i); //get char in string by index
        }
        return hash;
    }
}
