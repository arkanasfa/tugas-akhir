package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.ClubModel;

import java.util.List;

public interface ClubService {

    List<ClubModel> getClubList();
    ClubModel getClubById(Long id);
    void addClub(ClubModel newclub);
    void deleteClub(ClubModel club);
    void editClub(ClubModel editedClub);
}
