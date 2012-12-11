package vn.fiosoft.emp;

import vn.fiosoft.ai.CommandMain;
import vn.fiosoft.service.EMPApplication;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        EMPApplication.mInstance.setCommand(new CommandMain(this));
    }
}
