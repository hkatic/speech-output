package com.hrvojekatic.speechoutputdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hrvojekatic.speechoutput.Speech;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Speech.create(this);
		Button buttonSpeak = (Button) findViewById(R.id.button_speak);
		buttonSpeak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Speech.speak("This is a test message.");
			}
		});
	}

	@Override
	protected void onDestroy() {
		Speech.destroy();
		super.onDestroy();
	}
}
