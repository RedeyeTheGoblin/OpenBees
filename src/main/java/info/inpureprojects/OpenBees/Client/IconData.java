package info.inpureprojects.OpenBees.Client;

/**
 * Created by den on 8/6/2014.
 */
public class IconData {

    private String domain;
    private String path;
    private String tag;

    public IconData(String domain, String path, String tag) {
        this.domain = domain;
        this.path = path;
        this.tag = tag;
    }

    public String getDomain() {
        return domain;
    }

    public String getPath() {
        return path;
    }

    public String getTag() {
        return tag;
    }
}
