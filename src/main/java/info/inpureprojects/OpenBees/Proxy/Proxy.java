package info.inpureprojects.OpenBees.Proxy;

import java.io.File;

/**
 * Created by den on 8/6/2014.
 */
public abstract class Proxy {

    public abstract void setupAPI();

    public abstract void setupConfig(File configFolder);

    public abstract void print(String msg);

}
