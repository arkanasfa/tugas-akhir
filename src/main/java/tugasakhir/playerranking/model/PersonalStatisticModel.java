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
    @Column(name="club",nullable = false)
    private String club=" ";

    @NotNull
    @Column(name="game",nullable = false)
    private Double game= 0.0;

    @NotNull
    @Column(name="ppg",nullable = false)
    private Double ppg= 0.0;

    @NotNull
    @Column(name="fgmpg",nullable = false)
    private Double fgmpg= 0.0;

    @NotNull
    @Column(name="fgapg",nullable = false)
    private Double fgapg= 0.0;

    @NotNull
    @Column(name="fgperpg",nullable = false)
    private Double fgperpg= 0.0;

    @NotNull
    @Column(name="twoptmpg",nullable = false)
    private Double twoptmpg= 0.0;

    @NotNull
    @Column(name="twoptapg",nullable = false)
    private Double twoptapg= 0.0;

    @NotNull
    @Column(name="twoptperpg",nullable = false)
    private Double twoptperpg= 0.0;

    @NotNull
    @Column(name="threeptmpg",nullable = false)
    private Double threeptmpg= 0.0;

    @NotNull
    @Column(name="threeptapg",nullable = false)
    private Double threeptapg= 0.0;

    @NotNull
    @Column(name="threeptperpg",nullable = false)
    private Double threeptperpg= 0.0;

    @NotNull
    @Column(name="ftmpg",nullable = false)
    private Double ftmpg= 0.0;

    @NotNull
    @Column(name="ftapg",nullable = false)
    private Double ftapg= 0.0;

    @NotNull
    @Column(name="ftperpg",nullable = false)
    private Double ftperpg= 0.0;

    @NotNull
    @Column(name="apg",nullable = false)
    private Double apg= 0.0;

    @NotNull
    @Column(name="orpg",nullable = false)
    private Double orpg= 0.0;

    @NotNull
    @Column(name="drpg",nullable = false)
    private Double drpg= 0.0;

    @NotNull
    @Column(name="rpg",nullable = false)
    private Double rpg= 0.0;

    @NotNull
    @Column(name="blkpg",nullable = false)
    private Double blkpg= 0.0;

    @NotNull
    @Column(name="stlpg",nullable = false)
    private Double stlpg= 0.0;

    @NotNull
    @Column(name="flspg",nullable = false)
    private Double flspg= 0.0;

    @NotNull
    @Column(name="flsonpg",nullable = false)
    private Double flsonpg= 0.0;

    @NotNull
    @Column(name="topg",nullable = false)
    private Double topg= 0.0;

    @NotNull
    @Column(name="totalPoints",nullable = false)
    private Double points= 0.0;

    @NotNull
    @Column(name="totalFieldGoal",nullable = false)
    private Double fieldGoals= 0.0;

    @NotNull
    @Column(name="totalFieldGoalAttempt",nullable = false)
    private Double fieldGoalAttempts= 0.0;

    @NotNull
    @Column(name="totalTwoPointMade",nullable = false)
    private Double twoPointMades= 0.0;

    @NotNull
    @Column(name="totalTwoPointAttempt",nullable = false)
    private Double twoPointAttempts= 0.0;

    @NotNull
    @Column(name="totalThreePointMade",nullable = false)
    private Double threePointMades= 0.0;

    @NotNull
    @Column(name="totalThreePointAttempt",nullable = false)
    private Double threePointAttempts= 0.0;

    @NotNull
    @Column(name="totalFreeThrow",nullable = false)
    private Double freeThrows= 0.0;

    @NotNull
    @Column(name="totalFreeThrowAttempt",nullable = false)
    private Double freeThrowAttempts= 0.0;

    @NotNull
    @Column(name="totalAssist",nullable = false)
    private Double assists= 0.0;

    @NotNull
    @Column(name="totalRebound",nullable = false)
    private Double rebounds= 0.0;

    @NotNull
    @Column(name="totalOffRebound",nullable = false)
    private Double offRebounds= 0.0;

    @NotNull
    @Column(name="totalDefRebound",nullable = false)
    private Double defRebounds= 0.0;

    @NotNull
    @Column(name="totalBlock",nullable = false)
    private Double blocks= 0.0;

    @NotNull
    @Column(name="totalSteal",nullable = false)
    private Double steals= 0.0;

    @NotNull
    @Column(name="totalFoul",nullable = false)
    private Double fouls= 0.0;

    @NotNull
    @Column(name="totalFoulReceived",nullable = false)
    private Double foulons= 0.0;

    @NotNull
    @Column(name="totalTurnover",nullable = false)
    private Double turnovers= 0.0;

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

    @OneToOne(mappedBy = "personalStatistic")
    private RankModel rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGame() {
        return game;
    }

    public void setGame(Double game) {
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

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public RankModel getRank() {
        return rank;
    }

    public void setRank(RankModel rank) {
        this.rank = rank;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getFieldGoals() {
        return fieldGoals;
    }

    public void setFieldGoals(Double fieldGoals) {
        this.fieldGoals = fieldGoals;
    }

    public Double getFreeThrows() {
        return freeThrows;
    }

    public void setFreeThrows(Double freeThrows) {
        this.freeThrows = freeThrows;
    }

    public Double getAssists() {
        return assists;
    }

    public void setAssists(Double assists) {
        this.assists = assists;
    }

    public Double getRebounds() {
        return rebounds;
    }

    public void setRebounds(Double rebounds) {
        this.rebounds = rebounds;
    }

    public Double getBlocks() {
        return blocks;
    }

    public void setBlocks(Double blocks) {
        this.blocks = blocks;
    }

    public Double getSteals() {
        return steals;
    }

    public void setSteals(Double steals) {
        this.steals = steals;
    }

    public Double getFouls() {
        return fouls;
    }

    public void setFouls(Double fouls) {
        this.fouls = fouls;
    }

    public Double getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Double turnovers) {
        this.turnovers = turnovers;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Double getFgapg() {
        return fgapg;
    }

    public void setFgapg(Double fgapg) {
        this.fgapg = fgapg;
    }

    public Double getFgperpg() {
        return fgperpg;
    }

    public void setFgperpg(Double fgperpg) {
        this.fgperpg = fgperpg;
    }

    public Double getTwoptmpg() {
        return twoptmpg;
    }

    public void setTwoptmpg(Double twoptmpg) {
        this.twoptmpg = twoptmpg;
    }

    public Double getTwoptapg() {
        return twoptapg;
    }

    public void setTwoptapg(Double twoptapg) {
        this.twoptapg = twoptapg;
    }

    public Double getTwoptperpg() {
        return twoptperpg;
    }

    public void setTwoptperpg(Double twoptperpg) {
        this.twoptperpg = twoptperpg;
    }

    public Double getThreeptmpg() {
        return threeptmpg;
    }

    public void setThreeptmpg(Double threeptmpg) {
        this.threeptmpg = threeptmpg;
    }

    public Double getThreeptapg() {
        return threeptapg;
    }

    public void setThreeptapg(Double threeptapg) {
        this.threeptapg = threeptapg;
    }

    public Double getThreeptperpg() {
        return threeptperpg;
    }

    public void setThreeptperpg(Double threeptperpg) {
        this.threeptperpg = threeptperpg;
    }

    public Double getFtapg() {
        return ftapg;
    }

    public void setFtapg(Double ftapg) {
        this.ftapg = ftapg;
    }

    public Double getFtperpg() {
        return ftperpg;
    }

    public void setFtperpg(Double ftperpg) {
        this.ftperpg = ftperpg;
    }

    public Double getOrpg() {
        return orpg;
    }

    public void setOrpg(Double orpg) {
        this.orpg = orpg;
    }

    public Double getDrpg() {
        return drpg;
    }

    public void setDrpg(Double drpg) {
        this.drpg = drpg;
    }

    public Double getFlsonpg() {
        return flsonpg;
    }

    public void setFlsonpg(Double flsonpg) {
        this.flsonpg = flsonpg;
    }

    public Double getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public void setFieldGoalAttempts(Double fieldGoalAttempts) {
        this.fieldGoalAttempts = fieldGoalAttempts;
    }

    public Double getFreeThrowAttempts() {
        return freeThrowAttempts;
    }

    public void setFreeThrowAttempts(Double freeThrowAttempts) {
        this.freeThrowAttempts = freeThrowAttempts;
    }

    public Double getOffRebounds() {
        return offRebounds;
    }

    public void setOffRebounds(Double offRebounds) {
        this.offRebounds = offRebounds;
    }

    public Double getDefRebounds() {
        return defRebounds;
    }

    public void setDefRebounds(Double defRebounds) {
        this.defRebounds = defRebounds;
    }

    public Double getFoulons() {
        return foulons;
    }

    public void setFoulons(Double foulons) {
        this.foulons = foulons;
    }

    public Double getTwoPointMades() {
        return twoPointMades;
    }

    public void setTwoPointMades(Double twoPointMades) {
        this.twoPointMades = twoPointMades;
    }

    public Double getTwoPointAttempts() {
        return twoPointAttempts;
    }

    public void setTwoPointAttempts(Double twoPointAttempts) {
        this.twoPointAttempts = twoPointAttempts;
    }

    public Double getThreePointMades() {
        return threePointMades;
    }

    public void setThreePointMades(Double threePointMades) {
        this.threePointMades = threePointMades;
    }

    public Double getThreePointAttempts() {
        return threePointAttempts;
    }

    public void setThreePointAttempts(Double threePointAttempts) {
        this.threePointAttempts = threePointAttempts;
    }
}
