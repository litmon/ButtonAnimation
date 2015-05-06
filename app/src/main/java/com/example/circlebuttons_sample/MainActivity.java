package com.example.circlebuttons_sample;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity {

	View view;
	Button[] buttons = new Button[5];

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		view = findViewById(R.id.checkBox1);

		for (int i = 0; i < buttons.length; i++) {
			int id = getResources().getIdentifier("button" + (i + 1), "id", getPackageName());
			buttons[i] = (Button) findViewById(id);
			buttons[i].setVisibility(View.VISIBLE);
			buttons[i].setAlpha(0f);
		}

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("trigger clicked.");

				final CompoundButton radio = (CompoundButton) v;
				float width = v.getWidth();

				for (int i = 0; i < buttons.length; i++) {
					PropertyValuesHolder alpha;
					PropertyValuesHolder trans;

					if (radio.isChecked()) {
						alpha = PropertyValuesHolder.ofFloat("alpha", buttons[i].getAlpha(), 1f);
						trans = PropertyValuesHolder.ofFloat("translationX", buttons[i].getTranslationX(), width);
					} else {
						alpha = PropertyValuesHolder.ofFloat("alpha", buttons[i].getAlpha(), 0f);
						trans = PropertyValuesHolder.ofFloat("translationX", buttons[i].getTranslationX(), 0f);
					}

					ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(buttons[i], alpha, trans);
					anim.setDuration(300);
					anim.start();

					width += buttons[i].getWidth();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
