package com.rkmgithubacc.mba.moviems.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "theatre_id", precision = 10)
    private int theatreId;

    @Column(name = "theatre_name", unique = true)
    @Size(max = 20)
    @NotNull(message = "Theatre Name must not be null")
    private String theatreName;

    @Column(name = "movie_id")
    @NotNull(message = "Movie ID must not be null")
    private int movieId;

    public Theatre() {
    }

    public Theatre(String theatreName, int movieId) {
        this.theatreName = theatreName;
        this.movieId = movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "theatreId=" + theatreId +
                ", theatreName='" + theatreName + '\'' +
                ", movieId=" + movieId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theatre theatre = (Theatre) o;

        if (theatreId != theatre.theatreId) return false;
        if (movieId != theatre.movieId) return false;
        return Objects.equals(theatreName, theatre.theatreName);
    }

    @Override
    public int hashCode() {
        int result = theatreId;
        result = 31 * result + (theatreName != null ? theatreName.hashCode() : 0);
        result = 31 * result + movieId;
        return result;
    }
}
