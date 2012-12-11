package vn.fiosoft.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

public class EMPService extends Service {

    @Override
    public IBinder onBind(Intent intent) {	
	return null;
    }

    @Override
    public void onCreate() {	
	EMPApplication.start();
    }

    @Override
    public void onDestroy() {
	Process.killProcess(Process.myPid());
    }

}
