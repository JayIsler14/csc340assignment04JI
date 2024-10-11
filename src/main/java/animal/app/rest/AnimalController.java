package animal.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AnimalController.java.
 * Includes all REST API endpoint mappings for the Animal object.
 */
@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;

    /**
     * Get a list of all Animals in the database.
     * http://localhost:8080/animals/all
     *
     * @return a list of Animals  objects.
     */
    @GetMapping("/all")
    public List<Animal> getAllAnimals() {
        return service.getAllAnimals();
    }

    /**
     * Get a specific Animal by Id.
     * http://localhost:8080/animals/2
     *
     * @param animalId the unique Id for a Student.
     * @return One Animal object.
     */
    @GetMapping("/{AnimalId}")
    public Animal getOneAnimal(@PathVariable int animalId) {
        return service.getAnimalById(animalId);
    }

    /**
     * Fetch all animals whose major matches the search term.
     *
     * @param species the search key.
     * @return the list of matching Animals.
     */
    @GetMapping("")
    public List<Animal> getAnimalsByClass(@RequestParam(name = "species", defaultValue = "dog") String species) {
        return service.getAnimalsBySpecies(species);
    }

    /**
     * Fetch all animals whose major matches the search term.
     *
     * @param name the search key.
     * @return the list of matching Animals that contain that name.
     */
    @GetMapping("")
    public List<Animal> getAnimalsByName(@RequestParam(name = "name", defaultValue = "cat") String name) {
        return service.getAnimalsByName(name);
    }

    /**
     * Create a new Animal entry.
     * http://localhost:8080/animals/new --data '{ "animalId": 3, "name": "newName", "scientificName": "newScientificName", "species": "newSpecies", "habitat": "newHabitat", "description": "newDescription".}'
     *
     * @param animal the new Animal object.
     * @return the updated list of Animals.
     */
    @PostMapping("/new")
    public List<Animal> addNewAnimal(@RequestBody Animal animal) {
        service.addNewAnimal(animal);
        return service.getAllAnimals();
    }

    /**
     * Update an existing Animal object.
     * http://localhost:8080/animals/update/2 --data '{ "animalId": 1, "name": "sampleUpdated", "scientificName": "sampleUpdated", "species": "sampleUpdated", "habitat": "sampleUpdated", "description": "sampleUpdated". }'
     *
     * @param animalId the unique Animal Id.
     * @param animal   the new update Animal details.
     * @return the updated Animal object.
     */
    @PutMapping("/update/{animalId}")
    public Animal updateAnimal(@PathVariable int animalId, @RequestBody Animal animal) {
        service.updateAnimal(animalId, animal);
        return service.getAnimalById(animalId);
    }

    /**
     * Delete a Animal object.
     * http://localhost:8080/animals/delete/2
     *
     * @param animalId the unique Animal Id.
     * @return the updated list of Animals.
     */
    @DeleteMapping("/delete/{animalId}")
    public List<Animal> deleteAnimalById(@PathVariable int animalId) {
        service.deleteAnimalById(animalId);
        return service.getAllAnimals();
    }
}
