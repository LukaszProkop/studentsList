package com.epam.homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.epam.homework.Randomizer.randomName;
import static com.epam.homework.Randomizer.randomPoints;

public class Generator {

    public void csvGenerator(List<PreAcademyStudent> list, String fileName){
        try(PrintWriter pw = new PrintWriter(fileName);
        ) {
            listForCsv(list, fileName).forEach(pw::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> listForCsv(List<PreAcademyStudent> list, String fileName) {
        List<String> generateCSV = new LinkedList<>();
        if(fileName.equals("studentListInput.csv")) {
            generateCSV.add("Name,Quizzes,Tasks,Activity");
            list.forEach(i -> generateCSV.add(i.name() + "," + i.quizzes() + "," + i.tasks() + "," + i.activity()));
        }
        else if (fileName.equals("studentList.csv")){
            generateCSV.add("Name,Quizzes,Tasks,Activity,Total");
            list.forEach(i -> generateCSV.add(i.name() + "," + i.quizzes() +
                    "," + i.tasks() + "," + i.activity() + "," + i.totalNumberOfPoints()));
        }
        return generateCSV;
    }

    public void randomStudentsCsvGenerator(int numberOfStudents) {
        List<PreAcademyStudent> students = new LinkedList<>();
        if (numberOfStudents > 0)
            IntStream.rangeClosed(1, numberOfStudents)
                    .forEach(i -> students.add(new PreAcademyStudent(randomName() + " " + randomName(),
                            new Quizzes(randomPoints()), new Tasks(randomPoints()),
                            new Activity(randomPoints()))));
        csvGenerator(students, "studentListInput.csv");
    }
}
