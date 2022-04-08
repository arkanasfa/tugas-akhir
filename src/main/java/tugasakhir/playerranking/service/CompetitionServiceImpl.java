package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.*;
import tugasakhir.playerranking.repository.CompetitionRepository;
import tugasakhir.playerranking.repository.GameRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    ClubService clubService;

    @Autowired
    GameService gameService;

    @Autowired
    PersonalStatisticService personalStatisticService;

    @Override
    public List<CompetitionModel> getCompetitionList(){return competitionRepository.findAll();}

    @Override
    public void addCompetition(CompetitionModel newCompetition) {competitionRepository.save(newCompetition);}

    @Override
    public CompetitionModel getCompetitionById(Long id) {return competitionRepository.findById(id).get();}

    @Override
    public void deleteCompetition(CompetitionModel competition) {competitionRepository.delete(competition);}

    @Override
    public void editCompetition(CompetitionModel competition) {
        CompetitionModel oldCompetition = getCompetitionById(competition.getId());
        oldCompetition.setName(competition.getName());
        oldCompetition.setStart_date(competition.getStart_date());
        oldCompetition.setEnd_date(competition.getEnd_date());
        oldCompetition.setYear(competition.getYear());
        competitionRepository.save(oldCompetition);
    }

    @Override
    public void addParticipant(CompetitionModel competition, Long participantId){
        CompetitionModel targetCompetition = getCompetitionById(competition.getId());
        ClubModel participant = clubService.getClubById(participantId);
        List<ClubModel> oldParticipant = targetCompetition.getParticipant_club();
        oldParticipant.add(participant);
        targetCompetition.setParticipant_club(oldParticipant);
        List<PlayerModel> playerList = participant.getPlayerList();
        createPersonalStatistic(playerList,targetCompetition);
        competitionRepository.save(targetCompetition);
    }

    private void createPersonalStatistic(List<PlayerModel> playerList,CompetitionModel competition){
        for(PlayerModel player:playerList){
            PersonalStatisticModel newPersonalStatistic = new PersonalStatisticModel();
            newPersonalStatistic.setPlayer_id(player);
            newPersonalStatistic.setCompetition_id(competition);
            personalStatisticService.savePersonalStatistic(newPersonalStatistic);
        }
    }

    @Override
    public void removeParticipant(Long competitionId, Long participantId){
        CompetitionModel targetCompetition = getCompetitionById(competitionId);
        ClubModel participant = clubService.getClubById(participantId);
        List<ClubModel> oldParticipant = targetCompetition.getParticipant_club();
        oldParticipant.remove(participant);
        targetCompetition.setParticipant_club(oldParticipant);
        competitionRepository.save(targetCompetition);
    }

    @Override
    public String addGame(GameModel newGame, Long home_clubId, Long away_clubId){
        ClubModel home_club = clubService.getClubById(home_clubId);
        ClubModel away_club = clubService.getClubById(away_clubId);
        newGame.setHome_club(home_club);
        newGame.setHome_score(Integer.valueOf(0));
        newGame.setAway_club(away_club);
        newGame.setAway_score(Integer.valueOf(0));
        String code = gameCodeGenerator(newGame,home_clubId,away_clubId);
        newGame.setCode(code);
        gameService.saveGame(newGame);
        return code;
    }

    private String gameCodeGenerator(GameModel newGame, Long home_clubId, Long away_clubId){
        Random random = new Random();
        return "CO"+String.valueOf(newGame.getGame_competition().getId())+"HM"+String.valueOf(home_clubId)+"AW"+String.valueOf(away_clubId)+
                String.valueOf(Integer.toString(random.nextInt(9)))+String.valueOf(Integer.toString(random.nextInt(9)))
                +String.valueOf(Integer.toString(random.nextInt(9)))+String.valueOf(Integer.toString(random.nextInt(9)));
    }

    @Override
    public String editGame(GameModel game){
        GameModel targetGame = gameService.findGameById(game.getId());
        targetGame.setDate(game.getDate());
        targetGame.setTipoff(game.getTipoff());
        gameService.saveGame(targetGame);
        return targetGame.getCode();
    }


}
