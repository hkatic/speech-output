package com.hrvojekatic.speechoutput;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

/**
 * A speech class for easier handling of text-to-speech in Android.
 */
public final class Speech {
	private static final String TAG = Speech.class.getSimpleName();
	private static final String DEFAULT_INIT_MESSAGE = "TextToSpeech ready.";
	private static TextToSpeech sTts;

	/**
	 * Creates TextToSpeech object instance and performs check if something went wrong.
	 *
	 * @param context     An application or activity context
	 * @param locale      A language locale to use, default is Locale.US
	 * @param initMessage A message to speak when TextToSpeech is successfully initialized
	 */
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

	/**
	 * Creates TextToSpeech object instance and performs check if something went wrong.
	 *
	 * @param context     An application or activity context
	 * @param initMessage A message to speak when TextToSpeech is successfully initialized
	 */
	public static void create(final Context context, final String initMessage) {
		create(context, Locale.US, initMessage);
	}

	/**
	 * Creates TextToSpeech object instance and performs check if something went wrong.
	 *
	 * @param context An application or activity context
	 * @param locale  A language locale to use, default is Locale.US
	 */
	public static void create(final Context context, final Locale locale) {
		create(context, locale, DEFAULT_INIT_MESSAGE);
	}

	/**
	 * Creates TextToSpeech object instance and performs check if something went wrong.
	 *
	 * @param context An application or activity context
	 */
	public static void create(final Context context) {
		create(context, Locale.US, DEFAULT_INIT_MESSAGE);
	}

	/**
	 * Destroys TextToSpeech object to clean up resources.
	 * Use this on onDestroy method.
	 */
	public static void destroy() {
		if (sTts != null) {
			sTts.stop();
			sTts.shutdown();
		}
	}

	/**
	 * Get TextToSpeech object instance, useful for additional operations on TextToSpeech.
	 *
	 * @return TextToSpeech instance
	 */
	public static TextToSpeech getTts() {
		if (sTts != null) {
			return sTts;
		} else {
			return null;
		}
	}

	/**
	 * Speaks given text.
	 *
	 * @param text A text to speak
	 */
	public static void speak(String text) {
		if (sTts != null) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				sTts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
			} else {
				sTts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}
		}
	}
}