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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClubModel game_competition;

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

    public ClubModel getGame_competition() {
        return game_competition;
    }

    public void setGame_competition(ClubModel game_competition) {
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
