package com.epam.homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.epam.homework.Randomizer.randomName;
import static com.epam.homework.Randomizer.randomPoints;

    /*
    TODO:
        1. grades PreAcademyStudents using
	    2. total ordering per all points they have (via Comparable, total as in sum of all points)
	    3. orderings per quizzes, tasks and lecture activity (via Comparators)
	    4. optional: use record for PreAcademyStudent
    */

public class Main {
    public static void main(String[] args) {
        List<PreAcademyStudent> list = randomStudentsList(10);

        List<String> generateCSV = new LinkedList<>();
        generateCSV.add("Name,Quizzes,Tasks,Activity,Total");
        list.forEach(i-> generateCSV.add(i.name() + "," + i.quizzes() + "," + i.tasks() + "," + i.activity() + "," + i.totalNumberOfPoints()));

        try(PrintWriter pw = new PrintWriter("homework.csv")) {
            generateCSV.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.sort(new SortByActivity());
        list.sort(new SortByTasks());
        list.sort(new SortByQuizzes());
        list.sort(PreAcademyStudent::compareTo);

        List<PreAcademyStudent> orderedList = list;

        orderedList.forEach(System.out::println);
    }

    public static List<PreAcademyStudent> randomStudentsList(int numberOfStudents) {
        List<PreAcademyStudent> students = new LinkedList<>();
        if (numberOfStudents > 0)
            IntStream.rangeClosed(1, numberOfStudents)
                    .forEach(i -> students.add(new PreAcademyStudent(randomName() + " " + randomName(),
                            new Quizzes(randomPoints()), new Tasks(randomPoints()),
                            new Activity(randomPoints()))));
        return students;
    }
}
