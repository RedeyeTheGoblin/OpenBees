package info.inpureprojects.OpenBees.API;

import info.inpureprojects.OpenBees.API.Common.Exceptions.ModNotInstalledException;

/**
 * Created by den on 8/6/2014.
 */
public class OpenBeesAPI {

    private static IOpenBeesAPI apiHandler;

    public static IOpenBeesAPI getAPI() {
        if (apiHandler == null) {
            try {
                setup();
            } catch (ModNotInstalledException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return apiHandler;
    }

    private static void setup() throws ModNotInstalledException {
        try {
            apiHandler = (IOpenBeesAPI) Class.forName(modInfo.proxyCommon).getDeclaredField("apiHandler").get(null);
        } catch (Throwable t) {
            throw new ModNotInstalledException();
        }
    }

}
