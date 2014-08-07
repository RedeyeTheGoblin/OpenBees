package info.inpureprojects.OpenBees.API.Client;

/**
 * Created by den on 8/6/2014.
 */
public class IconData {

    private String domain;
    private String path;
    private String tag;
    private boolean block;

    public IconData(String domain, String path, String tag, boolean block) {

        this.domain = domain;
        this.path = path;
        this.tag = tag;
        this.block = block;
    }

    public boolean isBlock() {
        return block;
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
