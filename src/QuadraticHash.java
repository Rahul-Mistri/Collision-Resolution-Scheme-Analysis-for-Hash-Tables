public class QuadraticHash extends HashTable {

    private int numinsertions;

    /**
     * creates a table for quadratic probing
     * @param size
     * @param file
     * @param numkeys
     * @throws Exception
     */
    public QuadraticHash(int size, String file, int numkeys) throws Exception{
        super(size, file, numkeys);
        numinsertions=0;
    }

    /**
     * insertion method for quadratic probing methodology
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
        int i = 0;
        while((table[index]!=null) && (i<=size))
        {
            IPC++;
            i++;
            index = mod(update(index,i),size);
        }
        if (i<=size) {
            table[index] = p.copy();
            i=0;
            numinsertions++;
            loadfactor = (double)numinsertions/size;
        }
        else{
            throw new Exception("Insertion Failure: cannot locate empty slot");
        }
    }

    private int update(int val, int i)
    {
        val= val+ i<<1;
        return val-1;
    }

    /**
     * search method for quadratic probing methodology
     * @param s
     */
    public void search(String s){
        int index = hash(s);
        boolean notfound = true;
        int i = 0;
        while((notfound) && (i<=size))
        {
            if(table[index].getDate().equalsIgnoreCase(s)){
                notfound = false;
            }
            else{
                SPC++;
                i++;
                index = mod(update(index,i),size);
            }
        }
        if (maxSPC<i){
            maxSPC=i;
        }
    }




}
