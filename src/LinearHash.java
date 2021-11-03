public class LinearHash extends HashTable {

    private int numinsertions;


    /**
     * creates a table for linear probing
     * @param size
     * @param file
     * @param numkeys
     * @throws Exception
     */
    public LinearHash(int size, String file, int numkeys) throws Exception{
        super(size, file, numkeys);
        numinsertions=0;
    }

    /**
     * insertion method for linear probing methodology
     * @param p
     * @throws Exception
     */
    public void insert(Power p)throws Exception{
        if (numinsertions>=size)
        {
            throw new Exception("Insertion Failure: table is full");
        }
        String key = p.getDate();
        int index = hash(key);
        while(table[index]!=null)
        {
            IPC++;
            index = mod(index+1, size);
        }
        table[index]= p.copy();
        numinsertions++;
        loadfactor = (double)numinsertions/size;
    }

    /**
     * search method for linear probing methodology
     * @param s
     */
    public void search(String s){
        int index = hash(s);
        int i =0;
        boolean notfound = true;
        while(notfound)
        {
            if(table[index].getDate().equalsIgnoreCase(s)){
                notfound = false;
            }
            else{
                SPC++;
                index = mod(index+1, size);
                i++;
            }
        }
        if (maxSPC<i){
            maxSPC=i;
        }
    }

}
