package com.cmpt.a2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt.a2.models.Student;
import com.cmpt.a2.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {  
    
    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/student/view")
    public String getAllStudents(Model model){
        System.out.println("Getting all users");
        // get all users from database
        List<Student> students = studentRepo.findAll();
        // end of database call
        model.addAttribute("stu", students);
        return "student/showAll";
    }

    @PostMapping("/student/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {
        System.out.println("ADD student");
        String name = newStudent.get("name");
        String hairColour = newStudent.get("hairColour");
        double weight = Double.parseDouble(newStudent.getOrDefault("weight", "0")); // Default to 0 if not provided
        double height = Double.parseDouble(newStudent.getOrDefault("height", "0")); // Default to 0 if not provided
        double gpa = Double.parseDouble(newStudent.getOrDefault("gpa", "0")); // Default to 0 if not provided
        
        if(gpa>4.3)
        {gpa=4.3;}

        if(weight<0)
        {weight=0;}

        if(height<0)
        {height=0;}

        if(gpa<0)
        {gpa=0;}



        
        Student student = new Student(name, hairColour, weight, height, gpa);
        studentRepo.save(student);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "redirect:/student/view";
    }
    

    @PostMapping("/student/delete")
public String deleteStudent(@RequestParam("uid") int uid, HttpServletResponse response) {
    System.out.println("Deleting student with UID: " + uid);
    // Check if a student with the given UID exists
    boolean exists = studentRepo.existsById(uid);
    if (exists) {
        // If exists, delete the student
        studentRepo.deleteById(uid);
        response.setStatus(HttpServletResponse.SC_OK);
        return "redirect:/student/view";
    } else {
        // If the student does not exist, set HTTP response status to 404 Not Found
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "student/student/view";
    }
}


@GetMapping("/student/edit")
public String editStudent(@RequestParam("uid") int uid, Model model) {
    Student student = studentRepo.findById(uid).orElse(null);
    if (student != null) {
        model.addAttribute("student", student);
        System.out.println("Submitted hair color: " + student.getHairColour());
        return "student/editStudent";
    } else {
        // Redirect or handle the case where the student is not found
        return "student/student/view";
    }
}

@PostMapping("/student/update")
public String updateStudent(@ModelAttribute Student student) {
    studentRepo.save(student); // Saves the changes to the student
    return "redirect:/student/view"; // Redirect back to the student listing page
}


}


