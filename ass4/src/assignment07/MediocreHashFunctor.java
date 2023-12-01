package assignment07;

public class MediocreHashFunctor implements HashFunctor{
    //djb2
    /**
     * @param item
     * @return
     */
    @Override
    public int hash(String item) {
        int hash = 5381;
        for (int i = 0; i < item.length(); i++) {
            char c = item.charAt(i);
            hash = ((hash << 5) + hash) + (int) c; /* hash * 33 + c */
        }
        return hash;
    }
}
