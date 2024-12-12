import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Diary implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int count = 0;
    private int id;
    private String annotation;
    private String date;

    public void setAnnotation(String annotation){
        this.annotation = annotation;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.id = ++count;
    }


    @Override
    public String toString(){
        return "id: "+ id +", text: " + annotation + ", date: " + date;
    }

}
