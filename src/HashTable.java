import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class HashTable {

    //private fields
    protected int size;
    protected double loadfactor;
    protected Power[] table;
    protected LinkedList<Power>[] tableC;
    protected String file;
    protected int numkeys;
    protected int IPC;
    protected int SPC;
    protected int maxSPC;
    protected boolean chaining;

    /**
     * creates a hashtable that doesn't use chaining
     * @param size table size
     * @param file file name: NB!!! NOTE THAT FILE MUST ONLY BE IN THE FORM <FileName>.<FileExtension> AND FILE MUST BE PLACED IN BIN FOLDER
     * @param numkeys number of keys to be searched
     * @throws Exception if the table size is incorrect
     */
    public HashTable(int size, String file, int numkeys) throws Exception {
        this(size, file, numkeys, false);
    }
    /**
     * creates a hashtable
     * @param size table size
     * @param file file name
     * @param numkeys number of keys to be searched
     * @param chaining whether the table uses chaining or not
     * @throws Exception if the table size is incorrect
     */

    public HashTable(int size, String file, int numkeys, Boolean chaining) throws Exception{
        if (isPrime(size)) {
            this.chaining=chaining;
            this.size = size;
            this.file = file;
            this.numkeys = numkeys;
            if (!chaining){
                this.table = new Power[size];
            }
            else{
                this.tableC= new LinkedList[size];
            }
            loadfactor = 0;
            maxSPC=0;
            populate();
        }
        else {
            throw new Exception("Error: Table size must be a prime");
        }
    }

    protected abstract void insert(Power p) throws Exception;

    protected void search(String[] allkeys)
    {
        for (String key: allkeys) {
            search(key);
        }
    }

    protected abstract void search(String key);

    /**
     * returns the initial hash value for probing
     * @param key
     * @return
     */
    public int hash(String key){
        int hash = 0;
        for (int i = 0; i<key.length(); i++) {
            hash = 31*hash + (((int)key.charAt(i)));
            if (hash>=size)
            {
                hash = mod(hash, size);
            }
        }
        return hash;

    }

    /**
     * method to return modulus of a number k by i
     * @param k
     * @return equivalent of k%i
     */
    protected int mod(int k, int i)
    {
        while(k>=i){
            k=k-i;
        }
        return k;
    }

    /**
     * simple method to determine primality of a number
     * @param k
     * @return true if k is a prime number or false if it is not
     */
    public boolean isPrime(int k)
    {
        if (k<2) {
            return false;
        }
        int numfac = 2;
        for (int i = 2; i <= ((int)(Math.ceil(Math.sqrt(k)))); i++) {
            if(mod(k,i)==0){
                numfac++;
                break;
            }
        }
        return(numfac==2);
    }

    /**
     * prints data to a file
      * @param filename
     * @param val
     */
    public void printDataToFile(String filename, String val){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(filename, true));
            pw.println(val);
            pw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * generic method that receives file name and populates the hash table with the data
     *
     */
    public void populate(){
        try {
            Scanner sc = new Scanner(new FileReader(file));
            String temp = sc.nextLine();
            while(sc.hasNext()){
                temp = sc.nextLine();
                insert(new Power(temp));
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     *
     * @return Insertion Probe Count
     */
    public int getIPC() {
        return IPC;
    }

    /**
     *
     * @return Search Probe Count
     */
    public int getSPC() {
        return SPC;
    }
    /**
     *
     * @return Max Search Probe Count
     */
    public int getMaxSPC() {
        return maxSPC;
    }

    /**
     *
     * @return size of table
     */
    public int getSize(){ return size; }

    /**
     *
     * @return load factor of table
     */
    public double getLoadfactor(){ return loadfactor; }
}