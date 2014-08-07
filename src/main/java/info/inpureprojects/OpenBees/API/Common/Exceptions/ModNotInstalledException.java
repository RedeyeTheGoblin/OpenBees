package info.inpureprojects.OpenBees.API.Common.Exceptions;

/**
 * Created by den on 8/6/2014.
 */
public class ModNotInstalledException extends Exception {

    public ModNotInstalledException() {
        super("Attempted to use the OpenBees API but OpenBees is not installed!");
    }
}
