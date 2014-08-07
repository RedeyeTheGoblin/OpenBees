package info.inpureprojects.OpenBees.API;

import info.inpureprojects.OpenBees.API.Client.ClientAPI;
import info.inpureprojects.OpenBees.API.Common.CommonAPI;

/**
 * Created by den on 8/6/2014.
 */
public interface IOpenBeesAPI {

    public CommonAPI getCommonAPI();

    public ClientAPI getClientAPI();

}
