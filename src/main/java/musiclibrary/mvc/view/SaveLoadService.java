package musiclibrary.mvc.view;

import com.google.inject.Inject;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Track;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.model.ModelTypes;

import java.io.*;

public class SaveLoadService extends Listener {
    private Model<Artist> artistModel;
    private Model<Track> trackModel;
    private Model<TrackList> trackListModel;
    private Model<User> userModel;

    @Inject
    public SaveLoadService(Model<Artist> ma, Model<Track> mt, Model<TrackList> mtl, Model<User> mu) {
        this.artistModel = ma;
        this.trackModel = mt;
        this.trackListModel = mtl;
        this.userModel = mu;
        this.listen();
    }

    private void listen(){
        artistModel.addListener(new Listener() {
            @Override
            public void somthingChanged() {
                save(artistModel,ModelTypes.Artist);
                System.out.println("Artist saved");
            }
        });
        trackModel.addListener(new Listener() {
            @Override
            public void somthingChanged() {
                save(trackModel,ModelTypes.Track);
                System.out.println("Track saved");
            }
        });
        trackListModel.addListener(new Listener() {
            @Override
            public void somthingChanged() {
                save(trackListModel, ModelTypes.TrackList);
                System.out.println("TrackList saved");
            }
        });
        userModel.addListener(new Listener() {
            @Override
            public void somthingChanged() {
                save(userModel, ModelTypes.User);
                System.out.println("User saved");
            }
        });
    }


    public void save(Model model, ModelTypes className){
        String path = (new File("").getAbsolutePath())+"\\"+"src"+"\\"+"savedfiles"+"\\"+className+".out";
        checkFolder();
        try(FileOutputStream fos = new FileOutputStream(path)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(model);
                oos.flush();
            }
        }
       catch(IOException exception){throw new RuntimeException("IO in save SLS");}
    }
    public Model load(ModelTypes className){
        String path =( new File("").getAbsolutePath())+"\\"+"src"+"\\"+"savedfiles"+"\\"+className+".out";
        checkFolder();
        Model model=new Model();
        try ( FileInputStream fis = new FileInputStream(path)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                model=(Model) ois.readObject();
            }
        }
        catch(IOException exception){throw new RuntimeException("IO in load SLS");}
        catch(ClassNotFoundException e){throw new RuntimeException("ClassNotFound in load SLS");}

        return model;
    }
    private void checkFolder () {
        String path = new File("").getAbsolutePath();
        File folder = new File(path + "/src/savedfiles/");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void saveAll(){
        this.save(artistModel,ModelTypes.Artist);
        this.save(trackModel,ModelTypes.Track);
        this.save(trackListModel,ModelTypes.TrackList);
        this.save(userModel,ModelTypes.User);
    }
}
