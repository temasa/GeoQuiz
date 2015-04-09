package com.bignerdranch.android.geoquiz;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class QuizActivity extends Activity
{
	
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private Button mCheatButton;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
		new TrueFalse(R.string.question_oceans, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true)
	};
	
	private int mCurrentIndex = 0;
	
	private boolean mIsCheater;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (data == null) {
			return;
		}
		
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}
	
	private void updateQuestion() 
	{
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue) 
	{
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		int messageResId = 0;
		
		if (mIsCheater) {
			messageResId = R.string.judgement_toast;
		}
		else {
			if (userPressedTrue == answerIsTrue) {
				messageResId = R.string.correct_toast;
			}
			else {
				messageResId = R.string.incorrect_toast;
			}
		}
		
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		Logger.d(this.getClass(), "onCreate()", "called");
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
					mIsCheater = false;
					updateQuestion();
				}
			}
		);
		
		mCheatButton = (Button) findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(QuizActivity.this, CheatActivity.class);
					boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
					i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
					startActivityForResult(i, 0);
				}
			}
		);
		
		if (savedInstanceState != null ) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
		}
		
		updateQuestion();
    }

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		Logger.d(this.getClass(), "onSaveInstanceState(Bundle)", "called");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		Logger.d(this.getClass(), "onStart()", "called");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Logger.d(this.getClass(), "onPause()", "called");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Logger.d(this.getClass(), "onResume()", "called");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Logger.d(this.getClass(), "onStop()", "called");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Logger.d(this.getClass(), "onDestroy()", "called");
	}
	
}
