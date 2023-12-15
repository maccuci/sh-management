package com.maccuci.sh.service;

import com.maccuci.sh.enums.StatusModel;
import com.maccuci.sh.models.Model;
import com.maccuci.sh.repository.ModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
            if(model.isInStock()) {
                model.updateRatingStars(rs);
                modelRepository.save(model);
            }
        }
    }

    @Transactional
    public void updateStatusModel(@PathVariable Long id, StatusModel status) {
        Optional<Model> find = modelRepository.findById(id);

        if(find.isPresent()) {
            Model model = find.get();
            model.setStatus(status.name());
            modelRepository.save(model);
        }
    }

    @Transactional
    public void updateAvailableQuantity(@PathVariable Long id, boolean a, Integer quantity) {
        Optional<Model> find = modelRepository.findById(id);
        int q = 0;

        if(quantity < 0) throw new IllegalArgumentException("Quantity must be positive");
        if(find.isPresent()) {
            Model model = find.get();
            if(!a) {
                q = model.getAvailableQuantity() - quantity;
                if(q < 0) throw new IllegalArgumentException("The value of quantity is greater than the available quantity.");
                model.setAvailableQuantity(q);
            }
            q = model.getAvailableQuantity() + quantity;
            model.setAvailableQuantity(q);
            modelRepository.save(model);
        }
    }

    public Model getModelById(Long id) {
        return modelRepository.findById(id).orElse(null);
    }
    public Iterable<Model> getAllModels() {
        return modelRepository.findAll();
    }
}
