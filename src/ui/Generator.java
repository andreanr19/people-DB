package ui;

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

    private final static String PATHTOWRITE = "data/temporal.csv", PATHTOREADNAMES = "data/babynames-clean.csv",
            PATHTOREADLASTNAMES = "data/Names_2010Census.csv";

    private final static double AGE_RANGE_0_TO_14 = 0.1862;
    private final static double AGE_RANGE_15_TO_24 = 0.1312 + AGE_RANGE_0_TO_14;
    private final static double AGE_RANGE_25_TO_54 = 0.3729 + AGE_RANGE_15_TO_24;
    private final static double AGE_RANGE_55_TO_64 = 0.1294 + AGE_RANGE_25_TO_54;
    // 2246245

    public int count, q;

    private List<String> names, lastNames;

    public Generator() {
        names = new ArrayList<>();
        lastNames = new ArrayList<>();
    }

    public void generateData(int q) throws IOException {
        // generar datos en txt (Ramdomizado basodo en los datasets)
        this.q = q;

        loadDataToGenerate();

        PrintWriter pw = new PrintWriter(new FileWriter(PATHTOWRITE));

        pw.write("name,lastname,gender,age");

        for (int i = 0; i < q; i++) {

            pw.write(getRandomPerson());

            count++;

        }

        pw.close();
    }

    private String getRandomPerson() {
        int iName = (int) (Math.random() * (double) names.size()),
                iLName = (int) (Math.random() * (double) names.size());
        String nameAndGender = names.remove(iName).toUpperCase();
        String lastName = lastNames.remove(iLName);
        String gender = (nameAndGender.split(",")[1].equals("BOY")) ? "MALE" : "FEMALE";

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

        return nameAndGender.split(",")[0] + "," + lastName + "," + gender + "," + age + "\n";
    }

    private void loadDataToGenerate() throws IOException {

        BufferedReader brNames = new BufferedReader(new FileReader(new File(PATHTOREADNAMES)));
        BufferedReader brLNames = new BufferedReader(new FileReader(new File(PATHTOREADLASTNAMES)));
        brLNames.readLine();

        for (int i = 0; i < q; i++) {
            names.add(brNames.readLine());
            lastNames.add(brLNames.readLine().split(",")[0]);
        }

    }

    public void sendToDB() {
        // enviar File object a DBDriver para que este lea el archivo
    }

}