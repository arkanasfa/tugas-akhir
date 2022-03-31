package tugasakhir.playerranking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="competition")
public class CompetitionModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=100)
    @Column(name="name",nullable = false)
    private String name;

    @NotNull
    @Size(max=4)
    @Column(name="year",nullable = false)
    private String year;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="start_date",nullable = false)
    private Date start_date;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="end_date",nullable = false)
    private Date end_date;

    @OneToMany(mappedBy = "game_competition", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GameModel> gameList;

    @OneToMany(mappedBy = "competition_id", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PersonalStatisticModel> personalStatisticList;

    @ManyToMany
    @JoinTable(
            name = "participant_club",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id"))
    private List<ClubModel> participant_club;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public List<GameModel> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameModel> gameList) {
        this.gameList = gameList;
    }

    public List<PersonalStatisticModel> getPersonalStatisticList() {
        return personalStatisticList;
    }

    public void setPersonalStatisticList(List<PersonalStatisticModel> personalStatisticList) {
        this.personalStatisticList = personalStatisticList;
    }

    public List<ClubModel> getParticipant_club() {
        return participant_club;
    }

    public void setParticipant_club(List<ClubModel> participant_club) {
        this.participant_club = participant_club;
    }
}
