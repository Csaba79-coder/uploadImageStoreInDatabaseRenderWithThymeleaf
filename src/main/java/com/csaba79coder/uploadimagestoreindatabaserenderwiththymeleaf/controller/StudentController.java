package com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.controller;

import com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.model.Student;
import com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String home(Model model) {
        List<Student> list =  service.getAllStudent();
        model.addAttribute("list", list);
        return "index";
    }
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file,  Model model) throws IOException {
        Student student = new Student();
        String fileName = file.getOriginalFilename();
        student.setProfilePicture(fileName);
        student.setContent(file.getBytes());
        student.setSize(file.getSize());
        service.createStudent(student);
        model.addAttribute("success", "File Uploaded Successfully!!!");
        return "index";

    }

    @GetMapping("/downloadfile")
    public void downloadFile(@Param("id") Long id , Model model, HttpServletResponse response) throws IOException {
        Optional<Student> temp = service.findStudentById(id);
        if(temp.isPresent()) {
            Student student = temp.get();
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = "+student.getProfilePicture();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(student.getContent());
            outputStream.close();
        }
    }

    @GetMapping("/image")
    public void showImage(@Param("id") Long id, HttpServletResponse response, Optional<Student> student)
            throws ServletException, IOException {

        student = service.findStudentById(id);
        response.setContentType("image/jpeg");
        //response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        response.getOutputStream().write(student.get().getContent());
        response.getOutputStream().close();
    }
}
