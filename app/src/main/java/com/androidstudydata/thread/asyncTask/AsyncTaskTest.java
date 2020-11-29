package com.androidstudydata.thread.asyncTask;

import android.os.AsyncTask;

/**
 * Author：Mapogo
 * Date：2020/11/25
 * Note：
 */
class AsyncTaskTest extends AsyncTask<String, Integer, String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }
}
