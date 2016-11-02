package com.example.hosneara.project1;

/**
 * Created by hosneara on 11/2/16.
 */

public class SubmissionCheck {
    private int state = 0;
    private String submissionTime;
    SubmissionCheck(String submissionTime)
    {
        this.submissionTime = submissionTime;
    }
    public int getState()
    {
        return state;
    }
    public void setState(int state)
    {
        this.state = state;
        notifyObserver();

    }
    public void notifyObserver()
    {

    }
}
