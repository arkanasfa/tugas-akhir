package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.WeightModel;
import tugasakhir.playerranking.repository.WeightRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class WeightServiceImpl implements WeightService{
    @Autowired
    WeightRepository weightRepository;

    @Override
    public void addWeight(WeightModel weightModel){weightRepository.save(weightModel);}
}
