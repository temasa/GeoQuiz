package com.bignerdranch.android.geoquiz;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class QuizActivity extends Activity
{
	
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	
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
					Toast.makeText(QuizActivity.this,
					R.string.correct_toast,
					Toast.LENGTH_SHORT).show();
				}
			}
		);
		
		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(QuizActivity.this,
					R.string.incorrect_toast,
					Toast.LENGTH_SHORT).show();
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
