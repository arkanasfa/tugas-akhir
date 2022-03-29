package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.repository.ClubRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClubServiceImpl implements ClubService{

    @Autowired
    ClubRepository clubRepository;

    @Override
    public List<ClubModel> getClubList() {return clubRepository.findAll();}

    @Override
    public ClubModel getClubById(Long id) {return clubRepository.findById(id).get();}

    @Override
    public void addClub(ClubModel newClub) {clubRepository.save(newClub);}

    @Override
    public void editClub(ClubModel editedClub) {
        ClubModel oldClub = clubRepository.findById(editedClub.getId()).get();
        oldClub.setName(editedClub.getName());
        clubRepository.save(oldClub);
    }

    @Override
    public void deleteClub(ClubModel club) {clubRepository.delete(club);}

}
