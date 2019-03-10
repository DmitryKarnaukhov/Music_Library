package musiclibrary.mvc.controller;

import com.google.inject.Inject;
import musiclibrary.entities.Entity;
import musiclibrary.mvc.model.Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public abstract class GenericController<T extends Entity>{
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
                throw new RuntimeException("Cant write new id=0 in getNextId",e);
            }
        }else{
            try(FileReader fileReader =new FileReader(path)){
                id=fileReader.read();
            }catch (Exception e){
                throw new RuntimeException("Cant read id in getNextId",e);
            }
            try(FileWriter fileWriter = new FileWriter(path,false)){
                fileWriter.write(id+1);
            }catch (Exception e){
                throw new RuntimeException("Cant write next id in getNextId",e);
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

    public List<T> getAll() { return null; }

    public void replace(T item) {

    }

    public void add(T item) {

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
