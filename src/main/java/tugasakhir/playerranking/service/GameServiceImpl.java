package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;
import tugasakhir.playerranking.repository.CompetitionRepository;
import tugasakhir.playerranking.repository.GameRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class GameServiceImpl implements GameService{

    @Autowired
    GameRepository gameRepository;

    @Override
    public GameModel findGameById(Long id){
        return gameRepository.findById(id).get();
    }

    @Override
    public List<GameModel> getGameList(){
        return gameRepository.findAll();
    }

    @Override
    public GameModel findGameByCode(String code){return gameRepository.findByCode(code).get();}

    @Override
    public void removeGame(GameModel game) {gameRepository.delete(game);}

    @Override
    public void saveGame(GameModel game) {gameRepository.save(game);}

    @Override
    public void addGameScore(GameModel game, ClubModel club, List<PlayerGameStatisticModel> listPlayerGameStatistic){
        Integer score = 0;
        for(PlayerGameStatisticModel playerGameStatisticModel:listPlayerGameStatistic){
            score=score+playerGameStatisticModel.getPoint();
        }
        if(game.getAway_club()==club){
            game.setAway_score(score);
            game.setAway_statistic_identifier(1);
            saveGame(game);

        }
        else{
            game.setHome_score(score);
            game.setHome_statistic_identifier(1);
            saveGame(game);
        }
    }




}
