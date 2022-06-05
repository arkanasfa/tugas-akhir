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
        personalStatistic.setClub(player.getPlayer_club().getName());
        personalStatistic.setPoints(personalStatistic.getPoints()+gameStatistic.getPoint());
        personalStatistic.setFieldGoals(personalStatistic.getFieldGoals()+gameStatistic.getField_goal_made());
        personalStatistic.setFieldGoalAttempts(personalStatistic.getFieldGoalAttempts()+gameStatistic.getField_goal_attempted());
        personalStatistic.setTwoPointMades(personalStatistic.getTwoPointMades()+gameStatistic.getTwo_point_made());
        personalStatistic.setTwoPointAttempts(personalStatistic.getTwoPointAttempts()+gameStatistic.getTwo_point_attempted());
        personalStatistic.setThreePointMades(personalStatistic.getThreePointMades()+gameStatistic.getThree_point_made());
        personalStatistic.setThreePointAttempts(personalStatistic.getThreePointAttempts()+gameStatistic.getThree_point_attempted());
        personalStatistic.setAssists(personalStatistic.getAssists()+gameStatistic.getAssist());
        personalStatistic.setFreeThrows(personalStatistic.getFreeThrows()+gameStatistic.getFree_throw_made());
        personalStatistic.setFreeThrowAttempts(personalStatistic.getFreeThrowAttempts()+gameStatistic.getFree_throw_attempted());
        personalStatistic.setOffRebounds(personalStatistic.getOffRebounds()+gameStatistic.getOffRebound());
        personalStatistic.setDefRebounds(personalStatistic.getDefRebounds()+gameStatistic.getDefRebound());
        personalStatistic.setRebounds(personalStatistic.getRebounds()+gameStatistic.getDefRebound()+gameStatistic.getDefRebound());
        personalStatistic.setBlocks(personalStatistic.getBlocks()+gameStatistic.getBlock());
        personalStatistic.setBlockAgainsts(personalStatistic.getBlockAgainsts()+gameStatistic.getBlockAgainst());
        personalStatistic.setSteals(personalStatistic.getSteals()+gameStatistic.getSteal());
        personalStatistic.setFouls(personalStatistic.getFouls()+gameStatistic.getFoul());
        personalStatistic.setFoulons(personalStatistic.getFoulons()+gameStatistic.getFoulon());
        personalStatistic.setTurnovers(personalStatistic.getTurnovers()+gameStatistic.getTurnover());

        personalStatistic.setPpg(personalStatistic.getPoints()/personalStatistic.getGame());
        personalStatistic.setFgmpg(personalStatistic.getFieldGoals()/personalStatistic.getGame());
        personalStatistic.setFgapg(personalStatistic.getFieldGoalAttempts()/personalStatistic.getGame());
        if(personalStatistic.getFieldGoalAttempts()!=0){
            personalStatistic.setFgperpg(personalStatistic.getFieldGoals()/personalStatistic.getFieldGoalAttempts());
        }
        personalStatistic.setTwoptmpg(personalStatistic.getTwoPointMades()/personalStatistic.getGame());
        personalStatistic.setTwoptapg(personalStatistic.getTwoPointAttempts()/personalStatistic.getGame());
        if(personalStatistic.getTwoPointAttempts()!=0){
            personalStatistic.setTwoptperpg(personalStatistic.getTwoPointMades()/personalStatistic.getTwoPointAttempts());
        }
        personalStatistic.setThreeptmpg(personalStatistic.getThreePointMades()/personalStatistic.getGame());
        personalStatistic.setThreeptapg(personalStatistic.getThreePointAttempts()/personalStatistic.getGame());
        if(personalStatistic.getThreePointAttempts()!=0){
            personalStatistic.setThreeptperpg(personalStatistic.getThreePointMades()/personalStatistic.getThreePointAttempts());
        }
        personalStatistic.setApg(personalStatistic.getAssists()/personalStatistic.getGame());
        personalStatistic.setFtmpg(personalStatistic.getFreeThrows()/personalStatistic.getGame());
        personalStatistic.setFtapg(personalStatistic.getFreeThrowAttempts()/personalStatistic.getGame());
        if(personalStatistic.getFreeThrowAttempts()!=0){
            personalStatistic.setFtperpg(personalStatistic.getFreeThrows()/personalStatistic.getFreeThrowAttempts());
        }
        personalStatistic.setOrpg(personalStatistic.getOffRebounds()/personalStatistic.getGame());
        personalStatistic.setDrpg(personalStatistic.getDefRebounds()/personalStatistic.getGame());
        personalStatistic.setRpg(personalStatistic.getRebounds()/personalStatistic.getGame());
        personalStatistic.setBlkpg(personalStatistic.getBlocks()/personalStatistic.getGame());
        personalStatistic.setBlkapg(personalStatistic.getBlockAgainsts()/personalStatistic.getGame());
        personalStatistic.setStlpg(personalStatistic.getSteals()/personalStatistic.getGame());
        personalStatistic.setFlspg(personalStatistic.getFouls()/personalStatistic.getGame());
        personalStatistic.setFlsonpg(personalStatistic.getFoulons()/personalStatistic.getGame());
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

    @Override
    public PersonalStatisticModel removeCalculatedPersonalStatistic(PlayerModel player, PlayerGameStatisticModel gameStatistic, PersonalStatisticModel personalStatistic){
        personalStatistic.setGame(personalStatistic.getGame()-1);
        personalStatistic.setClub(player.getPlayer_club().getName());
        personalStatistic.setPoints(personalStatistic.getPoints()-gameStatistic.getPoint());
        personalStatistic.setFieldGoals(personalStatistic.getFieldGoals()-gameStatistic.getField_goal_made());
        personalStatistic.setFieldGoalAttempts(personalStatistic.getFieldGoalAttempts()-gameStatistic.getField_goal_attempted());
        personalStatistic.setTwoPointMades(personalStatistic.getTwoPointMades()-gameStatistic.getTwo_point_made());
        personalStatistic.setTwoPointAttempts(personalStatistic.getTwoPointAttempts()-gameStatistic.getTwo_point_attempted());
        personalStatistic.setThreePointMades(personalStatistic.getThreePointMades()-gameStatistic.getThree_point_made());
        personalStatistic.setThreePointAttempts(personalStatistic.getThreePointAttempts()-gameStatistic.getThree_point_attempted());
        personalStatistic.setAssists(personalStatistic.getAssists()-gameStatistic.getAssist());
        personalStatistic.setFreeThrows(personalStatistic.getFreeThrows()-gameStatistic.getFree_throw_made());
        personalStatistic.setFreeThrowAttempts(personalStatistic.getFreeThrowAttempts()-gameStatistic.getFree_throw_attempted());
        personalStatistic.setOffRebounds(personalStatistic.getOffRebounds()-gameStatistic.getOffRebound());
        personalStatistic.setDefRebounds(personalStatistic.getDefRebounds()-gameStatistic.getDefRebound());
        personalStatistic.setRebounds(personalStatistic.getRebounds()-(gameStatistic.getDefRebound()+gameStatistic.getDefRebound()));
        personalStatistic.setBlocks(personalStatistic.getBlocks()-gameStatistic.getBlock());
        personalStatistic.setBlockAgainsts(personalStatistic.getBlockAgainsts()-gameStatistic.getBlockAgainst());
        personalStatistic.setSteals(personalStatistic.getSteals()-gameStatistic.getSteal());
        personalStatistic.setFouls(personalStatistic.getFouls()-gameStatistic.getFoul());
        personalStatistic.setFoulons(personalStatistic.getFoulons()-gameStatistic.getFoulon());
        personalStatistic.setTurnovers(personalStatistic.getTurnovers()-gameStatistic.getTurnover());

        personalStatistic.setPpg(personalStatistic.getPoints()/personalStatistic.getGame());
        personalStatistic.setFgmpg(personalStatistic.getFieldGoals()/personalStatistic.getGame());
        personalStatistic.setFgapg(personalStatistic.getFieldGoalAttempts()/personalStatistic.getGame());
        if(personalStatistic.getFieldGoalAttempts()!=0){
            personalStatistic.setFgperpg(personalStatistic.getFieldGoals()/personalStatistic.getFieldGoalAttempts());
        }
        else{
            personalStatistic.setFgperpg(0.0);
        }
        personalStatistic.setTwoptmpg(personalStatistic.getTwoPointMades()/personalStatistic.getGame());
        personalStatistic.setTwoptapg(personalStatistic.getTwoPointAttempts()/personalStatistic.getGame());
        if(personalStatistic.getTwoPointAttempts()!=0){
            personalStatistic.setTwoptperpg(personalStatistic.getTwoPointMades()/personalStatistic.getTwoPointAttempts());
        }
        else{
            personalStatistic.setTwoptperpg(0.0);
        }
        personalStatistic.setThreeptmpg(personalStatistic.getThreePointMades()/personalStatistic.getGame());
        personalStatistic.setThreeptapg(personalStatistic.getThreePointAttempts()/personalStatistic.getGame());
        if(personalStatistic.getThreePointAttempts()!=0){
            personalStatistic.setThreeptperpg(personalStatistic.getThreePointMades()/personalStatistic.getThreePointAttempts());
        }
        else{
            personalStatistic.setThreeptperpg(0.0);
        }
        personalStatistic.setApg(personalStatistic.getAssists()/personalStatistic.getGame());
        personalStatistic.setFtmpg(personalStatistic.getFreeThrows()/personalStatistic.getGame());
        personalStatistic.setFtapg(personalStatistic.getFreeThrowAttempts()/personalStatistic.getGame());
        if(personalStatistic.getFreeThrowAttempts()!=0){
            personalStatistic.setFtperpg(personalStatistic.getFreeThrows()/personalStatistic.getFreeThrowAttempts());
        }
        else{
            personalStatistic.setFtperpg(0.0);
        }
        personalStatistic.setOrpg(personalStatistic.getOffRebounds()/personalStatistic.getGame());
        personalStatistic.setDrpg(personalStatistic.getDefRebounds()/personalStatistic.getGame());
        personalStatistic.setRpg(personalStatistic.getRebounds()/personalStatistic.getGame());
        personalStatistic.setBlkpg(personalStatistic.getBlocks()/personalStatistic.getGame());
        personalStatistic.setBlkapg(personalStatistic.getBlockAgainsts()/personalStatistic.getGame());
        personalStatistic.setStlpg(personalStatistic.getSteals()/personalStatistic.getGame());
        personalStatistic.setFlspg(personalStatistic.getFouls()/personalStatistic.getGame());
        personalStatistic.setFlsonpg(personalStatistic.getFlsonpg()/personalStatistic.getGame());
        personalStatistic.setTopg(personalStatistic.getTurnovers()/personalStatistic.getGame());
        return personalStatistic;
    }
}
