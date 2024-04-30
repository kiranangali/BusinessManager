package com.remodelAi.Business_Manger.Repository;

import com.remodelAi.Business_Manger.Model.BusinessManagerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface BusinessManagerRepository extends JpaRepository<BusinessManagerDTO, Long> {
    // Add custom query methods if needed
}