import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainTesting {

    private static String arr[];

    /**
     * main method to perform testing or to run a specific file
     * @param args
     */
    public static void main(String[] args) {
        int prime = 0;
        char scheme = ' ';
        String file = "";
        int numkeys = 0;
        HashTable h = null;
        if (args.length==4) {
            prime = Integer.parseInt(args[0]);
            scheme = args[1].charAt(0);
            file = args[2];
            numkeys = Integer.parseInt(args[3]);
            String[] searchkeys = getKeys(file,numkeys);
            switch (scheme) {
                case 'l': {
                    h = testLinearInsert(prime, file, numkeys);
                    h.printDataToFile("Hash"+h.getSize()+".txt","Linear\nIPC: "+h.getIPC()+"\nLoad Factor: "+h.getLoadfactor());
                    break;
                }
                case 'q': {
                    h = testQuadraticInsert(prime, file, numkeys);
                    h.printDataToFile("Hash"+h.getSize()+".txt","Quadratic\nIPC: "+h.getIPC()+"\nLoad Factor: "+h.getLoadfactor());
                    break;
                }
                case 'c': {
                    h = testChainingInsert(prime, file, numkeys);
                    h.printDataToFile("Hash"+h.getSize()+".txt","Chaining\nIPC: "+h.getIPC()+"\nLoad Factor: "+h.getLoadfactor());
                    break;
                }
            }
            h.search(searchkeys);
            h.printDataToFile("Hash"+h.getSize()+".txt","SPC: "+h.getSPC()+"\nAvg SPC: "+((double)h.getSPC()/numkeys)+"\nMax SPC: "+h.getMaxSPC());
        }
        else if (args.length==5){
            file = "cleaned_data.csv";
            numkeys = 400;
            String[] searchkeys = getKeys(file,numkeys);
            for (int i = 0; i < 5; i++) {

                prime = Integer.parseInt(args[i]);
                //testing linear
                h = testLinearInsert(prime, file, numkeys);
                h.printDataToFile("Hash" + h.getSize() + ".txt", "Linear\nIPC: " + h.getIPC() + "\nLoad Factor: " + h.getLoadfactor());
                h.search(searchkeys);
                h.printDataToFile("Hash" + h.getSize() + ".txt", "SPC: " + h.getSPC() + "\nAvg SPC: " + ((double) h.getSPC() / numkeys) + "\nMax SPC: " + h.getMaxSPC());
                //testing quadratic
                h = testQuadraticInsert(prime, file, numkeys);
                h.printDataToFile("Hash" + h.getSize() + ".txt", "Quadratic\nIPC: " + h.getIPC() + "\nLoad Factor: " + h.getLoadfactor());
                h.search(searchkeys);
                h.printDataToFile("Hash" + h.getSize() + ".txt", "SPC: " + h.getSPC() + "\nAvg SPC: " + ((double) h.getSPC() / numkeys) + "\nMax SPC: " + h.getMaxSPC());
                //testing chaining
                h = testChainingInsert(prime, file, numkeys);
                h.printDataToFile("Hash" + h.getSize() + ".txt", "Chaining\nIPC: " + h.getIPC() + "\nLoad Factor: " + h.getLoadfactor());
                h.search(searchkeys);
                h.printDataToFile("Hash" + h.getSize() + ".txt", "SPC: " + h.getSPC() + "\nAvg SPC: " + ((double) h.getSPC() / numkeys) + "\nMax SPC: " + h.getMaxSPC());
            }

        }
    }

    /**
     * constructs a linearhash object
     * @param size
     * @param file
     * @param numkeys
     * @return
     */
    public static LinearHash testLinearInsert(int size, String file, int numkeys){
        try{
            return new LinearHash(size, file, numkeys);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * constructs a quadratic hash object
     * @param size
     * @param file
     * @param numkeys
     * @return
     */
    public static QuadraticHash testQuadraticInsert(int size, String file, int numkeys){
        try{
            return new QuadraticHash(size, file, numkeys);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * constructs a chaining hash object
     * @param size
     * @param file
     * @param numkeys
     * @return
     */
    public static ChainingHash testChainingInsert(int size, String file, int numkeys){
        try{
            return new ChainingHash(size, file, numkeys);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * extracts a set of search keys from a given file
     * @param file
     * @param numkeys
     * @return
     */
    public static String[] getKeys(String file, int numkeys){
        try{
            Scanner sc = new Scanner(new FileReader(file));
            String temp = sc.nextLine();
            arr = new String[500];
            int i =0;
            while(sc.hasNext()){
                temp =sc.nextLine();
                arr[i]= temp.substring(0,temp.indexOf(","));
                i++;
            }
            List<String> list =Arrays.asList(arr);
            Collections.shuffle(list);
            arr = new String[numkeys];
            for (int k =0; k<numkeys; k++){
                arr[k]= list.get(k);
            }

            return arr;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
