package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
