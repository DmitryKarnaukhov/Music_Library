package musiclibrary;

import musiclibrary.entities.*;
import musiclibrary.mvc.controller.ArtistController;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.controller.TrackListController;
import musiclibrary.mvc.controller.UserController;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.SaveLoadService;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException,InterruptedException {
        System.out.println(new File("").getAbsolutePath());

        ArtistController artistController = new ArtistController(new Model<Artist>());
        TrackController trackController = new TrackController(new Model<Track>());
        TrackListController trackListController = new TrackListController((new Model<TrackList>()));
        UserController userController = new UserController(new Model<User>());
        SaveLoadService saveLoadService = new SaveLoadService(artistController,trackController,trackListController,userController);

        artistController.addListener(saveLoadService);
        trackController.addListener(saveLoadService);
        trackListController.addListener(saveLoadService);
        userController.addListener(saveLoadService);

        int tygaid = artistController.addArtist("Tyga");
        int id= trackController.addTrack("Get Rich", artistController.getArtist(tygaid), 1.22, Genre.Rap);
        trackController.addTrack("Too Rich",artistController.getArtist(tygaid),3.48,Genre.Rap);
       // System.out.println("Addad: "+ trackController.getTrack(id));

        //saveLoadService.save(trackController.getTrackContainer());

        TrackController trackController1 = new TrackController(saveLoadService.load()[1]);
        System.out.println("Getted:" +  trackController1.getTrack(id));

    }
}
