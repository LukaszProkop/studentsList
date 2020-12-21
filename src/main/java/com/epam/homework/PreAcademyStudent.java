package com.epam.homework;

import java.util.Comparator;

public record PreAcademyStudent(String name, Quizzes quizzes, Tasks tasks, Activity activity)
        implements Comparable<PreAcademyStudent> {

    public int totalNumberOfPoints() {
        return quizzes.points() + tasks.points() + activity.points();
    }

    @Override
    public int compareTo(PreAcademyStudent o) {
        return Integer.compare(quizzes.points() + tasks.points() + activity.points(),
                o.quizzes.points() + o.tasks.points() + o.activity.points());
    }

    @Override
    public String toString() {
        return "Student " + name + ": " +
                "Quizzes = " + quizzes +
                ", Tasks = " + tasks +
                ", Activity = " + activity +
                " ==> In total = " +
                totalNumberOfPoints();
    }
}

record Quizzes(int points) {
    @Override
    public String toString() {
        return points + "";
    }
}

record Tasks(int points) {
    @Override
    public String toString() {
        return points + "";
    }
}

record Activity(int points) {
    @Override
    public String toString() {
        return points + "";
    }
}

class SortByQuizzes implements Comparator<PreAcademyStudent> {
    @Override
    public int compare(PreAcademyStudent o1, PreAcademyStudent o2) {
        return o1.quizzes().points() - o2.quizzes().points();
    }
}

class SortByTasks implements Comparator<PreAcademyStudent> {
    @Override
    public int compare(PreAcademyStudent o1, PreAcademyStudent o2) {
        return o1.tasks().points() - o2.tasks().points();
    }
}

class SortByActivity implements Comparator<PreAcademyStudent> {
    @Override
    public int compare(PreAcademyStudent o1, PreAcademyStudent o2) {
        return o1.activity().points() - o2.activity().points();
    }
}