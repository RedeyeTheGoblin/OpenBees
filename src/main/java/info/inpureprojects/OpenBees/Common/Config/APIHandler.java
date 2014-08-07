package info.inpureprojects.OpenBees.Common.Config;

import info.inpureprojects.OpenBees.API.Client.ClientAPI;
import info.inpureprojects.OpenBees.API.Common.CommonAPI;
import info.inpureprojects.OpenBees.API.IOpenBeesAPI;

/**
 * Created by den on 8/6/2014.
 */
public class APIHandler implements IOpenBeesAPI {

    public CommonAPI common = new CommonAPI();
    public ClientAPI client = new ClientAPI();

    @Override
    public CommonAPI getCommonAPI() {
        return this.common;
    }

    @Override
    public ClientAPI getClientAPI() {
        return this.client;
    }
}
