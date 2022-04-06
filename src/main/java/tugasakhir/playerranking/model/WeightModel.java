package tugasakhir.playerranking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="weight")
public class WeightModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="C1",nullable = false)
    private Double C1 = 0.2;

    @NotNull
    @Column(name="C2",nullable = false)
    private Double C2 = 0.1;

    @NotNull
    @Column(name="C3",nullable = false)
    private Double C3 = 0.1;

    @NotNull
    @Column(name="C4",nullable = false)
    private Double C4 = 0.1;

    @NotNull
    @Column(name="C5",nullable = false)
    private Double C5 = 0.1;

    @NotNull
    @Column(name="C6",nullable = false)
    private Double C6 = 0.1;

    @NotNull
    @Column(name="C7",nullable = false)
    private Double C7 = 0.1;

    @NotNull
    @Column(name="C8",nullable = false)
    private Double C8 = 0.1;

    @NotNull
    @Column(name="C9",nullable = false)
    private Double C9 = 0.1;

    @OneToOne(mappedBy = "weight")
    private RankModel rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getC1() {
        return C1;
    }

    public void setC1(Double c1) {
        C1 = c1;
    }

    public Double getC2() {
        return C2;
    }

    public void setC2(Double c2) {
        C2 = c2;
    }

    public Double getC3() {
        return C3;
    }

    public void setC3(Double c3) {
        C3 = c3;
    }

    public Double getC4() {
        return C4;
    }

    public void setC4(Double c4) {
        C4 = c4;
    }

    public Double getC5() {
        return C5;
    }

    public void setC5(Double c5) {
        C5 = c5;
    }

    public Double getC6() {
        return C6;
    }

    public void setC6(Double c6) {
        C6 = c6;
    }

    public Double getC7() {
        return C7;
    }

    public void setC7(Double c7) {
        C7 = c7;
    }

    public Double getC8() {
        return C8;
    }

    public void setC8(Double c8) {
        C8 = c8;
    }

    public Double getC9() {
        return C9;
    }

    public void setC9(Double c9) {
        C9 = c9;
    }

    public RankModel getRank() {
        return rank;
    }

    public void setRank(RankModel rank) {
        this.rank = rank;
    }
}
