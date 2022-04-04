package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.repository.PlayerGameStatisticRepository;
import tugasakhir.playerranking.utility.CSVprocessor;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class PlayerGameStatisticServiceImpl implements PlayerGameStatisticService{
    @Autowired
    PlayerGameStatisticRepository playerGameStatisticRepository;

    @Override
    public void addPlayerGameStatistic(MultipartFile file, List<PlayerModel> listPlayer, GameModel game, ClubModel club){
        try{
            List<PlayerGameStatisticModel> statistic = CSVprocessor.csvToPlayerGameStatisticModel(file.getInputStream(),listPlayer,game,club);
            playerGameStatisticRepository.saveAll(statistic);
        } catch(IOException e){
                throw new RuntimeException("error: "+e.getMessage());
            }
    }

    @Override
    public List<PlayerGameStatisticModel> getClubGameStatistic(Long game_id,Long club_id){
        return playerGameStatisticRepository.findClubGameStatistic(game_id,club_id);
    }

}
