package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final int REQUEST_CODE_HINT = 1;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private Button mHintButton;
    private TextView mIndexTextView;
    private TextView mQuestionTextView;
    private TextView mQuestionsCompleted;
    private TextView mTotalMarksTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mHintBank [] = new int [] {
            R.string.hint_australia,
            R.string.hint_oceans,
            R.string.hint_mideast,
            R.string.hint_africa,
            R.string.hint_americas,
            R.string.hint_asia };

    private int mTotalMarks = 0;
    private int mCurrentIndex = 0;
    private boolean mIsCheater;
    private boolean mIsHinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        mIndexTextView = (TextView) findViewById(R.id.index);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mTotalMarksTextView = (TextView) findViewById(R.id.total_marks);
        mQuestionsCompleted = (TextView) findViewById(R.id.questions_completed);
        mQuestionsCompleted.setText(String.valueOf(Question.mQuestionCompleted));

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                if (!mQuestionBank[mCurrentIndex].isCheated())
                    mIsCheater = false;

                mIsHinter = false;
                updateQuestion();
            }
        });

        /**
         * Test if it can go back without going next
         */
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                if (mCurrentIndex < 0)
                    mCurrentIndex = 5;

                if (!mQuestionBank[mCurrentIndex].isCheated())
                    mIsCheater = false;

                mIsHinter = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        mHintButton = (Button) findViewById(R.id.hint_button);
        mHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int hintIndex = mHintBank[mCurrentIndex];
                Intent intent = HintActivity.newIntent(QuizActivity.this, hintIndex, mCurrentIndex);
                startActivityForResult(intent, REQUEST_CODE_HINT);
            }
        });

        updateQuestion();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode != Activity.RESULT_OK)
                return;


            if (requestCode == REQUEST_CODE_CHEAT) {
                if (data == null)
                    return;

                mIsCheater = CheatActivity.wasAnswerShown(data);
            }

            if (requestCode == REQUEST_CODE_HINT) {
                if (data == null)
                    return;

                mIsHinter = HintActivity.wasHintShown(data);
            }
        }
        catch (Exception e) {
            Toast.makeText(QuizActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        int index = mCurrentIndex + 1;
        mIndexTextView.setText(index + ". ");
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        /** mIsCheater should set separately each quiz **/
        if (mIsCheater)
            mQuestionBank[mCurrentIndex].setCheat(true);

        if (!mQuestionBank[mCurrentIndex].isChecked() && !mQuestionBank[mCurrentIndex].isCheated()) {
            mQuestionsCompleted.setText(String.valueOf(++Question.mQuestionCompleted));
            mQuestionBank[mCurrentIndex].setCheck(true);
            if (userPressedTrue == answerIsTrue) {
                if (mIsHinter) {
                    messageResId = R.string.Pone_marks;
                    mTotalMarks+=1;
                }
                else {
                    messageResId = R.string.Ptwo_marks;
                    mTotalMarks+=2;
                }
            }
            else {
                if (mIsHinter) {
                    mTotalMarks-=2;
                    messageResId = R.string.Mtwo_marks;
                }
                else {
                    mTotalMarks-=1;
                    messageResId = R.string.Mone_marks;
                }
            }
        }
        else if (mQuestionBank[mCurrentIndex].isChecked() && !mQuestionBank[mCurrentIndex].isCheated())
            messageResId = R.string.completed_toast;
        else if (mQuestionBank[mCurrentIndex].isChecked() && mQuestionBank[mCurrentIndex].isCheated())
            messageResId = R.string.completed_toast;
        else if (!mQuestionBank[mCurrentIndex].isChecked() && mQuestionBank[mCurrentIndex].isCheated())
            messageResId = R.string.judgment_toast;


        /** Have a question checked once it's clicked */
        mTotalMarksTextView.setText(String.valueOf(mTotalMarks));
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
