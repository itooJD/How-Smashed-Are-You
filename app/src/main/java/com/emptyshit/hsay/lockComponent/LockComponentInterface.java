package com.emptyshit.hsay.lockComponent;

/**
 * Created by tungu on 09/04/2017.
 */

public interface LockComponentInterface {

    void lockApp();

    boolean getRunningApps();

    void lockAppIntent();

    void releaseLock();
}
