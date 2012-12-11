package vn.fiosoft.service;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

public class EMPTextToSpeech implements TextToSpeech.OnInitListener {

    private Context mContext;
    private TextToSpeech mTextToSpeech;

    public EMPTextToSpeech(Context context) {
	mContext = context;
	mTextToSpeech = new TextToSpeech(mContext, this);
    }

    /**
     * Say text
     * 
     * @param text
     */
    public void say(String text) {
	mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
	if (status == TextToSpeech.SUCCESS)
	    Log.e("test", "success");
	else
	    Log.e("test", "fail");

    }

}
