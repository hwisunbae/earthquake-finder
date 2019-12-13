package com.bignerdranch.android.geoquiz;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    static int mQuestionCompleted = 0;
    private boolean mIsChecked;
    private boolean mIsCheated;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setCheck(boolean checked) {
        this.mIsChecked = checked;
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    public void setCheat(boolean cheat) {
        this.mIsCheated = cheat;
    }

    public boolean isCheated() {
        return this.mIsCheated;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
