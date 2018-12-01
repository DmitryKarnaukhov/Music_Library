package musiclibrary.mvc.view;

import musiclibrary.mvc.Listener;
import musiclibrary.mvc.model.Model;

import java.io.*;

public class SaveLoadService {
    Listener listener;
    String path = new File("").getAbsolutePath();

    public void save(Model...models){
        try(FileOutputStream fos = new FileOutputStream(path+"/src/savedfiles/model.out")){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeInt(models.length);
                for (Model model:models
                     ) {
                    oos.writeObject(model);
                }
                oos.flush();
            }
        }
        catch(IOException exception){}
    }
    public Model[] load(){
        Model[] models =null;
        try ( FileInputStream fis = new FileInputStream(path+"/src/savedfiles/model.out")){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                int length = ois.readInt();
                models = new Model[length];
                for (int i = 0; i < length; i++) {
                    models[i]=(Model) ois.readObject();
                }
            }
        }
        catch(IOException exception){}
        catch(ClassNotFoundException e){}
        return models;
    }
}
