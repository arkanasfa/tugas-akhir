package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.*;
import tugasakhir.playerranking.repository.PersonalStatisticRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonalStatisticServiceImpl implements PersonalStatisticService{
    @Autowired
    PersonalStatisticRepository personalStatisticRepository;

    @Autowired
    PlayerGameStatisticService playerGameStatisticService;

    @Override
    public void addPersonalStatistic(List<PlayerModel> listPlayer, GameModel game) {
        for(PlayerModel player : listPlayer){
            if(personalStatisticRepository.findPersonalStatisticByCompetitionIdandPlayerId(game.getGame_competition().getId(),player.getId()).isPresent()){
                PersonalStatisticModel personalStatistic = personalStatisticRepository.findPersonalStatisticByCompetitionIdandPlayerId(game.getGame_competition().getId(),player.getId()).get();
                personalStatisticRepository.save(calculatePersonalStatistic(personalStatistic,game,player));
            }
            else if(playerGameStatisticService.checkPlayerGameStatistic(game.getId(), player.getId())){
                PersonalStatisticModel newPersonalStatistic = new PersonalStatisticModel();
                newPersonalStatistic.setPlayer_id(player);
                newPersonalStatistic.setCompetition_id(game.getGame_competition());
                personalStatisticRepository.save(calculatePersonalStatistic(newPersonalStatistic,game,player));
            }
        }
    }

    private PersonalStatisticModel calculatePersonalStatistic(PersonalStatisticModel personalStatistic,GameModel game, PlayerModel player){
        PlayerGameStatisticModel gameStatistic = playerGameStatisticService.getPlayerGameStatistic(game.getId(), player.getId());
        personalStatistic.setGame(personalStatistic.getGame()+1);
        personalStatistic.setPpg((personalStatistic.getPpg()+gameStatistic.getPoint())/personalStatistic.getGame());
        personalStatistic.setFgmpg((personalStatistic.getFgmpg()+gameStatistic.getField_goal_made())/personalStatistic.getGame());
        personalStatistic.setApg((personalStatistic.getApg()+gameStatistic.getAssist())/personalStatistic.getGame());
        personalStatistic.setFtmpg((personalStatistic.getFtmpg()+gameStatistic.getFree_throw_made())/personalStatistic.getGame());
        personalStatistic.setRpg((personalStatistic.getRpg()+gameStatistic.getOffRebound()+gameStatistic.getDefRebound())/personalStatistic.getGame());
        personalStatistic.setBlkpg((personalStatistic.getBlkpg()+ gameStatistic.getBlock())/personalStatistic.getGame());
        personalStatistic.setStlpg((personalStatistic.getStlpg()+gameStatistic.getSteal())/personalStatistic.getGame());
        personalStatistic.setFlspg((personalStatistic.getFlspg()+gameStatistic.getFoul())/personalStatistic.getGame());
        personalStatistic.setTopg((personalStatistic.getTopg()+gameStatistic.getTurnover())/personalStatistic.getGame());
        return personalStatistic;
    }
}
