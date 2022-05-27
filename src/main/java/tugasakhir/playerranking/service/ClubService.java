package tugasakhir.playerranking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tugasakhir.playerranking.model.ClubModel;

import java.util.List;

public interface ClubService {

    Page<ClubModel> getClubPagination(Pageable pageable);
    List<ClubModel> getClubList();
    ClubModel getClubById(Long id);
    void addClub(ClubModel newclub);
    void deleteClub(ClubModel club);
    void editClub(ClubModel editedClub);
}
