package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.PositionModel;

import java.util.List;

public interface PositionService {
    List<PositionModel> getPositionList();
    PositionModel getPositionById(Long id);
}
