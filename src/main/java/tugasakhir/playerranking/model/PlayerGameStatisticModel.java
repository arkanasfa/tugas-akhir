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
@Entity(name="player_game_statistic")
public class PlayerGameStatisticModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="minute",nullable = false)
    private String minute;

    @NotNull
    @Column(name="point",nullable = false)
    private Integer point;

    @NotNull
    @Column(name="field_goal_made",nullable = false)
    private Integer field_goal_made;

    @NotNull
    @Column(name="field_goal_attempted",nullable = false)
    private Integer field_goal_attempted;

    @NotNull
    @Column(name="field_goal_percentage",nullable = false)
    private Double field_goal_percentage;

    @NotNull
    @Column(name="two_point_made",nullable = false)
    private Integer two_point_made;

    @NotNull
    @Column(name="two_point_attempted",nullable = false)
    private Integer two_point_attempted;

    @NotNull
    @Column(name="two_point_percentage",nullable = false)
    private Double two_point_percentage;

    @NotNull
    @Column(name="three_point_made",nullable = false)
    private Integer three_point_made;

    @NotNull
    @Column(name="three_point_attempted",nullable = false)
    private Integer three_point_attempted;

    @NotNull
    @Column(name="three_point_percentage",nullable = false)
    private Double three_point_percentage;

    @NotNull
    @Column(name="free_throw_made",nullable = false)
    private Integer free_throw_made;

    @NotNull
    @Column(name="free_throw_attempted",nullable = false)
    private Integer free_throw_attempted;

    @NotNull
    @Column(name="free_throw_percentage",nullable = false)
    private Double free_throw_percentage;

    @NotNull
    @Column(name="offRebound",nullable = false)
    private Integer offRebound;

    @NotNull
    @Column(name="defRebound",nullable = false)
    private Integer defRebound;

    @NotNull
    @Column(name="totalRebound",nullable = false)
    private Integer totalRebound;

    @NotNull
    @Column(name="assist",nullable = false)
    private Integer assist;

    @NotNull
    @Column(name="turnover",nullable = false)
    private Integer turnover;

    @NotNull
    @Column(name="steal",nullable = false)
    private Integer steal;

    @NotNull
    @Column(name="block",nullable = false)
    private Integer block;

    @NotNull
    @Column(name="foul",nullable = false)
    private Integer foul;

    @NotNull
    @Column(name="foulon",nullable = false)
    private Integer foulon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GameModel game_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PlayerModel player_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClubModel club_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getField_goal_made() {
        return field_goal_made;
    }

    public void setField_goal_made(Integer field_goal_made) {
        this.field_goal_made = field_goal_made;
    }

    public Integer getField_goal_attempted() {
        return field_goal_attempted;
    }

    public void setField_goal_attempted(Integer field_goal_attempted) {
        this.field_goal_attempted = field_goal_attempted;
    }

    public Integer getTwo_point_made() {
        return two_point_made;
    }

    public void setTwo_point_made(Integer two_point_made) {
        this.two_point_made = two_point_made;
    }

    public Integer getTwo_point_attempted() {
        return two_point_attempted;
    }

    public void setTwo_point_attempted(Integer two_point_attempted) {
        this.two_point_attempted = two_point_attempted;
    }

    public Integer getThree_point_made() {
        return three_point_made;
    }

    public void setThree_point_made(Integer three_point_made) {
        this.three_point_made = three_point_made;
    }

    public Integer getThree_point_attempted() {
        return three_point_attempted;
    }

    public void setThree_point_attempted(Integer three_point_attempted) {
        this.three_point_attempted = three_point_attempted;
    }

    public Integer getFree_throw_made() {
        return free_throw_made;
    }

    public void setFree_throw_made(Integer free_throw_made) {
        this.free_throw_made = free_throw_made;
    }

    public Integer getFree_throw_attempted() {
        return free_throw_attempted;
    }

    public void setFree_throw_attempted(Integer free_throw_attempted) {
        this.free_throw_attempted = free_throw_attempted;
    }

    public Integer getOffRebound() {
        return offRebound;
    }

    public void setOffRebound(Integer offRebound) {
        this.offRebound = offRebound;
    }

    public Integer getDefRebound() {
        return defRebound;
    }

    public void setDefRebound(Integer defRebound) {
        this.defRebound = defRebound;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    public Integer getTurnover() {
        return turnover;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    public Integer getSteal() {
        return steal;
    }

    public void setSteal(Integer steal) {
        this.steal = steal;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getFoul() {
        return foul;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public GameModel getGame_id() {
        return game_id;
    }

    public void setGame_id(GameModel game_id) {
        this.game_id = game_id;
    }

    public PlayerModel getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(PlayerModel player_id) {
        this.player_id = player_id;
    }

    public ClubModel getClub_id() {
        return club_id;
    }

    public void setClub_id(ClubModel club_id) {
        this.club_id = club_id;
    }

    public Double getField_goal_percentage() {
        return field_goal_percentage;
    }

    public void setField_goal_percentage(Double field_goal_percentage) {
        this.field_goal_percentage = field_goal_percentage;
    }

    public Double getTwo_point_percentage() {
        return two_point_percentage;
    }

    public void setTwo_point_percentage(Double two_point_percentage) {
        this.two_point_percentage = two_point_percentage;
    }

    public Double getThree_point_percentage() {
        return three_point_percentage;
    }

    public void setThree_point_percentage(Double three_point_percentage) {
        this.three_point_percentage = three_point_percentage;
    }

    public Double getFree_throw_percentage() {
        return free_throw_percentage;
    }

    public void setFree_throw_percentage(Double free_throw_percentage) {
        this.free_throw_percentage = free_throw_percentage;
    }

    public Integer getTotalRebound() {
        return totalRebound;
    }

    public void setTotalRebound(Integer totalRebound) {
        this.totalRebound = totalRebound;
    }

    public Integer getFoulon() {
        return foulon;
    }

    public void setFoulon(Integer foulon) {
        this.foulon = foulon;
    }
}
