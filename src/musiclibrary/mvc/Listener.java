package musiclibrary.mvc;

public class Listener {
    private boolean isModelChanged;

    public Listener() {
        isModelChanged=false;
    }
    public void changed(){
        isModelChanged=true;
    }

    public boolean listen(){
        if (isModelChanged=false) return false;
        isModelChanged=false;
        return  true;
    }
}
