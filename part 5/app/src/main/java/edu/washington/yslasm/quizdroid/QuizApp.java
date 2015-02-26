package edu.washington.yslasm.quizdroid;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class QuizApp extends android.app.Application {


    public static ArrayList<String> Topics = new ArrayList<String>();
    public static ArrayList<String> Descriptions = new ArrayList<String>();
    public static ArrayList<String> MarvelTopic = new ArrayList<String>();
    public static ArrayList<String> MathQuestions = new ArrayList<String>();
    public static ArrayList<String> ScienceQuestions = new ArrayList<String>();
    public static ArrayList<String> MarvelQuestions = new ArrayList<String>();
    public static ArrayList<String> MarvelAnswers = new ArrayList<String>();
    public static ArrayList<String> MathAnswers = new ArrayList<String>();
    public static ArrayList<String> ScienceAnswers = new ArrayList<String>();
    public static ArrayList<String> MathCorrect = new ArrayList<String>();
    public static ArrayList<String> ScienceCorrect = new ArrayList<String>();
    public static ArrayList<String> MarvelCorrect = new ArrayList<String>();
    public final String TAG = "QuizApp";

    public  QuizApp(Context context)  {
        Log.i(TAG, "QuizApp object being run");
        try {
            gatherData(context);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void gatherData(Context context) throws IOException {
        if (Topics.isEmpty()) {
            Log.i(TAG, "entered gatherData");
            InputStream in = context.getAssets().open("quizdata.json");
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.beginArray();
            String topic = "";
            int count = 0;
            int correct = 0;
            while (reader.hasNext()) {
                Log.i(TAG, "entered JSON");
                reader.beginObject();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("title")) {
                        topic = reader.nextString();
                        Topics.add(topic);
                    }
                    if (name.equals("desc")) {
                        Descriptions.add(reader.nextString());
                    }
                    if (name.equals("questions")) {
                        reader.beginArray();
                        while (reader.hasNext()) {
                            count++;
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String tag = reader.nextName();
                                if (tag.equals("text")) {
                                    if (topic.equals("Science!")) {
                                        ScienceQuestions.add(reader.nextString());
                                    } else if (topic.equals("Marvel Super Heroes")) {
                                        MarvelQuestions.add(reader.nextString());
                                    } else {
                                        MathQuestions.add(reader.nextString());
                                    }
                                }
                                if (tag.equals("answer")) {
                                    correct = Integer.parseInt(reader.nextString());
                                }
                                if (tag.equals("answers")) {
                                    ArrayList<String> temp = new ArrayList<String>();
                                    reader.beginArray();
                                    while (reader.hasNext()) {
                                        String answer = reader.nextString();
                                        if (topic.equals("Science!")) {
                                            ScienceAnswers.add(answer);
                                        } else if (topic.equals("Marvel Super Heroes")) {
                                            MarvelAnswers.add(answer);
                                        } else {
                                            MathAnswers.add(answer);
                                        }
                                        temp.add(answer);
                                    }
                                    if (topic.equals("Science!")) {
                                        ScienceCorrect.add(temp.get(correct - 1));
                                    } else if (topic.equals("Marvel Super Heroes")) {
                                        MarvelCorrect.add(temp.get(correct - 1));
                                    } else {
                                        MathCorrect.add(temp.get(correct - 1));
                                    }
                                    reader.endArray();

                                }

                            }
                            reader.endObject();

                        }
                        reader.endArray();

                    }
                }
                reader.endObject();
            }
            reader.endArray();
            reader.close();
        }
        Log.i(TAG, "" + MarvelAnswers);
        Log.i(TAG, "" + MarvelCorrect);
    }

    public ArrayList<String> Topics() {

        return Topics;

    }
    public ArrayList<String> Descriptions() {

        return Descriptions;

    }
    public ArrayList<String> MarvelTopic() {
        MarvelTopic.add("Marvel Super Heroes");
        MarvelTopic.add("Who doesn't love Superheroes");
        MarvelTopic.add("The Marvel Universe is the shared fictional " +
                "universe where stories in most comic book titles and other media published " +
                "by Marvel Entertainment take place, including those featuring Marvel's most " +
                "familiar characters, such as Spider-Man, the X-Men, the Fantastic Four and the Avengers.");
        return MarvelTopic;

    }
    public ArrayList<String> getMathQuestions() {

        return MathQuestions;
    }

    public ArrayList<String> getScienceQuestions() {

        return ScienceQuestions;
    }

    public ArrayList<String> getMarvelQuestions() {

        return MarvelQuestions;
    }

    public ArrayList<String> getMathAnswers() {


        return MathAnswers;
    }

    public ArrayList<String> getScienceAnswers() {


        return ScienceAnswers;
    }

    public ArrayList<String> getMarvelAnswers() {

        return MarvelAnswers;
    }

    public ArrayList<String> getMathCorrect() {

        return MathCorrect;
    }

    public ArrayList<String> getScienceCorrect() {

        return ScienceCorrect;
    }

    public ArrayList<String> getMarvelCorrect() {

        return MarvelCorrect;
    }
}
