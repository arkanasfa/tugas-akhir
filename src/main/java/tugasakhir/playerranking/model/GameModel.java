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

@Table
@Entity(name="game")
public class GameModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date",nullable = false)
    private Date date;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name="tipoff",nullable = false)
    private Date tipoff;

    @NotNull
    @Column(name="home_score",nullable = false)
    private Integer home_score;

    @NotNull
    @Column(name="away_score",nullable = false)
    private Integer away_score;

    @NotNull
    @Size(max=100)
    @Column(name="code",nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CompetitionModel game_competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_club_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClubModel home_club;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "away_club_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClubModel away_club;

    @OneToMany(mappedBy = "game_id", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PlayerGameStatisticModel> playergamestatisticList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTipoff() {
        return tipoff;
    }

    public void setTipoff(Date tipoff) {
        this.tipoff = tipoff;
    }

    public Integer getHome_score() {
        return home_score;
    }

    public void setHome_score(Integer home_score) {
        this.home_score = home_score;
    }

    public Integer getAway_score() {
        return away_score;
    }

    public void setAway_score(Integer away_score) {
        this.away_score = away_score;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CompetitionModel getGame_competition() {
        return game_competition;
    }

    public void setGame_competition(CompetitionModel game_competition) {
        this.game_competition = game_competition;
    }

    public ClubModel getHome_club() {
        return home_club;
    }

    public void setHome_club(ClubModel home_club) {
        this.home_club = home_club;
    }

    public ClubModel getAway_club() {
        return away_club;
    }

    public void setAway_club(ClubModel away_club) {
        this.away_club = away_club;
    }

    public List<PlayerGameStatisticModel> getPlayergamestatisticList() {
        return playergamestatisticList;
    }

    public void setPlayergamestatisticList(List<PlayerGameStatisticModel> playergamestatisticList) {
        this.playergamestatisticList = playergamestatisticList;
    }
}
