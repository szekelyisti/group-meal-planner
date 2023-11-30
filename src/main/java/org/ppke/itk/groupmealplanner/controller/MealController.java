package org.ppke.itk.groupmealplanner.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.ppke.itk.groupmealplanner.domain.Meal;
import org.ppke.itk.groupmealplanner.repository.CustomMealRepository;
import org.ppke.itk.groupmealplanner.repository.MealRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Meal")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealRepository mealRepository;
    private final CustomMealRepository customMealRepository;

    @GetMapping("/meals")
    public List<Meal> getMeals(@RequestParam(required = false, defaultValue = "100") Integer limit,
                               @RequestParam(required = false, defaultValue = "desc") String sort) {
        log.info("Calling GET /meals endpoint");

        var sortParam = sort.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "name") : Sort.by(Sort.Direction.DESC, "name");

        return mealRepository.findAll(PageRequest.of(0, limit, sortParam)).toList();
    }

    @GetMapping(value = "/meals/{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<Meal> getMealById(@PathVariable("id") Integer id) {
        log.info("Calling GET /meals/{} endpoint", id);
        return mealRepository.findById(id);
    }

    @GetMapping(value = "/meals/{id}", produces = APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getMealByIdAsPDF(@PathVariable("id") Integer id) throws IOException {
        Optional<Meal> meal = mealRepository.findById(id);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.newLineAtOffset(25, 50);
        contentStream.showText("Meal Name: " + meal.get().getName());
        contentStream.newLineAtOffset(25, 50);
        contentStream.showText("Meal Instructions: " + meal.get().getInstruction());
        contentStream.newLineAtOffset(25, 50);
        contentStream.showText("Meal Approximated Price: " + meal.get().getApproximatedPrice());
        contentStream.endText();
        contentStream.close();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=meal-" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));
    }

    @PostMapping("/meals")
    public void createMeal(@RequestBody Meal meal) {
        mealRepository.saveAndFlush(meal);
    }

    @PutMapping("/meals/{id}")
    public Meal updateMeal(@PathVariable("id") Integer id, @RequestBody(required = false) Meal updateMeal) {
        log.info("Calling PUT /meals endpoint");
        return customMealRepository.updateMeal(id, updateMeal.getName(), updateMeal.getInstruction(), updateMeal.getApproximatedPrice());
    }

    @DeleteMapping("/meals/{id}")
    public void deleteMeal(@PathVariable("id") Integer id) {
        mealRepository.deleteById(id);
    }
}
