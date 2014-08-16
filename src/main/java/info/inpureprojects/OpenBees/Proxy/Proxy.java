package info.inpureprojects.OpenBees.Proxy;

import com.google.gson.Gson;

import java.io.File;

/**
 * Created by den on 8/6/2014.
 */
public abstract class Proxy {

    public abstract void setupAPI();

    public abstract void setupConfig(File configFolder);

    public abstract void print(String msg);

    public abstract boolean isShiftKey();

    public abstract boolean isShiftCtrlKey();

    public abstract boolean isCtrlKey();

    public abstract Gson getJson();

}
