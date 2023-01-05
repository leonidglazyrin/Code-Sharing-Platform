package com.api.codeplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "snippets")
public class PieceOfCode {

    @JsonProperty("code")
    @Column(name = "piece_of_code", columnDefinition="text")
    private String pieceOfCode;

    @JsonProperty("date")
    @Column(name = "load_date")
    private LocalDateTime loadDate;

    @JsonIgnore
    @Id
    private UUID id;

    @JsonProperty("time")
    @Column(name = "time")
    private Long secondsToDelete = 0L;

    @JsonProperty("views")
    @Column(name = "views")
    private Long viewsToDelete = 0L;

    public PieceOfCode() {
    }

    public String getPieceOfCode() {
        return pieceOfCode;
    }

    public void setPieceOfCode(String pieceOfCode) {
        this.pieceOfCode = pieceOfCode;
    }

    public String getLoadDate() {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");;
        return this.loadDate.format(customFormat);
    }

    public void setLoadDate() {
        this.loadDate = LocalDateTime.now();
    }

    @Override
    public String toString(){
        return this.pieceOfCode;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public Long getSecondsToDelete() {
        if(this.secondsToDelete == 0) return 0L;

        LocalDateTime endTime = this.loadDate.plusSeconds(this.secondsToDelete);
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), endTime);
    }

    public void setSecondsToDelete(Long secondsToDelete) {
        this.secondsToDelete = secondsToDelete;
    }

    public Long getViewsToDelete() {
        return viewsToDelete;
    }

    public void setViewsToDelete(Long viewsToDelete) {
        this.viewsToDelete = viewsToDelete;
    }
}
