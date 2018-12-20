package musiclibrary.mvc.controller;

import com.google.inject.Inject;
import musiclibrary.mvc.model.Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public abstract class GenericController<T>{
    protected Model<T> container;

    public GenericController() {
    }

    @Inject
    public GenericController(Model<T> Container) {
        this.container = Container;
    }

    public int getNextId() {
        int id =0;
        checkFolder();
        String path = (new File("").getAbsolutePath())+"/src/savedfiles/id.out";
        if(! new File(path).exists()){
            try(FileWriter fileWriter = new FileWriter(path,false)){
                fileWriter.write(0);
            }catch (Exception e){
                e.getMessage();
            }
        }else{
            try(FileReader fileReader =new FileReader(path)){
                id=fileReader.read();
            }catch (Exception e){
                e.getMessage();
            }
            try(FileWriter fileWriter = new FileWriter(path,false)){
                fileWriter.write(id+1);
            }catch (Exception e){
                e.getMessage();
            }
        }
        return id;
    }

    public void del(int Id) {
        container.remove(Id);
    }

    public T get(int id) {
        return container.getItem(id);
    }

    public Model<T> getContainer() {
        return container;
    }

    private void checkFolder () {
        String path = (new File("").getAbsolutePath())+ "/src/savedfiles/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
}
