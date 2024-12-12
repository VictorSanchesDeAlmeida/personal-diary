import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int count = 0; // Contador global de IDs
    private int id;
    private String annotation;
    private String date;

    // Construtor para inicializar anotação
    public Diary(String annotation) {
        this.annotation = annotation;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    public String getAnnotation() {
        return annotation;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", text: " + annotation + ", date: " + date + "}";
    }
}
