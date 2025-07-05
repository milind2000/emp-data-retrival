package com.rcm.emp_managment.dto;

import java.time.LocalDate;

public class ReviewDTO {
    private LocalDate reviewDate;
    private Integer score;

    public ReviewDTO(LocalDate reviewDate, Integer score) {
        this.reviewDate = reviewDate;
        this.score = score;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public Integer getScore() {
        return score;
    }
}
