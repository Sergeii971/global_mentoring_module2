package com.os.module2.task7;

import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {
    private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;

    // Processing window size; should be odd.
    private int mBlurWidth = 15;

    public ForkBlur(int[] src, int start, int length, int[] dst) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
    }

    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; index++) {
            // Calculate average.
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0),
                        mSource.length - 1);
                int pixel = mSource[mindex];
                rt += (float) ((pixel & 0x00ff0000) >> 16)
                        / mBlurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8)
                        / mBlurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0)
                        / mBlurWidth;
            }

            // Reassemble destination pixel.
            int dpixel = (0xff000000) |
                    (((int) rt) << 16) |
                    (((int) gt) << 8) |
                    (((int) bt) << 0);
            mDestination[index] = dpixel;
        }
    }

    protected static int sThreshold = 100000;

    protected void compute() {
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }

        int split = mLength / 2;

        invokeAll(new ForkBlur(mSource, mStart, split, mDestination),
                new ForkBlur(mSource, mStart + split, mLength - split,
                        mDestination));
    }

    public int[] getmSource() {
        return mSource;
    }

    public void setmSource(int[] mSource) {
        this.mSource = mSource;
    }

    public int getmStart() {
        return mStart;
    }

    public void setmStart(int mStart) {
        this.mStart = mStart;
    }

    public int getmLength() {
        return mLength;
    }

    public void setmLength(int mLength) {
        this.mLength = mLength;
    }

    public int[] getmDestination() {
        return mDestination;
    }

    public void setmDestination(int[] mDestination) {
        this.mDestination = mDestination;
    }

    public int getmBlurWidth() {
        return mBlurWidth;
    }

    public void setmBlurWidth(int mBlurWidth) {
        this.mBlurWidth = mBlurWidth;
    }

    public static int getsThreshold() {
        return sThreshold;
    }

    public static void setsThreshold(int sThreshold) {
        ForkBlur.sThreshold = sThreshold;
    }
}
