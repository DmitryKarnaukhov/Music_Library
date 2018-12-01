package musiclibrary;

import musiclibrary.entities.Genre;
import musiclibrary.mvc.controller.MainController;
import musiclibrary.mvc.view.SaveLoadService;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException,InterruptedException {
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        SaveLoadService sls =new SaveLoadService();
        MainController mainController = new MainController();
        mainController.getTrackController().addTrack("Abba","Abbator","Abbalbum", 3.15, Genre.Rap);
        System.out.println(mainController.getTrackController().getTrack(0));

        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
    }
}
