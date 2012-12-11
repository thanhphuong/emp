package vn.fiosoft.service;

import java.util.ArrayList;

import vn.fiosoft.ai.Command;
import vn.fiosoft.ai.CommandApplication;
import vn.fiosoft.log.Log4J;
import vn.fiosoft.settings.Settings;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

public class EMPApplication extends Application {

	private static int mMaxResults = 3;

	private static Boolean isRuning = false;
	private Boolean isListening = false;
	public static EMPApplication mInstance;

	private SpeechRecognizer mSpeechRecognizer;
	private Intent mIntentSpeechReconizer;
	private Command command;

	

	@Override
	public void onCreate() {
		super.onCreate();

		mInstance = this;

		// create SpeechRecognizer object
		mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
		mSpeechRecognizer.setRecognitionListener(new EMPRecognitionListener());

		command = new CommandApplication();

		

	}

	
	public static Boolean getIsRuning() {
		return isRuning;
	}

	/**
	 * start speech recognizer
	 * 
	 * @param maxResult
	 */
	public void startSpeechRecognizer(int maxResult) {
		mMaxResults = maxResult;
		if (mIntentSpeechReconizer == null) {
			mIntentSpeechReconizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			mIntentSpeechReconizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			mIntentSpeechReconizer.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());

		}
		mIntentSpeechReconizer.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, maxResult);
		mSpeechRecognizer.startListening(mIntentSpeechReconizer);
	}

	/**
	 * stop speech recognizer
	 */
	public void stopSpeechRecognizer() {
		mSpeechRecognizer.stopListening();
	}

	/**
	 * start application and service
	 */
	public static void start() {
		if (isRuning == true)
			return;

		isRuning = true;

		// initial log
		Log4J.mLevel = Settings.Log4J.mLevel;

		// start Service
		mInstance.startService(new Intent(mInstance, EMPService.class));

		mInstance.startSpeechRecognizer(mMaxResults);

	}

	public Boolean getIsListening() {
		return isListening;
	}

	public void setIsListening(Boolean isListening) {
		this.isListening = isListening;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * This class Used for receiving notifications from the SpeechRecognizer
	 * when the recognition related events occur. All the callback are executed
	 * on the Application main thread.
	 * 
	 */
	private class EMPRecognitionListener implements RecognitionListener {

		public void onBeginningOfSpeech() {

		}

		public void onBufferReceived(byte[] buffer) {

		}

		public void onEndOfSpeech() {

		}

		public void onError(int error) {
			startSpeechRecognizer(mMaxResults);
		}

		public void onEvent(int eventType, Bundle params) {

		}

		public void onPartialResults(Bundle partialResults) {

		}

		public void onReadyForSpeech(Bundle params) {

		}

		public void onResults(Bundle results) {
			ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

			for (String s : data) {
				if (getCommand().execute(s))
					break;
			}

			startSpeechRecognizer(mMaxResults);
		}

		public void onRmsChanged(float rmsdB) {

		}

	}

}
