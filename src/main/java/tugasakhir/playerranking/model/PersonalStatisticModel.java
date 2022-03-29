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

@Table
@Entity(name="personal_statistic")
public class PersonalStatisticModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="ppg",nullable = false)
    private Float ppg;

    @NotNull
    @Column(name="fgmpg",nullable = false)
    private Float fgmpg;

    @NotNull
    @Column(name="apg",nullable = false)
    private Float apg;

    @NotNull
    @Column(name="rpg",nullable = false)
    private Float rpg;

    @NotNull
    @Column(name="blkpg",nullable = false)
    private Float blkpg;

    @NotNull
    @Column(name="stlpg",nullable = false)
    private Float stlpg;

    @NotNull
    @Column(name="ftmpg",nullable = false)
    private Float ftmpg;

    @NotNull
    @Column(name="flspg",nullable = false)
    private Float flspg;

    @NotNull
    @Column(name="topg",nullable = false)
    private Float topg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PlayerModel player_id;

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

    public Float getPpg() {
        return ppg;
    }

    public void setPpg(Float ppg) {
        this.ppg = ppg;
    }

    public Float getFgmpg() {
        return fgmpg;
    }

    public void setFgmpg(Float fgmpg) {
        this.fgmpg = fgmpg;
    }

    public Float getApg() {
        return apg;
    }

    public void setApg(Float apg) {
        this.apg = apg;
    }

    public Float getRpg() {
        return rpg;
    }

    public void setRpg(Float rpg) {
        this.rpg = rpg;
    }

    public Float getBlkpg() {
        return blkpg;
    }

    public void setBlkpg(Float blkpg) {
        this.blkpg = blkpg;
    }

    public Float getStlpg() {
        return stlpg;
    }

    public void setStlpg(Float stlpg) {
        this.stlpg = stlpg;
    }

    public Float getFtmpg() {
        return ftmpg;
    }

    public void setFtmpg(Float ftmpg) {
        this.ftmpg = ftmpg;
    }

    public Float getFlspg() {
        return flspg;
    }

    public void setFlspg(Float flspg) {
        this.flspg = flspg;
    }

    public Float getTopg() {
        return topg;
    }

    public void setTopg(Float topg) {
        this.topg = topg;
    }

    public PlayerModel getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(PlayerModel player_id) {
        this.player_id = player_id;
    }

    public CompetitionModel getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(CompetitionModel competition_id) {
        this.competition_id = competition_id;
    }
}
