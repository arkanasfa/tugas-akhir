package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.PersonalStatisticModel;
import tugasakhir.playerranking.model.RankModel;

import java.util.List;

public interface RankService {
    void rankPlayer(List<PersonalStatisticModel> listPersonalStatistic);
    void addRank(RankModel rank);
    List<RankModel> createListOfRank(List<PersonalStatisticModel> listPersonalStatistic);
    void addAllRank(List<RankModel> listRank);
}
