package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.PersonalStatisticModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.model.RankModel;

import java.util.List;

public interface RankService {
    void rankPlayer(List<PersonalStatisticModel> listPersonalStatistic, List<RankModel> listRank);
    void addRank(RankModel rank);
    List<RankModel> createListOfRank(List<PersonalStatisticModel> listPersonalStatistic);
    void addAllRank(List<RankModel> listRank);
    List<RankModel> getRankingByPlayerAndCompetition(List<PlayerModel> listPlayerModel, CompetitionModel competition);
    List<RankModel> sortRank(List<RankModel> listRank);
}
