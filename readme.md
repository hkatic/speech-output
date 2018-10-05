# Speech Output library by Hrvoje Katić #

## Introduction ##

Sometimes we need to use text-to-speech in our Android applications, and we're using TextToSpeech object for that. However, this requires writing a bit of boilerplate code, especially if we need to support older platforms, which is of course what we lazy developers don't like. Therefore, I've created SpeechOutput library to make it easier for myself and other Android developers.

## How to use ##

In your activity's onCreate method, write the following line:

SpeechOutput.create(this);

And when it's the time to free up resources, in onDestroy method, write the following line:

SpeechOutput.destroy();

When you want your application to speak something, use SpeechOutput.speak method. Example:

SpeechOutput.speak("The quick brown fox jumped over the lazy dog.");

Sounds easy? Yep and I think so!

All the methods in Speech class are static, so there's no need for instantiation. You can use SpeechOutput.getTts() method that will return TextToSpeech instance, allowing you to do additional work, such as setLanguage, setVoice etc.

The create method is overloaded, so you can call SpeechOutput.create with additional parameters as well. Context is always required, since that's how it is in Android my friend! However, you can also pass Locale or initialization message String if you wish. For example:

SpeechOutput.create(getApplicationContext(), Locale.UK, "Welcome to my application baby!");

Or, to use US english locale (which is default), you can write for example:

SpeechOutput.create(getApplicationContext(), "Welcome to my application baby!");

The Demo application is included so you can see for yourself how it works. I am planning to add more features from time to time, such as audio ducking, calling speak with optional QUEUE_FLUSH or QUEUE_ADD etc. So stay tuned and have fun!
