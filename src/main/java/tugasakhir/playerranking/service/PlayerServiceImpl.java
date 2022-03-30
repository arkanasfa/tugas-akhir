package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.model.PositionModel;
import tugasakhir.playerranking.repository.PlayerRepository;

import javax.transaction.Transactional;
import java.time.*;
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
        PositionModel position = positionService.getPositionById(positionId);
        ClubModel club = clubService.getClubById(clubId);
        Integer age = generateAge(newPlayer.getBirthday());
        newPlayer.setPlayer_club(club);
        newPlayer.setPlayer_position(position);
        newPlayer.setAge(age);
        playerRepository.save(newPlayer);}

    @Override
    public void editPlayer(PlayerModel editedPlayer, Long positionId, Long clubId){
        PlayerModel oldPlayer = getPlayerById(editedPlayer.getId());
        PositionModel newPosition = positionService.getPositionById(positionId);
        ClubModel newClub = clubService.getClubById(clubId);
        Integer newAge = generateAge(editedPlayer.getBirthday());
        oldPlayer.setName(editedPlayer.getName());
        oldPlayer.setAge(newAge);
        oldPlayer.setBirthday(editedPlayer.getBirthday());
        oldPlayer.setHeight(editedPlayer.getHeight());
        oldPlayer.setWeight(editedPlayer.getWeight());
        oldPlayer.setNumber(editedPlayer.getNumber());
        oldPlayer.setPlayer_position(newPosition);
        oldPlayer.setPlayer_club(newClub);
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
}
