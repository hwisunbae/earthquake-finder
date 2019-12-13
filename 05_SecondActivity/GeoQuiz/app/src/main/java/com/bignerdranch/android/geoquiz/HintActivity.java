package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private static final String EXTRA_HINT_SHOWN =
            "com.bignerdranch.android.geoquiz.hint_shown";
    private static final String EXTRA_HINT_INDEX =
            "com.bignerdranch.android.geoquiz.hint_index";

    private int mHintIndex;
    private static int index = 0;

    private TextView mIndex;
    private TextView mHintTextView;
    private Button mShowHintButton;
    private Button mExitButton;

    public static Intent newIntent(Context packageContext, int hintIndex, int curIndex) {
        Intent intent = new Intent(packageContext, HintActivity.class);
        intent.putExtra(EXTRA_HINT_INDEX, hintIndex);
        index = curIndex + 1;
        return intent;
    }

    public static boolean wasHintShown(Intent result) {
        return result.getBooleanExtra(EXTRA_HINT_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mHintIndex = getIntent().getIntExtra(EXTRA_HINT_INDEX, 0);

        mIndex = (TextView) findViewById(R.id.index);
        mHintTextView = (TextView) findViewById(R.id.hint_text_view);
        mShowHintButton = (Button) findViewById(R.id.show_hint_button);
        mExitButton = (Button) findViewById(R.id.exit);

        mHintTextView.setText("Hint");
        mShowHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndex.setText(index + ". ");
                mHintTextView.setText(mHintIndex);
                setHintShownResult(true);
            }
        });
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setHintShownResult(boolean isHintShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_HINT_SHOWN, isHintShown);
        setResult(RESULT_OK, data);
    }
}
