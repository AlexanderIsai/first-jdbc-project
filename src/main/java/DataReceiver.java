import java.util.Scanner;

public class DataReceiver {
    private int listSize;
    private int site;

    public DataReceiver() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("У нас 99 машин. Введите количество машин на экране");
        this.listSize = scanner.nextInt();
        System.out.println("Какую страницу Вы хотите посмотреть?");
        this.site = scanner.nextInt();
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "DataReceiver{" +
                "listSize=" + listSize +
                ", site=" + site +
                '}';
    }
}
