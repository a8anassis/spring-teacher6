package gr.aueb.cf.teacherapp.controller;

import gr.aueb.cf.teacherapp.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.teacherapp.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.teacherapp.dto.TeacherInsertDTO;
import gr.aueb.cf.teacherapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.teacherapp.mapper.Mapper;
import gr.aueb.cf.teacherapp.model.Teacher;
import gr.aueb.cf.teacherapp.service.RegionService;
import gr.aueb.cf.teacherapp.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.parser.Entity;

@Controller
@RequestMapping("/school")
@RequiredArgsConstructor
public class TeacherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
    private final TeacherService teacherService;
    private final RegionService regionService;
    private final Mapper mapper;

    @GetMapping("/teachers/insert")
    public String getTeacherForm(Model model) {
        model.addAttribute("teacherInsertDTO", new TeacherInsertDTO());
        model.addAttribute("regions", regionService.findAllRegions());
        return "teacher-form";
    }

    public String saveTeacher(@Valid @ModelAttribute("teacherInsertDTO") TeacherInsertDTO teacherInsertDTO,
                              BindingResult bindingResult,
                              Model model) {
        Teacher savedTeacher;
        if (bindingResult.hasErrors()) {
            return "teacher-form";
        }

        try {
            savedTeacher = teacherService.saveTeacher(teacherInsertDTO);
            LOGGER.info("Teacher with id {} inserted", savedTeacher.getId());
            //return "success";
        } catch (EntityAlreadyExistsException | EntityInvalidArgumentException e) {
            LOGGER.error("Teacher with vat {} not inserted", teacherInsertDTO.getVat());
            model.addAttribute("errorMessage", e.getMessage());
            return "teacher-form";
        }

        //return "redirect:/teachers";

        TeacherReadOnlyDTO teacherReadOnlyDTO = mapper.mapToTeacherReadOnlyDTO(savedTeacher);
        model.addAttribute("teacher", savedTeacher);
        return "success";






    }



}
