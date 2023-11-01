package com.maccuci.sh.repository;

import com.maccuci.sh.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
