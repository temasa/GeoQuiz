package com.bignerdranch.android.geoquiz;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class CheatActivity extends Activity
{
	
	public static final String EXTRA_ANSWER_IS_TRUE = 
		"com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = 
		"com.bignerdranch.android.geoquiz.answer_shown";
		
	private boolean mAnswerIsTrue;
	
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	
	private void setAnswerShowResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		setAnswerShowResult(false);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mAnswerIsTrue) {
						mAnswerTextView.setText(R.string.true_button);
					}
					else {
						mAnswerTextView.setText(R.string.false_button);
					}
					setAnswerShowResult(true);
				}
			}
		);
	}
}
