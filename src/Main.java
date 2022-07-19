import data.DataPrintingManager;
import data.DataSavingManager;
import data.models.Game;
import data.models.Genre;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String path = "myWorkingText.txt";
        Scanner scanner = new Scanner(System.in);
        DataPrintingManager printData = new DataPrintingManager(path);
        DataSavingManager saveData = new DataSavingManager(path);

        saveData.sortByGenre();
        //while(true) {
            System.out.println("Make your choice:\n1 - to add new game\n2 - delete game\n3 - find game\n4 - print all the games");
            //@TODO switch case
        switch (scanner.nextInt()) {
            case 1 -> saveData.saveTheGame();
            case 2 -> {
                System.out.println("Do you want to delete by id or name?");
                if (scanner.nextLine().equals("id")) {
                    System.out.println("Enter the id");
                    saveData.deleteGameById(scanner.nextInt());
                }
                if (scanner.nextLine().equals("name")) {
                    System.out.println("Enter the game name");
                    saveData.deleteGameByName(scanner.nextLine());
                }
            }
            case 3 -> {
                System.out.println("By what do you want to find the game?");
                if (scanner.nextLine().equals("id")) {
                    System.out.println("Enter the id");
                    printData.findById(scanner.nextInt());
                }
                if (scanner.nextLine().equals("name")) {
                    System.out.println("Enter the game name");
                    printData.findByName(scanner.nextLine());
                }
                if (scanner.nextLine().equals("date")) {
                    System.out.println("Enter the date");
                    printData.findByDate(scanner.nextInt());
                }
                if (scanner.nextLine().equals("company")) {
                    System.out.println("Enter the company");
                    printData.findByCompany(scanner.nextLine());
                }
                if (scanner.nextLine().equals("genre")) {
                    System.out.println("Enter the genre");
                    printData.findByGenre(scanner.nextLine());
                }
            }
            case 4 -> printData.printAllTheGames();
        }
        //}

        //System.out.printf(age + "asdn nasnd nasdn ");
        //System.out.printf("%d  %d asdn nasnd nasdn ", age, 10);
//        System.out.printf("%100.2fHello!", 51664184.1535252519151651615616516156);




        Game firstGame = new Game("Rust one", 2013, "Facepunch", Genre.Adventurous);
        Game second = new Game("Rust two", 2013, "Facepunch", Genre.Rpg);
        Game third = new Game("Rust three", 2013, "Facepunch", Genre.Survival);



//        DataSavingManager saveData = new DataSavingManager(path);
//        saveData.saveTheGame(firstGame);
//        saveData.saveTheGame(second);
//        saveData.saveTheGame(third);


        //saveData.deleteGameByName("Rust one");
//        saveData.sortById();


//        1 | Rust number | 2013 | Facepunch | Survival |
//                2 | Rust one | 2014 | SmthingOne | Survival |
//                123 | Rust two | 2015 | GoldileGen | Survival |
//                4 | Rust three | 2016 | GenreTwo | Survival |

//       saveData.deleteGameByName("Rust one");
//        printData.printAllTheGames();
//        printData.findByName("Rust two");
//        printData.findByCompany("Facepunch");
//        printData.findByGenre("Survival");
//        printData.printAllTheGames();
//        printData.printAllTheGames();
//        printData.printAllTheGames();





    }
}