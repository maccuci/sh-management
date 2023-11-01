package com.maccuci.sh;

import com.maccuci.sh.models.Model;
import com.maccuci.sh.models.ModelType;
import com.maccuci.sh.service.ModelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class HardwareManagementApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HardwareManagementApplication.class, args);

        ModelService modelService = context.getBean(ModelService.class);

        Model model = new Model("Ryzen 5 5600X", "A new CPU focused on performance for gaming.", ModelType.CPU.name(), "ryzen.com", 105.0F, 105.0F, 105.0F, new Date(), new Date(), new Date(), 4);
        modelService.addModel(model);
    }
}

