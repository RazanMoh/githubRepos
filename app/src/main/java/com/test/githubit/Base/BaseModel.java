package com.test.githubit.Base;

public abstract class BaseModel<T> implements BaseActivityMVP.Model<T> {

    private long timestamp;
    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    public BaseModel() {
        this.timestamp = System.currentTimeMillis();
    }

/*    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }
    */

}
