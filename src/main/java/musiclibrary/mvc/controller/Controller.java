package musiclibrary.mvc.controller;

import com.google.inject.Guice;
import com.google.inject.ImplementedBy;
import com.google.inject.Injector;
import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.Listener;

public interface Controller {
    void addListener (Listener listener);
    void update(boolean del,int id);
    Model<?> getContainer();
}
