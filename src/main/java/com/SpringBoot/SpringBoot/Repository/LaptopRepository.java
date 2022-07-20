package com.SpringBoot.SpringBoot.Repository;

import com.SpringBoot.SpringBoot.Entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Long >{

}
