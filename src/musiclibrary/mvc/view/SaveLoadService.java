package musiclibrary.mvc.view;

import musiclibrary.mvc.Listener;
import musiclibrary.mvc.controller.MainController;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.controller.UserController;

import java.io.*;

public class SaveLoadService {
    Listener listener;
    MainController mainController;

    public void saveMainController(ObjectOutputStream oos) throws IOException{
        try{
            oos.writeObject(mainController);
            oos.flush();
            oos.close();
        }
        catch(IOException exception){}
    }
    public void loadMainController(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        try {
            mainController = (MainController) ois.readObject();
        }
        catch(IOException exception){}
        catch(ClassNotFoundException e){}
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
