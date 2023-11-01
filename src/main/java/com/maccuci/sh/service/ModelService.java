package com.maccuci.sh.service;

import com.maccuci.sh.models.Model;
import com.maccuci.sh.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Model addModel(Model model) {
        return modelRepository.save(model);
    }

    public Iterable<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(Long id) {
        return modelRepository.findById(id).orElse(null);
    }
}
