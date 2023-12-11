import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static ArrayList<String> products = new ArrayList<String>();
    public static Scanner userIn = new Scanner(System.in);

    public static void main(String[] args) {
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "/ProductTestData.txt");
        String id = "";
        String name = "";
        String description = "";
        double cost = 0;

        do {
            id = SafeInput.getNonZeroLenString(userIn, "What is this product's id number");
            name = SafeInput.getNonZeroLenString(userIn, "What is this product's name");
            description = SafeInput.getNonZeroLenString(userIn, "What is this product's description");
            cost = SafeInput.getDouble(userIn, "What is the cost of this product");

            products.add(id + ", " + name + ", " + description + ", " + cost);
        } while (SafeInput.getYNConfirm(userIn, "Would you like to add another product"));

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : products) {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}