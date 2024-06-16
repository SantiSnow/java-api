package com.osaka_software.api.run;


import jakarta.annotation.PostConstruct;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();
    private final JdbcClient jdbcClient;


    public RunRepository(JdbcClient jdbcClient)
    {
        this.jdbcClient = jdbcClient;
    }


    List<Run> findAll()
    {
        return jdbcClient.sql("SELECT * FROM RUNS")
                .query(Run.class)
                .list();
    }

    Optional<Run> findById(Integer id)
    {
        return jdbcClient.sql("SELECT * FROM RUNS WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    void store(Run run)
    {
        var updated = jdbcClient.sql("INSERT INTO RUNS (id, title, kilometers, startTime, endTime ,location) VALUES (?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.kilometers(), run.startTime(), run.endTime(), run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    void update(Run run, Integer id)
    {
        var updated = jdbcClient.sql("UPDATE RUNS SET title=?, kilometers=?, startTime=?, endTime=? ,location=? WHERE id=?")
                .params(List.of(run.title(), run.kilometers(), run.startTime(), run.endTime(), run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    void delete(Integer id)
    {
        var updated = jdbcClient.sql("DELETE FROM RUNS WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);
    }
}
