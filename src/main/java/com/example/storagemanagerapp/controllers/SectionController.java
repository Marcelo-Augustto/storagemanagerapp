package com.example.storagemanagerapp.controllers;

import com.example.storagemanagerapp.models.Product;
import com.example.storagemanagerapp.models.Section;
import com.example.storagemanagerapp.repository.ProductRepository;
import com.example.storagemanagerapp.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SectionController {

    @Autowired
    private SectionRepository sr;

    @Autowired
    private ProductRepository pr;

    @RequestMapping(value = "/RegisterSection", method = RequestMethod.GET)
    public String form() {
        return "section/formSection";
    }

    @RequestMapping(value = "/RegisterSection", method = RequestMethod.POST)
    public String formPost(@Valid Section section, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){
            attributes.addFlashAttribute("message", "All fields must be filled");
            return "redirect:/RegisterSection";
        }
        sr.save(section);
        attributes.addFlashAttribute("message", "Section has been successfully registered");
        return "redirect:/RegisterSection";
    }

    @RequestMapping("/sections")
    public ModelAndView getSections() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Section> sections = sr.findAll();
        mv.addObject("sections", sections);
        return mv;
    }

    @RequestMapping("/deleteSection")
    public String deleteSection(long id) {
        Section section = sr.findById(id);
        sr.delete(section);
        return "redirect:/sections";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView sectionDetails(@PathVariable("id") long id) {
        Section section = sr.findById(id);
        ModelAndView mv = new ModelAndView("section/sectionDetails");
        mv.addObject("section", section);

        Iterable<Product> products = pr.findBySection(section);
        mv.addObject("products", products);

        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String sectionDetailsPost(@PathVariable("id") long id, @Valid Product product, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "All fields must be filled");
            return "redirect:/{id}";
        }
        Section section = sr.findById(id);
        product.setSection(section);
        pr.save(product);
        attributes.addFlashAttribute("message", "Product has been successfully registered");
        return "redirect:/{id}";
    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(String serial) {
        Product product = pr.findBySerial(serial);
        pr.delete(product);

        Section section = product.getSection();
        long idLong = section.getId();
        String id = "" + idLong;
        return "redirect:/" + id;
    }
}
