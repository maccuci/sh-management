package com.maccuci.sh.service;

import com.maccuci.sh.enums.StatusModel;
import com.maccuci.sh.models.Model;
import com.maccuci.sh.repository.ModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
        modelRepository.findById(id).ifPresent(model -> {
            if(model.isInStock()) {
                model.updateRatingStars(rs);
                modelRepository.save(model);
            }
        });
    }

    @Transactional
    public void updateStatusModel(@PathVariable Long id, StatusModel status) {
        modelRepository.findById(id).ifPresent(model -> {
            model.setStatus(status.name());
            modelRepository.save(model);
        });
    }

    @Transactional
    public void updateAvailableQuantity(@PathVariable Long id, boolean a, Integer quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be positive");

        Model model = modelRepository.findById(id).orElseThrow(() -> new IllegalStateException("Model with id " + id + " does not exist"));
        int q = a ? model.getAvailableQuantity() + quantity : model.getAvailableQuantity() - quantity;

        if (q < 0) throw new IllegalStateException("Quantity must be positive");

        model.setAvailableQuantity(q);
        modelRepository.save(model);
    }

    public Model getModelById(Long id) {
        return modelRepository.findById(id).orElse(null);
    }
    public Iterable<Model> getAllModels() {
        return modelRepository.findAll();
    }
}
