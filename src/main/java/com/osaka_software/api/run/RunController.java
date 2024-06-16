package com.osaka_software.api.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository)
    {
        this.runRepository = runRepository;
    }

    /**
     * Find all the runs
     *
     * @return
     */
    @GetMapping("")
    public List<Run> findAll() {
        return runRepository.findAll();
    }

    /**
     * Find single Run
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty())
        {
            throw new RunNotFoundException();
        }
        return run.get();
    }

    /**
     * Create a new run record
     *
     * @param run
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Run run)
    {
        runRepository.store(run);
    }

    /**
     * Edit a single run record
     * @param run
     * @param id
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@RequestBody Run run, @PathVariable Integer id)
    {
        runRepository.update(run, id);
    }

    /**
     * Delete a single record
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        runRepository.delete(id);
    }

}
