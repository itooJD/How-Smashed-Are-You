package com.emptyshit.hsay.lockComponent;

/**
 * Created by tungu on 15/06/2017.
 */
import java.io.Serializable;

public class LockStatus implements Serializable{

    private static final long serialVersionUID = 3L;
    private boolean lock = false;

    public LockStatus(){
    }

    void activateLock(){
        this.lock = true;
    }

    void deactivateLock(){
        this.lock = false;
    }

    boolean isLocked(){
        return this.lock;
    }
}
