package tugasakhir.playerranking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="rank")
public class RankModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "performance_score",nullable = false)
    private Double performance_score=0.0;

    @OneToOne
    @JoinColumn(name="personal_statistic_id",referencedColumnName = "id")
    private PersonalStatisticModel personalStatistic;

    @OneToOne
    @JoinColumn(name="weight_id",referencedColumnName = "id")
    private WeightModel weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPerformance_score() {
        return performance_score;
    }

    public void setPerformance_score(Double performance_score) {
        this.performance_score = performance_score;
    }

    public PersonalStatisticModel getPersonalStatistic() {
        return personalStatistic;
    }

    public void setPersonalStatistic(PersonalStatisticModel personalStatistic) {
        this.personalStatistic = personalStatistic;
    }

    public WeightModel getWeight() {
        return weight;
    }

    public void setWeight(WeightModel weight) {
        this.weight = weight;
    }
}
