package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.repository.CompetitionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    ClubService clubService;

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
        competitionRepository.save(targetCompetition);
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
}
