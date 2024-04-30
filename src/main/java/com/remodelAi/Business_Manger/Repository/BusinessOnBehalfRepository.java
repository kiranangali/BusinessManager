package com.remodelAi.Business_Manger.Repository;

import com.remodelAi.Business_Manger.Model.BusinessOnBehalf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessOnBehalfRepository extends JpaRepository<BusinessOnBehalf, Long> {
    // Custom query methods if needed
}
