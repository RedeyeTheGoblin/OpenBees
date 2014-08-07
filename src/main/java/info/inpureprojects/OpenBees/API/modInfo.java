package info.inpureprojects.OpenBees.API;

/**
 * Created by den on 8/6/2014.
 */
public class modInfo {

    public static final String modid = "openbees";
    public static final String name = "INpure|OpenBees";
    public static final String version = "dev";
    public static final String deps = "required-after:inpure|core";

    private static final String proxy_path = "info.inpureprojects.OpenBees.Proxy.";
    public static final String proxyCommon = proxy_path + "ProxyCommon";
    public static final String proxyClient = proxy_path + "ProxyClient";

}
