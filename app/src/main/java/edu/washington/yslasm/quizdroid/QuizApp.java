package edu.washington.yslasm.quizdroid;

import android.util.Log;

import java.util.ArrayList;


public class QuizApp extends android.app.Application {
    public static ArrayList<String> MathTopic = new ArrayList<String>();
    public static ArrayList<String> PhysicsTopic = new ArrayList<String>();
    public static ArrayList<String> MarvelTopic = new ArrayList<String>();
    public static ArrayList<String> MathQuestions = new ArrayList<String>();
    public static ArrayList<String> PhysicsQuestions = new ArrayList<String>();
    public static ArrayList<String> MarvelQuestions = new ArrayList<String>();
    public static ArrayList<String> MarvelAnswers = new ArrayList<String>();
    public static ArrayList<String> MathAnswers = new ArrayList<String>();
    public static ArrayList<String> PhysicsAnswers = new ArrayList<String>();
    public static ArrayList<String> MathCorrect = new ArrayList<String>();
    public static ArrayList<String> PhysicsCorrect = new ArrayList<String>();
    public static ArrayList<String> MarvelCorrect = new ArrayList<String>();
    public final String TAG = "QuizApp";

    public  QuizApp() {
        Log.i(TAG, "QuizApp object being run");
    }

    public ArrayList<String> MathTopic() {
        MathTopic.add("Math");
        MathTopic.add("The Power of Numbers");
        MathTopic.add("Mathematics (from Greek μάθημα máthēma, “knowledge, study," +
                " learning”) is the study of topics such as quantity (numbers), structure, space, and change.");
        return MathTopic;

    }
    public ArrayList<String> PhysicsTopic() {
        PhysicsTopic.add("Physics");
        PhysicsTopic.add("Formulas and Laws of Motion");
        PhysicsTopic.add("Physics  is the natural science that involves the study of matter and its motion through space " +
                "and time, along with related concepts such as energy and force.");
        return PhysicsTopic;

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
        MathQuestions.add("What is 2 + 2?");
        MathQuestions.add("What is the square root of 49?");
        MathQuestions.add("What is 4 x 4?");
        return MathQuestions;
    }

    public ArrayList<String> getPhysicsQuestions() {
        PhysicsQuestions.add("If an object is moving, then the magnitude of its ____ cannot be zero.");
        PhysicsQuestions.add("The speedometer in your car tells you the ____ of your car");
        PhysicsQuestions.add("What is the formula for force?");
        return PhysicsQuestions;
    }

    public ArrayList<String> getMarvelQuestions() {
        MarvelQuestions.add("What is Spiderman's real name");
        MarvelQuestions.add("Who was the villian in the Avengers movie?");
        MarvelQuestions.add("Who is not a member of the X-Men?");
        return MarvelQuestions;
    }

    public ArrayList<String> getMathAnswers() {
        //answers for 1st question
        MathAnswers.add("1");
        MathAnswers.add("2");
        MathAnswers.add("3");
        MathAnswers.add("4");

        //answers for 2nd question
        MathAnswers.add("20");
        MathAnswers.add("7");
        MathAnswers.add("3");
        MathAnswers.add("100");

        //answers for 3rd question
        MathAnswers.add("16");
        MathAnswers.add("8");
        MathAnswers.add("44");
        MathAnswers.add("12");

        return MathAnswers;
    }

    public ArrayList<String> getPhysicsAnswers() {
        //answers for 1st question
        PhysicsAnswers.add("speed");
        PhysicsAnswers.add("velocity");
        PhysicsAnswers.add("acceleration");
        PhysicsAnswers.add("speed and velocity");

        //answers for 2nd question
        PhysicsAnswers.add("acceleration");
        PhysicsAnswers.add("average speed");
        PhysicsAnswers.add("instantaneous speed");
        PhysicsAnswers.add("velocity");

        //answers for 3rd question
        PhysicsAnswers.add("F = sin/cos");
        PhysicsAnswers.add("F = ma");
        PhysicsAnswers.add("F = mv");
        PhysicsAnswers.add("F = w x r");

        return PhysicsAnswers;
    }

    public ArrayList<String> getMarvelAnswers() {
        //answers for 1st question
        MarvelAnswers.add("Peter Parker");
        MarvelAnswers.add("Harry Osbourne");
        MarvelAnswers.add("Tony Stark");
        MarvelAnswers.add("Bruce Banner");

        //answers for 2nd question
        MarvelAnswers.add("Venom");
        MarvelAnswers.add("Red Skull");
        MarvelAnswers.add("Loki");
        MarvelAnswers.add("Doctor Doom");

        //answers for 3rd question
        MarvelAnswers.add("Wolverine");
        MarvelAnswers.add("Cyclops");
        MarvelAnswers.add("Storm");
        MarvelAnswers.add("Ted Neward");

        return MarvelAnswers;
    }

    public ArrayList<String> getMathCorrect() {
        MathCorrect.add("4");
        MathCorrect.add("7");
        MathCorrect.add("16");
        return MathCorrect;
    }

    public ArrayList<String> getPhysicsCorrect() {
        PhysicsCorrect.add("speed and velocity");
        PhysicsCorrect.add("instantaneous speed");
        PhysicsCorrect.add("F = ma");
        return PhysicsCorrect;
    }

    public ArrayList<String> getMarvelCorrect() {
        MarvelCorrect.add("Peter Parker");
        MarvelCorrect.add("Loki");
        MarvelCorrect.add("Ted Neward");
        return MarvelCorrect;
    }
}
