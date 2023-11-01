package com.maccuci.sh.service;

import com.maccuci.sh.models.Model;
import com.maccuci.sh.repository.ModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    @Transactional
    public void updateRatingStars(@PathVariable Long id, Integer rs) {
        Optional<Model> find = modelRepository.findById(id);
        if(find.isPresent()) {
            Model model = find.get();
            model.updateRatingStars(rs);
            modelRepository.save(model);
        }
    }

    public Iterable<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(Long id) {
        return modelRepository.findById(id).orElse(null);
    }
}
