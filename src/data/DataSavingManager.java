package data;

import data.models.Game;
import data.models.Genre;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class DataSavingManager {
    private final String path;

    public DataSavingManager(String path) {
        this.path = path;
    }

    public void saveTheGame() {
        Scanner scan = new Scanner(System.in);

        int id = getId();
        System.out.println("Your id is: " + id);

        System.out.println("Input the game name:");
        String name = scan.nextLine();

        while (name.length() < 3 || name.length() > 30) {
            System.out.println("Error! name length must be between 3 and 30 symbols. try again:");
            System.out.println("Input the game name:");
            name = scan.nextLine();
        }

        int date = 0;

        while (String.valueOf(date).length() != 4) {
            System.out.println("Input the game founding year:");
            if (scan.hasNextInt()) {
                date = scan.nextInt();

                if (String.valueOf(date).length() != 4) {
                    System.out.println("Error! date length must be 4. try again:");
                }
            }
            else
            {
                System.out.println("Date must include only digits!");
                scan.next();
            }
        }

        scan.nextLine();
        System.out.println("Input the game company:");
        String company = scan.nextLine();

        while (company.length() < 3 || company.length() > 30) {
            System.out.println("Error! Company length must be between 3 and 30 symbols. Try again:");
            System.out.println("Input the game company:");
            company = scan.nextLine();
        }

        System.out.println("Choose the game genre:\n1 - Survival\n2 - Rpg\n3 - Adventurous:");

        int genreChoice = scan.nextInt();

        while (genreChoice < 1 || genreChoice > Genre.values().length) {
            System.out.println("Error! Genre must be between 1 and " + Genre.values().length + " value. Try again:");
            System.out.println("Choose the game genre:\n1 - Survival\n2 - Rpg\n3 - Adventurous:");
            genreChoice = scan.nextInt();
        }

        Genre genre = Genre.values()[genreChoice - 1];


        try {

            FileWriter writer = new FileWriter(path, true);

            String gameInfo = id + " | " + name + " | " + date + " | " + company + " | " + genre + " |\n";
            writer.append(gameInfo);
            writer.close();
            System.out.println("Game has been saved successfully.");
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file! Game saving process failed.");
        }
    }

    public boolean saveTheGame(Game game) {
        try {
            FileWriter writer = new FileWriter(path, true);
            int id = getId();
            String gameInfo = id + " | " + game.name + " | " + game.date + " | " + game.company + " | " + game.genre + "|\n";
            writer.append(gameInfo);
            writer.close();
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
        return true;
    }
    public void deleteGameById(int id) {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            StringBuilder copy = new StringBuilder();
            while (read.hasNextLine()) {
                copy.append(read.nextLine()).append("\n");
            }

            read.close();
            String [] lines = copy.toString().split("\\n");

            boolean haveGameWithSuchId = true;
            for (String myLine : lines) {
                haveGameWithSuchId = myLine.trim().startsWith(String.valueOf(id));

                if (haveGameWithSuchId) {
                    FileWriter writer = new FileWriter(path, false);

                    for (String line : lines) {
                        if (line.trim().startsWith(String.valueOf(id))) {
                            System.out.println("Game with id: " + id + " have been deleted successfully!:");
                            //@TODO System.out.println(line);
                        }
                        else {
                            writer.write(line + "\n");
                        }
                    }
                    writer.close();
                    break;
                }
            }
            if (!haveGameWithSuchId) {
                System.out.println("We didn't find the game with such an id.");
            }
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void deleteGameByName(String name) {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            StringBuilder copy = new StringBuilder();
            while (read.hasNextLine()) {
                copy.append(read.nextLine()).append("\n");
            }

            read.close();
            String [] lines = copy.toString().split("\\n");

            boolean haveGameWithSuchName = true;
            for (String myLine : lines) {

                haveGameWithSuchName = myLine.split("\\|")[1].trim().equals(name.trim());
                //haveGameWithSuchName = myLine.trim().contains(name);

                if (haveGameWithSuchName) {
                    FileWriter writer = new FileWriter(path, false);

                    for (String line : lines) {

                        if (line.split("\\|")[1].trim().equals(name.trim())) {
                            String[] gameData = line.split("\\|");
                            System.out.println("Id = " + gameData[0] + ", Name = " + gameData[1] + ", Date = " + gameData[2] + ", Company = " + gameData[3] + ", Genre = " + gameData[4]);
                            System.out.println("Game with name: " + name + " have been deleted successfully!:");
                        }
                        else {
                            writer.write(line + "\n");
                        }
                    }
                    writer.close();
                    break;
                }
            }
            if (!haveGameWithSuchName) {
                System.out.println("We didn't find the game with such an name.");
            }
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public boolean clear() {
        try {
            FileWriter writer = new FileWriter(path, false);
            writer.write("");
            writer.close();
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
            //myFile.createNewFile();
        }
        return true;
    }

    private int getId() {
        int id = 0;

        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            while (read.hasNextLine()) {
                int tempId = Integer.parseInt(read.next());
                if (tempId > id) {
                    id = tempId;
                }
//                System.out.println("id = " + id);
                read.nextLine();
            }}
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
        return id+1;
    }

    public void sortById() {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            int amountOfLines = 0;
            while (read.hasNextLine()) {
                read.nextLine();
                amountOfLines++;
            }

            read.close();
            read = new Scanner(myFile);

            System.out.println("file length = " + amountOfLines);

            String[] myFileText = new String[amountOfLines];


            for (int i = 0; i < myFileText.length; i++) {
                myFileText[i] = read.nextLine();
            }


            for (int i = 0; i < myFileText.length; i++) {
                System.out.println(i + "||" + myFileText[i]);
            }

            System.out.println("length = " + myFileText.length);
            for (int j = 0; j < myFileText.length-1; j++) {
                for (int i = 0; i < myFileText.length - 1; i++) {
                    String[] line = myFileText[i].split("\\|");
                    String[] nextLine = myFileText[i + 1].split("\\|");



                    if (Integer.parseInt(line[0].trim()) > Integer.parseInt(nextLine[0].trim())) {
                        String temp = myFileText[i];
                        myFileText[i] = myFileText[i + 1];
                        myFileText[i + 1] = temp;

                    }
                }
            }



            FileWriter writer = new FileWriter(path, false);

            for (String myLine : myFileText) {
                writer.write(myLine + "\n");
            }

            writer.close();

        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void sortByName() {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            int amountOfLines = 0;
            while (read.hasNextLine()) {
                read.nextLine();
                amountOfLines++;
            }

            read.close();
            read = new Scanner(myFile);

            System.out.println("file length = " + amountOfLines);

            String[] myFileText = new String[amountOfLines];


            for (int i = 0; i < myFileText.length; i++) {
                myFileText[i] = read.nextLine();
            }


            for (int i = 0; i < myFileText.length; i++) {
                System.out.println(i + "||" + myFileText[i]);
            }

            System.out.println("length = " + myFileText.length);
            for (int j = 0; j < myFileText.length-1; j++) {
                for (int i = 0; i < myFileText.length - 1; i++) {
                    String[] line = myFileText[i].split("\\|");
                    String[] nextLine = myFileText[i + 1].split("\\|");



                    if (line[1].trim().length() > nextLine[1].trim().length()) {
                        String temp = myFileText[i];
                        myFileText[i] = myFileText[i + 1];
                        myFileText[i + 1] = temp;

                    }
                }
            }



            FileWriter writer = new FileWriter(path, false);

            for (String myLine : myFileText) {
                writer.write(myLine + "\n");
            }

            writer.close();



        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void sortByDate() {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            int amountOfLines = 0;
            while (read.hasNextLine()) {
                read.nextLine();
                amountOfLines++;
            }

            read.close();
            read = new Scanner(myFile);

            System.out.println("file length = " + amountOfLines);

            String[] myFileText = new String[amountOfLines];


            for (int i = 0; i < myFileText.length; i++) {
                myFileText[i] = read.nextLine();
            }


            for (int i = 0; i < myFileText.length; i++) {
                System.out.println(i + "||" + myFileText[i]);
            }

            System.out.println("length = " + myFileText.length);
            for (int j = 0; j < myFileText.length-1; j++) {
                for (int i = 0; i < myFileText.length - 1; i++) {
                    String[] line = myFileText[i].split("\\|");
                    String[] nextLine = myFileText[i + 1].split("\\|");



                    if (Integer.parseInt(line[2].trim()) > Integer.parseInt(nextLine[2].trim())) {
                        String temp = myFileText[i];
                        myFileText[i] = myFileText[i + 1];
                        myFileText[i + 1] = temp;

                    }
                }
            }



            FileWriter writer = new FileWriter(path, false);

            for (String myLine : myFileText) {
                writer.write(myLine + "\n");
            }

            writer.close();



        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void sortByCompany() {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            int amountOfLines = 0;
            while (read.hasNextLine()) {
                read.nextLine();
                amountOfLines++;
            }

            read.close();
            read = new Scanner(myFile);

            System.out.println("file length = " + amountOfLines);

            String[] myFileText = new String[amountOfLines];


            for (int i = 0; i < myFileText.length; i++) {
                myFileText[i] = read.nextLine();
            }


            for (int i = 0; i < myFileText.length; i++) {
                System.out.println(i + "||" + myFileText[i]);
            }

            System.out.println("length = " + myFileText.length);
            for (int j = 0; j < myFileText.length-1; j++) {
                for (int i = 0; i < myFileText.length - 1; i++) {
                    String[] line = myFileText[i].split("\\|");
                    String[] nextLine = myFileText[i + 1].split("\\|");



                    if (line[3].trim().length() > nextLine[3].trim().length()) {
                        String temp = myFileText[i];
                        myFileText[i] = myFileText[i + 1];
                        myFileText[i + 1] = temp;

                    }
                }
            }



            FileWriter writer = new FileWriter(path, false);

            for (String myLine : myFileText) {
                writer.write(myLine + "\n");
            }

            writer.close();



        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void sortByGenre() {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            int amountOfLines = 0;
            while (read.hasNextLine()) {
                read.nextLine();
                amountOfLines++;
            }

            read.close();
            read = new Scanner(myFile);


            String[] myFileText = new String[amountOfLines];


            for (int i = 0; i < myFileText.length; i++) {
                myFileText[i] = read.nextLine();
            }


            Arrays.sort(myFileText, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] line = o1.split("\\|");
                    String[] line2 = o2.split("\\|");
                    if (line[4].trim().length() == line2[4].trim().length()) {
                        return line[4].trim().compareTo(line2[4].trim());
                    }
                    else
                        return line[4].trim().length() - line2[4].trim().length();
                }
            });
//            for (int j = 0; j < myFileText.length-1; j++) {
//                for (int i = 0; i < myFileText.length - 1; i++) {
//                    String[] line = myFileText[i].split("\\|");
//                    String[] nextLine = myFileText[i + 1].split("\\|");
//
//
//
//                    if (line[4].trim().length() > nextLine[4].trim().length()) {
//                        String temp = myFileText[i];
//                        myFileText[i] = myFileText[i + 1];
//                        myFileText[i + 1] = temp;
//                    }
//                    else if (line[4].trim().length() == nextLine[4].trim().length())
//                    {
//                        if (line[4].trim().compareTo(nextLine[4].trim()) > 0) {
//                            String temp = myFileText[i];
//                            myFileText[i] = myFileText[i + 1];
//                            myFileText[i + 1] = temp;
//                        }
//                    }
//                }
//            }



            FileWriter writer = new FileWriter(path, false);

            for (String myLine : myFileText) {
                writer.write(myLine + "\n");
            }

            writer.close();



        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
}
