package vn.fiosoft.emp;

import vn.fiosoft.ai.CommandApplication;
import vn.fiosoft.ai.CommandMain;
import vn.fiosoft.service.EMPApplication;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);      
        EMPApplication.start();
    }
    
    @Override
    protected void onResume() {        
        super.onResume();
        EMPApplication.mInstance.setCommand(new CommandMain(this));
    }
    
    @Override
    protected void onPause() {       
        super.onPause();
        EMPApplication.mInstance.setCommand(new CommandApplication());
    }
}
