package com.bignerdranch.android.geoquiz;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class QuizActivity extends Activity
{
	
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
		new TrueFalse(R.string.question_oceans, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true)
	};
	
	private int mCurrentIndex = 0;
	
	private void updateQuestion() 
	{
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue)
	{
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		if (userPressedTrue == answerIsTrue) {
			messageResId = R.string.correct_toast;
		}
		else {
			messageResId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
		
		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		
		mTrueButton = (Button) findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					checkAnswer(true);
				}
			}
		);
		
		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					checkAnswer(false);
				}
			}
		);
		
		mNextButton = (Button) findViewById(R.id.next_button);
		mNextButton.setOnClickListener( 
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
					updateQuestion();
				}
			}
		);
		
		updateQuestion();
    }
}
