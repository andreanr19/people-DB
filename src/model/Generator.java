package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    public final static String PATHTOREADNAMES = "data/babynames-clean.csv",
            PATHTOREADLASTNAMES = "data/Names_2010Census.csv", PATHTOREADCOUNTRIES = "data/country_p.csv";
    public final static String PATHTOWRITE = "data/temporal.csv";

    public final static int MAX_CAPACITY = 1022277970;

    private final static double AGE_RANGE_0_TO_14 = 0.1862;
    private final static double AGE_RANGE_15_TO_24 = 0.1312 + AGE_RANGE_0_TO_14;
    private final static double AGE_RANGE_25_TO_54 = 0.3729 + AGE_RANGE_15_TO_24;
    private final static double AGE_RANGE_55_TO_64 = 0.1294 + AGE_RANGE_25_TO_54;

    public int count, q, countID;

    private List<String> names, lastNames;

    public Generator() {

        names = new ArrayList<>();
        lastNames = new ArrayList<>();
        countID = 0000000000;
    }

    public void generateData(int q) throws IOException {

        this.q = q;

        loadDataToGenerate(6000);

        PrintWriter pw = new PrintWriter(new FileWriter(PATHTOWRITE));

        pw.write("id,name,lastname,gender,age\n");

        for (int i = 0; i < q && count < MAX_CAPACITY; i++) {

            pw.write(getRandomPerson());

            count++;

        }

        pw.close();
    }

    public String getRandomPerson() throws IOException {
        int iName = (int) (Math.random() * (double) names.size()),
                iLName = (int) (Math.random() * (double) lastNames.size());
        String tID = countID + "";
        for (int i = 0; i < 10 && tID.length() < 10; i++) {

            tID = "0" + tID;

        }

        if(names.size()<1){
            loadDataToGenerate(6780);
        }
        String nameAndGender = names.remove(iName).toUpperCase();
        if(lastNames.size()<1){
            loadDataToGenerate(6780);
        }
        String lastName = lastNames.remove(iLName);
        String gender = (nameAndGender.split(";")[1].equals("BOY")) ? "m" : "f";

        int age;
        
        Random rN = new Random();

        if ((double) count / (double) q < AGE_RANGE_0_TO_14) {

            age = rN.nextInt(14 + 1);
        } else if ((double) count / (double) q < AGE_RANGE_15_TO_24) {
            age = rN.nextInt(24 - 15 + 1) + 15;

        } else if ((double) count / (double) q < AGE_RANGE_25_TO_54) {
            age = rN.nextInt(54 - 25 + 1) + 25;

        } else if ((double) count / (double) q < AGE_RANGE_55_TO_64) {
            age = rN.nextInt(64 - 55 + 1) + 55;
        } else {
            age = rN.nextInt(89 - 65 + 1) + 65;

        }
        countID++;
        return tID + "," + nameAndGender.split(";")[0] + "," + lastName + "," + gender + "," + age + "," + "0" + "\n";
    }

    public void loadDataToGenerate(int x) throws IOException {

        BufferedReader brNames = new BufferedReader(new FileReader(new File(PATHTOREADNAMES)));
        BufferedReader brLNames = new BufferedReader(new FileReader(new File(PATHTOREADLASTNAMES)));
        brLNames.readLine();

        for (int i = 0; i < x; i++) {
            names.add(brNames.readLine());
            lastNames.add(brLNames.readLine().split(",")[0]);
        }

        brNames.close();
        brLNames.close();
    }

    public void cleanTemporalFiles() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(PATHTOWRITE));
        pw.close();
    }

    public void setQ(int q) {
        this.q = q;
    }

    public void setCount(int count) {
        this.count = count;
    }

}