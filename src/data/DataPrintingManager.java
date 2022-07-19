package data;


import java.io.File;
import java.util.Scanner;

public class DataPrintingManager {
    private final String path;

    public DataPrintingManager(String path) {
        this.path = path;
    }

    public void printFirstTheGame() {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            while (read.hasNextLine()) {
                String data = read.nextLine();
//                if (data.equals("|"))
//                    break;
                System.out.print(data + " ");
            }
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void printAllTheGames() {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);
            System.out.println("+------+--------------+------+------------+----------+");
            System.out.printf("%-6s %-13s %7s %-12s %-10s %1s","| Id ", "| Game name", "| Date", "| Company", "| Genre", "|");
            System.out.println();
            System.out.println("+------+--------------+------+------------+----------+");
            while (read.hasNextLine()) {
                String id = getNext(read);
                String name = getNext(read);
                String date = getNext(read);
                String company = getNext(read);
                String genre = getNext(read);
              System.out.printf("%-6s %-13s %7s %-12s %-10s %1s","| " + id, "| " + name, "| " + date, "| " + company, "| " + genre, "|");
                System.out.println();
                read.nextLine();
            }
            System.out.println("+------+--------------+------+------------+----------+");
            read.nextLine();

        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }


        }

    public void findById (int id) {
        try{
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);

            while (read.hasNextLine()) {
                String mId = getNext(read);
                if (mId.equals(Integer.toString(id))) {
                    String name = getNext(read);
                    String date = getNext(read);
                    String company = getNext(read);
                    String genre = getNext(read);
                    System.out.println("id = " + id + " name : " + name + " date = " + date + " company : " + company + " genre: " + genre);
                }
                read.nextLine();
            }
        }
        catch(Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void findByName(String name) {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);

            while (read.hasNextLine()) {

                String temp;
                StringBuilder mName = new StringBuilder();

                while (true) {
                    temp = read.next();
                    //System.out.println("temp = " + temp);
                    if (temp.equals("|")) {
                        if (mName.toString().trim().equals(name)) {
                            System.out.println("We found the game!");
                            System.out.println("Name : " + mName);
                            System.out.println("date = " + getNext( read));
                            System.out.println("Company name = " + getNext( read));
                            System.out.println("genre = " + getNext( read));
                        }
                        else
                            read.nextLine();
                        break;
                    }

                    else
                        mName.append(temp).append(" ");

                }

                System.out.println("mName = " + mName);

            }
        }
        catch (Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void findByDate(int Date) {
        try{
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);

            while (read.hasNextLine()) {
                String id = getNext(read);
                String name = getNext(read);
                String mDate = getNext(read);
            if (mDate.equals(Integer.toString(Date))) {
                String company = getNext(read);
                String genre = getNext(read);
                System.out.println("id = " + id + " name : " + name + " date = " + Date + " company : " + company + " genre: " + genre);
            }
            //else
                read.nextLine();
            }
        }
        catch(Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }

    public void findByCompany(String company) {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);

            while (read.hasNextLine()) {
                String id = getNext(read);
                String name = getNext(read);
                String date = getNext(read);
                String mCompany = getNext(read);
                if (mCompany.equals(company)) {
                    String genre = getNext(read);
                    System.out.println("id = " + id + ", name : " + name + " date = " + date + " company : " + company + " genre : " + genre);
                }
                read.nextLine();


            }
        }
        catch(Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    public void findByGenre(String genre) {
        try {
            File myFile = new File(path);
            Scanner read = new Scanner(myFile);

            while (read.hasNextLine()) {
                String id = getNext(read);
                String name = getNext(read);
                String date = getNext(read);
                String company = getNext(read);
                String mGenre = getNext(read);
                if (mGenre.equals(genre)) {
                    System.out.println("id = " + id + ", name : " + name + " date = " + date + " company : " + company + " genre : " + genre);
                }
                read.nextLine();
            }
        }
        catch(Exception exception) {
            System.out.println("We don't have such a file!");
        }
    }
    private String getNext(Scanner read) {
        String temp;
        StringBuilder result = new StringBuilder();
        while (true) {
            temp = read.next();
            if (temp.equals("|")) {
                return result.toString().trim();
            }
            else
                result.append(temp).append(" ");

        }
    }
}