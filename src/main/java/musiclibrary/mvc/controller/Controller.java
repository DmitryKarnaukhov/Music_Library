package musiclibrary.mvc.controller;

import com.google.inject.Guice;
import com.google.inject.ImplementedBy;
import com.google.inject.Injector;
import musiclibrary.mvc.view.Listener;

interface Controller {
    void addListener (Listener listener);
    void update(boolean del,int id);
}
