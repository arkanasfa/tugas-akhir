package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.PositionModel;
import tugasakhir.playerranking.repository.PositionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements PositionService{
    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<PositionModel> getPositionList() {return positionRepository.findAll();}

    @Override
    public PositionModel getPositionById(Long id) {return positionRepository.findById(id).get();}
}
