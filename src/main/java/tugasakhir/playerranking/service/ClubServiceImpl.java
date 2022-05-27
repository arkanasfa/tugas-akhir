package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.repository.ClubRepository;

import javax.transaction.Transactional;
import java.text.Collator;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ClubServiceImpl implements ClubService{

    @Autowired
    ClubRepository clubRepository;

    @Override
    public Page<ClubModel> getClubPagination(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ClubModel> listClub = clubRepository.findAll();
        Collections.sort(listClub, (ClubModel c1, ClubModel c2) -> c1.getName().compareTo(c2.getName()));
        List<ClubModel> listClubOnPage;
        if (listClub.size() < startItem) {
            listClubOnPage = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listClub.size());
            listClubOnPage = listClub.subList(startItem, toIndex);
        }
        Page<ClubModel> clubPage = new PageImpl<ClubModel>(listClubOnPage, PageRequest.of(currentPage, pageSize), listClub.size());

        return clubPage;
    }

    @Override
    public List<ClubModel> getClubList(){return clubRepository.findAll();}

    @Override
    public ClubModel getClubById(Long id) {return clubRepository.findById(id).get();}

    @Override
    public void addClub(ClubModel newClub) {clubRepository.save(newClub);}

    @Override
    public void editClub(ClubModel editedClub) {
        ClubModel oldClub = getClubById(editedClub.getId());
        oldClub.setName(editedClub.getName());
        clubRepository.save(oldClub);
    }

    @Override
    public void deleteClub(ClubModel club) {
        List<PlayerModel> playerList=club.getPlayerList();
        for(PlayerModel player : playerList){
            player.setPlayer_club(null);
        }
        clubRepository.delete(club);
    }

}
