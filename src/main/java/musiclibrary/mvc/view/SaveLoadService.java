package musiclibrary.mvc.view;

import com.google.inject.Inject;
import com.google.inject.Provides;
import musiclibrary.mvc.controller.ArtistController;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.controller.TrackListController;
import musiclibrary.mvc.controller.UserController;
import musiclibrary.mvc.model.Model;

import javax.inject.Singleton;
import java.io.*;

public class SaveLoadService implements Listener {
    private String path = new File("").getAbsolutePath();
    private Model[] models;

    @Inject
    public SaveLoadService(ArtistController AC, TrackController TC, TrackListController TLC, UserController UC) {
        this.models = getNewModels(AC, TC, TLC, UC);
    }
    //@Provides @Singleton
    private  Model[] getNewModels(ArtistController AC, TrackController TC, TrackListController TLC, UserController UC) {
        return new Model[]{AC.getArtistContainer(),TC.getTrackContainer(), TLC.getTrackListContainer(),UC.getUserContainer()};
    }

    @Override
    public void somethingChanged(int modelNum, boolean delete, int id) {
        switch (modelNum){
            case 0:{
                if (delete==false){
                    ArtistController trackController = new ArtistController(models[modelNum]);
                    System.out.println("Changed:"+ trackController.getArtist(id));
                }
                break;
            }
            case 1:{
                if (delete==false){
                    TrackController trackController = new TrackController(models[modelNum]);
                    System.out.println("Changed:"+ trackController.getTrack(id));
                }
                break;
            }
            case 2:{
                if (delete==false){
                    TrackListController trackController = new TrackListController(models[modelNum]);
                    System.out.println("Changed:"+ trackController.getTrackList(id));
                }
                break;
            }
            case 3:{
                if (delete==false) {
                    UserController userController =new UserController((models[modelNum]));
                    System.out.println("Changed:"+ userController.getUser(id));
                }
                break;
            }
        }
        this.save(models);
    }

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
