package musiclibrary.mvc.controller;

import java.io.Serializable;

public class MainController implements Serializable {
    TrackController trackController;
    UserController userController;

    public MainController() {
        userController=new UserController();
        trackController=new TrackController();
    }

    public MainController(TrackController trackController, UserController userController) {
        this.trackController = trackController;
        this.userController = userController;
    }

    public TrackController getTrackController() {
        return trackController;
    }

    public void setTrackController(TrackController trackController) {
        this.trackController = trackController;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public Controller getController(String type){
        if (type=="Track") return trackController;
        if (type=="User") return userController;
        return null;
    }

}
