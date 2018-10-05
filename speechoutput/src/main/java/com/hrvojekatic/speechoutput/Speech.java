package com.hrvojekatic.speechoutput;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class Speech {
	private static final String TAG = Speech.class.getSimpleName();
	private static final String DEFAULT_INIT_MESSAGE = "TextToSpeech ready.";
	private static TextToSpeech sTts;

	public static void create(final Context context, final Locale locale, final String initMessage) {
		sTts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status == TextToSpeech.SUCCESS) {
					int result = sTts.setLanguage(locale);
					if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
						Log.e(TAG, "This Language is not supported");
					}
					speak(initMessage);
				} else {
					Log.e(TAG, "TextToSpeech initialization failed.");
				}
			}
		});
	}

	public static void create(final Context context, final String initMessage) {
		create(context, Locale.US, initMessage);
	}

	public static void create(final Context context, final Locale locale) {
		create(context, locale, DEFAULT_INIT_MESSAGE);
	}

	public static void create(final Context context) {
		create(context, Locale.US, DEFAULT_INIT_MESSAGE);
	}

	public static void destroy() {
		if (sTts != null) {
			sTts.stop();
			sTts.shutdown();
		}
	}

	public static TextToSpeech getTts() {
		return sTts;
	}

	public static void speak(String text) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			sTts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
		} else {
			sTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}
	}
}