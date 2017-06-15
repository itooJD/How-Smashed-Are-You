package com.emptyshit.hsay.lockComponent;

import android.content.Context;

import com.emptyshit.hsay.playerComponent.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by tungu on 15/06/2017.
 */

public class LockComponent implements LockComponentInterface{

    private LockStatus lockStatus;
    private Context context;
    private final String fileName = "lock";

    public LockComponent(Context context){
        this.lockStatus = new LockStatus();
        this.context = context;
    }

    @Override
    public void lock() {
        this.lockStatus.activateLock();
        try{
            FileOutputStream fileOutputStream = this.context.openFileOutput(this.fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.lockStatus);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unLock() {
        this.lockStatus.deactivateLock();
        try{
            FileOutputStream fileOutputStream = this.context.openFileOutput(this.fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.lockStatus);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isLocked() {
        try {
            FileInputStream fileInputStream = this.context.openFileInput(this.fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            LockStatus lockStatus = (LockStatus) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            this.lockStatus = lockStatus;
            return this.lockStatus.isLocked();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}
