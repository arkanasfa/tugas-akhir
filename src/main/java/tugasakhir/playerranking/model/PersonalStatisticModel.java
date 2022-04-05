package tugasakhir.playerranking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Table
@Entity(name="personal_statistic")
public class PersonalStatisticModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="game",nullable = false, columnDefinition = "integer default 0")
    private Integer game=0;

    @NotNull
    @Column(name="ppg",nullable = false, columnDefinition = "double precision default 0")
    private Double ppg=0.0;

    @NotNull
    @Column(name="fgmpg",nullable = false, columnDefinition = "double precision default 0")
    private Double fgmpg=0.0;

    @NotNull
    @Column(name="apg",nullable = false, columnDefinition = "double precision default 0")
    private Double apg=0.0;

    @NotNull
    @Column(name="rpg",nullable = false, columnDefinition = "double precision default 0")
    private Double rpg=0.0;

    @NotNull
    @Column(name="blkpg",nullable = false, columnDefinition = "double precision default 0")
    private Double blkpg=0.0;

    @NotNull
    @Column(name="stlpg",nullable = false, columnDefinition = "double precision default 0")
    private Double stlpg=0.0;

    @NotNull
    @Column(name="ftmpg",nullable = false, columnDefinition = "double precision default 0")
    private Double ftmpg=0.0;

    @NotNull
    @Column(name="flspg",nullable = false, columnDefinition = "double precision default 0")
    private Double flspg=0.0;

    @NotNull
    @Column(name="topg",nullable = false, columnDefinition = "double precision default 0")
    private Double topg=0.0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PlayerModel player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CompetitionModel competition_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Double getPpg() {
        return ppg;
    }

    public void setPpg(Double ppg) {
        this.ppg = ppg;
    }

    public Double getFgmpg() {
        return fgmpg;
    }

    public void setFgmpg(Double fgmpg) {
        this.fgmpg = fgmpg;
    }

    public Double getApg() {
        return apg;
    }

    public void setApg(Double apg) {
        this.apg = apg;
    }

    public Double getRpg() {
        return rpg;
    }

    public void setRpg(Double rpg) {
        this.rpg = rpg;
    }

    public Double getBlkpg() {
        return blkpg;
    }

    public void setBlkpg(Double blkpg) {
        this.blkpg = blkpg;
    }

    public Double getStlpg() {
        return stlpg;
    }

    public void setStlpg(Double stlpg) {
        this.stlpg = stlpg;
    }

    public Double getFtmpg() {
        return ftmpg;
    }

    public void setFtmpg(Double ftmpg) {
        this.ftmpg = ftmpg;
    }

    public Double getFlspg() {
        return flspg;
    }

    public void setFlspg(Double flspg) {
        this.flspg = flspg;
    }

    public Double getTopg() {
        return topg;
    }

    public void setTopg(Double topg) {
        this.topg = topg;
    }

    public PlayerModel getPlayer_id() {
        return player;
    }

    public void setPlayer_id(PlayerModel player) {
        this.player = player;
    }

    public CompetitionModel getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(CompetitionModel competition_id) {
        this.competition_id = competition_id;
    }
}
