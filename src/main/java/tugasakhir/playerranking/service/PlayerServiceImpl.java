package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.model.PositionModel;
import tugasakhir.playerranking.repository.PlayerRepository;

import javax.transaction.Transactional;
import java.text.Collator;
import java.time.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ClubService clubService;

    @Autowired
    PositionService positionService;

    @Override
    public List<PlayerModel> getPlayerList() {return playerRepository.findAll();}

    @Override
    public PlayerModel getPlayerById(Long id) {return playerRepository.findById(id).get();}

    @Override
    public void addPlayer(PlayerModel newPlayer, Long positionId, Long clubId) {
        if(clubId==-1){
            newPlayer.setPlayer_club(null);
        }
        else{
            ClubModel club = clubService.getClubById(clubId);
            newPlayer.setPlayer_club(club);
        }
        PositionModel position = positionService.getPositionById(positionId);
        newPlayer.setPlayer_position(position);
        Integer age = generateAge(newPlayer.getBirthday());
        newPlayer.setAge(age);
        playerRepository.save(newPlayer);}

    @Override
    public void editPlayer(PlayerModel editedPlayer, Long positionId, Long clubId){
        PlayerModel oldPlayer = getPlayerById(editedPlayer.getId());
        PositionModel newPosition = positionService.getPositionById(positionId);
        if(clubId==-1){
            oldPlayer.setPlayer_club(null);
        }
        else{
            ClubModel newClub = clubService.getClubById(clubId);
            oldPlayer.setPlayer_club(newClub);
        }
        Integer newAge = generateAge(editedPlayer.getBirthday());
        oldPlayer.setName(editedPlayer.getName());
        oldPlayer.setAge(newAge);
        oldPlayer.setBirthday(editedPlayer.getBirthday());
        oldPlayer.setHeight(editedPlayer.getHeight());
        oldPlayer.setWeight(editedPlayer.getWeight());
        oldPlayer.setNumber(editedPlayer.getNumber());
        oldPlayer.setPlayer_position(newPosition);
        playerRepository.save(oldPlayer);
    }

    private Integer generateAge(Date birthday){
        Instant instant = birthday.toInstant();
        ZonedDateTime zoned = instant.atZone(ZoneId.of("Asia/Jakarta"));
        LocalDate birthDate = zoned.toLocalDate();
        Period period = Period.between(birthDate, LocalDate.now());
        return period.getYears();
    }

    @Override
    public void deletePlayer(PlayerModel player){
        playerRepository.delete(player);
    }

    @Override
    public Page<PlayerModel> getPlayerPagination(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<PlayerModel> listPlayer = playerRepository.findAll();
        Collections.sort(listPlayer, (PlayerModel p1, PlayerModel p2) -> p1.getName().compareTo(p2.getName()));
        List<PlayerModel> listPlayerOnPage;
        if (listPlayer.size() < startItem) {
            listPlayerOnPage = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listPlayer.size());
            listPlayerOnPage = listPlayer.subList(startItem, toIndex);
        }
        Page<PlayerModel> playerPage = new PageImpl<PlayerModel>(listPlayerOnPage, PageRequest.of(currentPage, pageSize), listPlayer.size());

        return playerPage;
    }

    //@Override
    //public PlayerModel getPlayerByNumberandByClub(String number, ClubModel player_club) {return playerRepository.findByNumberandByClub(number,player_club).get();}
}
