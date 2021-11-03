import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ChainingHash extends HashTable{


    private int numinsertions;

    /**
     * creates a hashtable
     * @param size
     * @param file
     * @param numkeys
     * @throws Exception if the table size is incorrect
     */
    public ChainingHash(int size, String file, int numkeys) throws Exception{
            super(size, file, numkeys, true);
            numinsertions=0;
        }

    /**
     * insertion method for chaining hashtable
     * @param p
     * @throws Exception if there is already an element containing that date ie duplicate
     */
    public void insert(Power p) throws Exception
    {
        String key  = p.getDate();
        int index = hash(key);
        if (tableC[index]==null) {
            tableC[index] = new LinkedList<Power>();
        }
        if(tableC[index].indexOf(p)>=0){
            throw new Exception("Error: element already present");
        }
        tableC[index].add(p.copy());
        numinsertions++;
        IPC = IPC + (tableC[index].size()-1);
        loadfactor = (double)numinsertions/size;

    }

    /**
     * simple search method that finds the element
     * @param key
     */
    public void search(String key) {
        int index = hash(key);
        boolean notfound = true;
        int numprobes = tableC[index].indexOf(new Power(key, "",""));
        SPC = SPC + numprobes;
        if (maxSPC<numprobes)
        {
            maxSPC=numprobes;
        }


    }



}

