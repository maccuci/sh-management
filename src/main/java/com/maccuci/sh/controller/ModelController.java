package com.maccuci.sh.controller;

import com.maccuci.sh.models.Model;
import com.maccuci.sh.service.ModelService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<Model> createModel(@RequestBody Model modelRequest) {
        Model newModel = modelService.addModel(modelRequest);

        if (newModel != null) {
            return new ResponseEntity<>(newModel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public void updateRatingStars(@RequestBody Model modelRequest, Integer rs) {
        Model find = modelService.getModelById(modelRequest.getId());
        if(find != null) {
            find.updateRatingStars(rs);
        }
    }
}
