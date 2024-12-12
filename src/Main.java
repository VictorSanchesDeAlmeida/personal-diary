import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String file = "diary.txt";

        Diary diary = new Diary();

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Select any option: ");

            System.out.println("1- Add Annotation");
            System.out.println("2- Delete Annotation");
            System.out.println("3- Display Annotation");

            System.out.println("4- Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1:
                    System.out.println("Enter Annotation: ");
                    String annotation = scanner.nextLine();

                    diary.setAnnotation(annotation);

                    try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){

                        out.writeObject(diary);
                        System.out.println("Annotation added: " + diary);

                    } catch (IOException e) {
                        System.out.println("Error saving annotation: " + e.getMessage());
                    }
                    break;
                case 2:
                    diary.setAnnotation(null);
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                        out.writeObject(diary);
                        System.out.println("Annotation deleted successfully.");
                    } catch (IOException e) {
                        System.out.println("Error deleting annotation: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Display Annotation: ");

                    try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){

                        Diary readDiary = (Diary) in.readObject();
                        System.out.println(readDiary);

                    }catch (IOException | ClassNotFoundException  e) {
                        System.out.println("Error displaying annotation: " + e.getMessage());
                    }

                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
            }
        }
    }
}