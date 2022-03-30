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
@Entity(name="player")
public class PlayerModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=100)
    @Column(name="name",nullable = false)
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="birthday",nullable = false)
    private Date birthday;

    @NotNull
    @Column(name="age",nullable = false)
    private Integer age;

    @NotNull
    @Column(name="height",nullable = false)
    private Integer height;

    @NotNull
    @Column(name="weight",nullable = false)
    private Integer weight;

    @NotNull
    @Size(max=2)
    @Column(name="number",nullable = false)
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "club_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClubModel player_club;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PositionModel player_position;

    @OneToMany(mappedBy = "player_id", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PlayerGameStatisticModel> playergamestatisticList;

    @OneToMany(mappedBy = "player_id", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PersonalStatisticModel> personalstatisticList;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ClubModel getPlayer_club() {
        return player_club;
    }

    public void setPlayer_club(ClubModel player_club) {
        this.player_club = player_club;
    }

    public PositionModel getPlayer_position() {
        return player_position;
    }

    public void setPlayer_position(PositionModel player_position) {
        this.player_position = player_position;
    }

    public List<PlayerGameStatisticModel> getPlayergamestatisticList() {
        return playergamestatisticList;
    }

    public void setPlayergamestatisticList(List<PlayerGameStatisticModel> playergamestatisticList) {
        this.playergamestatisticList = playergamestatisticList;
    }

    public List<PersonalStatisticModel> getPersonalstatisticList() {
        return personalstatisticList;
    }

    public void setPersonalstatisticList(List<PersonalStatisticModel> personalstatisticList) {
        this.personalstatisticList = personalstatisticList;
    }
}
