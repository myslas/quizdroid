package edu.washington.yslasm.quizdroid;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            URL url = new URL("http://tednewardsandbox.site44.com/questions.json");

            //create the new connection

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //set up some things on the connection

            urlConnection.setRequestMethod("GET");

            urlConnection.setDoOutput(true);

            //and connect!

            urlConnection.connect();

            //set the path where we want to save the file

            //in this case, going to save it on the root directory of the

            //sd card.

            File SDCardRoot = new File("/Users/baseballkid-88/AndroidStudioProjects/quizdroid/app/src/main/java/edu/washington/yslasm/quizdroid");

            //create a new file, specifying the path, and the filename

            //which we want to save the file as.

            File file = new File(SDCardRoot, "quizdata.json");

            //this will be used to write the downloaded data into the file we created

            FileOutputStream fileOutput = new FileOutputStream(file);

            //this will be used in reading the data from the internet

            InputStream inputStream = urlConnection.getInputStream();

            //this is the total size of the file

            int totalSize = urlConnection.getContentLength();

            //variable to store total downloaded bytes

            int downloadedSize = 0;

            //create a buffer...

            byte[] buffer = new byte[1024];

            int bufferLength = 0; //used to store a temporary size of the buffer

            //now, read through the input buffer and write the contents to the file

            while ((bufferLength = inputStream.read(buffer)) > 0)

            {

                //add the data in the buffer to the file in the file output stream (the file on the sd card

                fileOutput.write(buffer, 0, bufferLength);

                //add up the size so we know how much is downloaded

                downloadedSize += bufferLength;

                int progress = (int) (downloadedSize * 100 / totalSize);

                //this is where you would do something to report the prgress, like this maybe

                //updateProgress(downloadedSize, totalSize);

            }

            //close the output stream when done

            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
