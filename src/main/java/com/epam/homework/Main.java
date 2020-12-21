package com.epam.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

    /*
    TODO:
        1. Generate input csv
        2. Load input csv file
        3. grades PreAcademyStudents using
	    4. total ordering per all points they have (via Comparable, total as in sum of all points)
	    5. orderings per quizzes, tasks and lecture activity (via Comparators)
	    6. optional: use record for PreAcademyStudent
	    7 Save sorted list in .csv file
    */

public class Main {

    private final File csvLoader = new File("studentListInput.csv");
    private final StringBuilder studentList = new StringBuilder();
    private final List<PreAcademyStudent> list = new LinkedList<>();

    public static void main(String[] args) {
        Main main = new Main();
        new Generator().randomStudentsCsvGenerator(10);
        main.studentList();
        new Generator().csvGenerator(main.list, "studentList.csv");
    }

    private void studentList() {
        loadCsv();
        String[] studentsArr = studentList.toString().split("\n");
        String[] arrForList = Arrays.copyOfRange(studentsArr, 1, studentsArr.length);
        Arrays.stream(arrForList).map(i -> i.split(","))
                .forEach(i -> list.add(new PreAcademyStudent(i[0], new Quizzes(Integer.parseInt(i[1])),
                        new Tasks(Integer.parseInt(i[2])), new Activity(Integer.parseInt(i[3])))));
        sortList();
        list.forEach(System.out::println);
    }

    private void loadCsv() {
        try {
            Scanner csvData = new Scanner(csvLoader);
            while (csvData.hasNextLine()) {
                studentList.append(csvData.nextLine()).append('\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sortList() {
        list.sort(new SortByActivity());
        list.sort(new SortByTasks());
        list.sort(new SortByQuizzes());
        list.sort(PreAcademyStudent::compareTo);
    }
}
