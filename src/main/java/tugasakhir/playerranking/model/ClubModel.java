package tugasakhir.playerranking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="club")
public class ClubModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=100)
    @Column(name="name",nullable = false)
    private String name;

    @ManyToMany(mappedBy = "participant_club")
    private List<CompetitionModel> competitions;

    @OneToMany(mappedBy = "player_club", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PlayerModel> playerList;

    @OneToMany(mappedBy = "home_club", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GameModel> homegameList;

    @OneToMany(mappedBy = "away_club", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GameModel> awaygameList;

    @OneToMany(mappedBy = "club_id", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PlayerGameStatisticModel> playerclubgamestatisticList;

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

    public List<CompetitionModel> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitionModel> competitions) {
        this.competitions = competitions;
    }

    public List<PlayerModel> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<PlayerModel> playerList) {
        this.playerList = playerList;
    }

    public List<GameModel> getHomegameList() {
        return homegameList;
    }

    public void setHomegameList(List<GameModel> homegameList) {
        this.homegameList = homegameList;
    }

    public List<GameModel> getAwaygameList() {
        return awaygameList;
    }

    public void setAwaygameList(List<GameModel> awaygameList) {
        this.awaygameList = awaygameList;
    }
}
