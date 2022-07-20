package com.SpringBoot.SpringBoot.Controller;

import com.SpringBoot.SpringBoot.Entities.Laptop;
import com.SpringBoot.SpringBoot.Repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

   @Value("${app.message}")
   String message;

   private final Logger log = LoggerFactory.getLogger(LaptopController.class);

   private final LaptopRepository laptopRepository;

   public LaptopController(LaptopRepository laptoprepository) {
      this.laptopRepository = laptoprepository;
   }

   /**
    * http://localhost:9000/laptops
    * @return
    */
   @GetMapping("/laptops")
   @ApiOperation("Buscar todos los laptops")
   public List<Laptop> findAll(){
      System.out.println(message);
      return laptopRepository.findAll();
   }

   /**
    * Request
    * Reponse
    * @param id
    * @return
    */
   @GetMapping("/laptops/{id}")
   @ApiOperation("Buscar laptop por id")
   public ResponseEntity<Laptop>  findById(@PathVariable Long id){

      Optional<Laptop> laptopOption = laptopRepository.findById(id);

      return laptopOption.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

   }


   /**
    * crear laptop
    *
    * @param laptop
    * @return
    */
   @PostMapping("/laptops")
   @ApiOperation("Crear laptop por id")
   public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop){
      if(laptop.getId() != null){
         log.warn("trying to create a laptop with id");
         return ResponseEntity.badRequest().build();
      }
      Laptop result = laptopRepository.save(laptop);
      return ResponseEntity.ok(result);
   }


   /**
    * actualizar libro existente
    */
   @PutMapping("/laptops/{id}")
   @ApiOperation("Actualizar laptop por id")
   public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop){
      if(laptop.getId() == null){
         log.warn("Trying to update a non existent laptop");
         return ResponseEntity.badRequest().build();
      }
      if(!laptopRepository.existsById(laptop.getId())){
         log.warn("Trying to update a non existent laptop");
         return ResponseEntity.notFound().build();
      }
      Laptop result = laptopRepository.save(laptop);
      return ResponseEntity.ok(result);
   }

   /**
    * Borrar un libro
    */

   @DeleteMapping("/laptops/{id}")
   @ApiOperation("Borrar laptop por id")
   public ResponseEntity<Laptop> delete(@PathVariable Long id){

      if (!laptopRepository.existsById(id)){
         log.warn("Trying to delete a non existent laptop");
         return ResponseEntity.notFound().build();
      }

      laptopRepository.deleteById(id);

      return ResponseEntity.noContent().build();
   }

   @DeleteMapping("/laptops")
   @ApiOperation("Borrar todos los laptops")
   public ResponseEntity<Laptop> deleteAll(){
      log.info("REST Request for delete all books");
      laptopRepository.deleteAll();
      return ResponseEntity.noContent().build();
   }
}
