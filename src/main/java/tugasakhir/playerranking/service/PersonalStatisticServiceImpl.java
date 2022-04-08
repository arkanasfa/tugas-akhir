package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.*;
import tugasakhir.playerranking.repository.PersonalStatisticRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonalStatisticServiceImpl implements PersonalStatisticService{
    @Autowired
    PersonalStatisticRepository personalStatisticRepository;

    @Autowired
    PlayerGameStatisticService playerGameStatisticService;

    @Override
    public List<PersonalStatisticModel> addPersonalStatistic(List<PlayerModel> listPlayer, GameModel game) {
        List<PersonalStatisticModel> listPersonalStatistic = new ArrayList<>();
        for(PlayerModel player : listPlayer){
            PersonalStatisticModel personalStatistic = getPersonalStatisticByCompetitionIdandPlayerId(game.getGame_competition().getId(),player.getId());
            if(playerGameStatisticService.checkPlayerGameStatistic(game.getId(), player.getId())){
                savePersonalStatistic(calculatePersonalStatistic(personalStatistic,game,player));
            }
            listPersonalStatistic.add(personalStatistic);
        }
        return listPersonalStatistic;
    }

    private PersonalStatisticModel calculatePersonalStatistic(PersonalStatisticModel personalStatistic,GameModel game, PlayerModel player){
        PlayerGameStatisticModel gameStatistic = playerGameStatisticService.getPlayerGameStatistic(game.getId(), player.getId());
        personalStatistic.setGame(personalStatistic.getGame()+1);
        personalStatistic.setPoints(personalStatistic.getPoints()+gameStatistic.getPoint());
        personalStatistic.setFieldGoals(personalStatistic.getFieldGoals()+gameStatistic.getField_goal_made());
        personalStatistic.setAssists(personalStatistic.getAssists()+gameStatistic.getAssist());
        personalStatistic.setFreeThrows(personalStatistic.getFreeThrows()+gameStatistic.getFree_throw_made());
        personalStatistic.setRebounds(personalStatistic.getRebounds()+gameStatistic.getDefRebound()+gameStatistic.getDefRebound());
        personalStatistic.setBlocks(personalStatistic.getBlocks()+gameStatistic.getBlock());
        personalStatistic.setSteals(personalStatistic.getSteals()+gameStatistic.getSteal());
        personalStatistic.setFouls(personalStatistic.getFouls()+gameStatistic.getFoul());
        personalStatistic.setTurnovers(personalStatistic.getTurnovers()+gameStatistic.getTurnover());
        personalStatistic.setPpg(personalStatistic.getPoints()/personalStatistic.getGame());
        personalStatistic.setFgmpg(personalStatistic.getFieldGoals()/personalStatistic.getGame());
        personalStatistic.setApg(personalStatistic.getAssists()/personalStatistic.getGame());
        personalStatistic.setFtmpg(personalStatistic.getFreeThrows()/personalStatistic.getGame());
        personalStatistic.setRpg(personalStatistic.getRebounds()/personalStatistic.getGame());
        personalStatistic.setBlkpg(personalStatistic.getBlocks()/personalStatistic.getGame());
        personalStatistic.setStlpg(personalStatistic.getSteals()/personalStatistic.getGame());
        personalStatistic.setFlspg(personalStatistic.getFouls()/personalStatistic.getGame());
        personalStatistic.setTopg(personalStatistic.getTurnovers()/personalStatistic.getGame());
        return personalStatistic;
    }

    @Override
    public Boolean checkPersonalStatisticByCompetitionIdandPlayerId(Long competitionId, Long playerId){
        return personalStatisticRepository.findPersonalStatisticByCompetitionIdandPlayerId(competitionId,playerId).isPresent();
    }

    @Override
    public PersonalStatisticModel getPersonalStatisticByCompetitionIdandPlayerId(Long competitionId, Long playerId){
        return  personalStatisticRepository.findPersonalStatisticByCompetitionIdandPlayerId(competitionId,playerId).get();
    }

    @Override
    public void savePersonalStatistic(PersonalStatisticModel personalStatistic){
        personalStatisticRepository.save(personalStatistic);
    }
}
