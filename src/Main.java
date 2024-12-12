import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE = "diary.txt";

    public static void main(String[] args) {
        List<Diary> diaryList = loadDiaryList();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect any option: ");
            System.out.println("1- Add Annotation");
            System.out.println("2- Delete Annotation");
            System.out.println("3- Display Annotations");
            System.out.println("4- Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do Scanner

            switch (option) {
                case 1: // Adicionar anotação
                    System.out.println("Enter Annotation: ");
                    String annotation = scanner.nextLine();

                    Diary diary = new Diary(annotation);
                    diaryList.add(diary);
                    saveDiaryList(diaryList);
                    System.out.println("Annotation added: " + diary);
                    break;

                case 2: // Excluir anotação pelo ID

                    if (diaryList.isEmpty()) {
                        System.out.println("No diary found");
                    } else {
                        for (Diary d : diaryList) {
                            System.out.println(d);
                        }
                    }

                    System.out.println("Enter ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    boolean removed = diaryList.removeIf(d -> d.getId() == idToDelete);

                    if (removed) {
                        saveDiaryList(diaryList);
                        System.out.println("Annotation with ID " + idToDelete + " deleted successfully.");
                    } else {
                        System.out.println("Annotation with ID " + idToDelete + " not found.");
                    }
                    break;

                case 3: // Mostrar anotações
                    if (diaryList.isEmpty()) {
                        System.out.println("No annotations found.");
                    } else {
                        System.out.println("Displaying all annotations:");
                        for (Diary d : diaryList) {
                            System.out.println(d);
                        }
                    }
                    break;

                case 4: // Sair do programa
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Método para carregar as anotações do arquivo
    private static List<Diary> loadDiaryList() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (List<Diary>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); // Retorna uma lista vazia se o arquivo não existir
        }
    }

    // Método para salvar a lista de anotações no arquivo
    private static void saveDiaryList(List<Diary> diaryList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(diaryList);
        } catch (IOException e) {
            System.out.println("Error saving annotations: " + e.getMessage());
        }
    }
}
