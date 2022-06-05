package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.*;
import tugasakhir.playerranking.repository.RankRepository;

import javax.transaction.Transactional;
import java.util.*;

import static java.lang.Math.sqrt;

@Service
@Transactional
public class RankServiceImpl implements RankService{
    @Autowired
    RankRepository rankRepository;

    @Autowired
    WeightService weightService;

    @Autowired
    PersonalStatisticService personalStatisticService;

    @Override
    public void rankPlayer(List<PersonalStatisticModel> listPersonalStatistic, List<RankModel> listRank){
        List<List<Double>> evaluationMatrix = createMatrix(listPersonalStatistic);
        printMatrix(evaluationMatrix);
        List<List<Double>> normalizedMatrix = normalizeMatrix(evaluationMatrix);
        printMatrix(normalizedMatrix);
        List<List<Double>> weightedMatrix = weightMatrix(normalizedMatrix,listRank);
        printMatrix(weightedMatrix);
        List<List<Double>> idealSolutionMatrix = createIdealSolutionMatrix(weightedMatrix);
        printMatrix(idealSolutionMatrix);
        List<Double> besteuclideanDistance = calculateBestEuclideanDistance(weightedMatrix,idealSolutionMatrix);
        List<Double> worsteuclideanDistance = calculateWorstEuclideanDistance(weightedMatrix,idealSolutionMatrix);
        List<Double> performanceScore = calculatePerformanceScore(besteuclideanDistance,worsteuclideanDistance);
        setRanking(listRank,performanceScore);
    }

    @Override
    public List<RankModel> createListOfRank(List<PersonalStatisticModel> listPersonalStatistic){
        List<RankModel> listRank = new ArrayList<>();
        for(PersonalStatisticModel personalStatistic:listPersonalStatistic){
            if(personalStatistic.getRank()==null){
                RankModel rank = new RankModel();
                WeightModel weight = new WeightModel();
                rank.setPersonalStatistic(personalStatistic);
                rank.setWeight(weight);
                weightService.addWeight(weight);
                addRank(rank);
                listRank.add(rank);
            }
            else{
                listRank.add(personalStatistic.getRank());
            }
        }
        return listRank;
    }

    @Override
    public void addRank(RankModel rank){ rankRepository.save(rank);}

    @Override
    public void addAllRank(List<RankModel> listRank) {rankRepository.saveAll(listRank);}


    private List<List<Double>> createMatrix(List<PersonalStatisticModel> listPersonalStatistic){
        List<List<Double>> evaluationMatrix = new ArrayList<>();
        for(int i=0;i<listPersonalStatistic.size();i++){
            ArrayList<Double> playerStats = new ArrayList<>();
            playerStats.add(listPersonalStatistic.get(i).getFgmpg());
            playerStats.add(listPersonalStatistic.get(i).getFtmpg());
            playerStats.add(listPersonalStatistic.get(i).getFgperpg());
            playerStats.add(listPersonalStatistic.get(i).getTwoptperpg());
            playerStats.add(listPersonalStatistic.get(i).getThreeptperpg());
            playerStats.add(listPersonalStatistic.get(i).getFtperpg());
            playerStats.add(listPersonalStatistic.get(i).getApg());
            playerStats.add(listPersonalStatistic.get(i).getOrpg());
            playerStats.add(listPersonalStatistic.get(i).getDrpg());
            playerStats.add(listPersonalStatistic.get(i).getTopg());
            evaluationMatrix.add(playerStats);
        }
        return evaluationMatrix;
    }

    private List<List<Double>> normalizeMatrix(List<List<Double>> evaluationMatrix){
        List<Double> divisor = getDivisor(evaluationMatrix);
        for(int col=0;col<10;col++){
            for(int row=0;row<evaluationMatrix.size();row++){
                evaluationMatrix.get(row).set(col,evaluationMatrix.get(row).get(col)/divisor.get(col));
            }
        }
        return evaluationMatrix;
    }

    private List<Double> getDivisor(List<List<Double>> evaluationMatrix){
        List<Double> divisor = new ArrayList<>();
        for(int col=0;col<10;col++){
            Double squareValue = 0.0;
            for(int row=0;row<evaluationMatrix.size();row++){
                squareValue = squareValue+(evaluationMatrix.get(row).get(col)*evaluationMatrix.get(row).get(col));
            }
            divisor.add(sqrt(squareValue));
        }
        System.out.println(divisor);
        return divisor;
    }

    private List<List<Double>> weightMatrix(List<List<Double>> normalizedMatrix, List<RankModel> listRank){
        for(int row=0;row<normalizedMatrix.size();row++){
            List<Double> weight = Arrays.asList(listRank.get(row).getWeight().getC1(),listRank.get(row).getWeight().getC2(),listRank.get(row).getWeight().getC3(),listRank.get(row).getWeight().getC4(),
                    listRank.get(row).getWeight().getC5(),listRank.get(row).getWeight().getC6(),listRank.get(row).getWeight().getC7(),listRank.get(row).getWeight().getC8(),listRank.get(row).getWeight().getC9(),listRank.get(row).getWeight().getC10());
            for(int col=0;col<normalizedMatrix.get(row).size();col++){
                normalizedMatrix.get(row).set(col,normalizedMatrix.get(row).get(col)*weight.get(col));
            }
        }
        return normalizedMatrix;
    }

    private List<List<Double>> createIdealSolutionMatrix(List<List<Double>> weightedMatrix){
        List<List<Double>> idealSolutionMatrix = new ArrayList<>();
        List<Double> maxValue = new ArrayList<>();
        List<Double> minValue = new ArrayList<>();
        for(int col=0;col<10;col++){
            List<Double> alternatives = new ArrayList<>();
            for(int row=0;row<weightedMatrix.size();row++){
                alternatives.add(weightedMatrix.get(row).get(col));
            }
            maxValue.add(Collections.max(alternatives));
            minValue.add(Collections.min(alternatives));
        }
        idealSolutionMatrix.add(maxValue);
        idealSolutionMatrix.add(minValue);
        return idealSolutionMatrix;
    }

    private List<Double> calculateBestEuclideanDistance(List<List<Double>> weightedMatrix, List<List<Double>> idealSolutionMatrix){
        List<Double> bestEuclideanDistance = new ArrayList<>();
        for(int row=0;row<weightedMatrix.size();row++){
           Double calculation = 0.0;
           for(int colBen=0;colBen<9;colBen++){
               calculation=calculation+(Math.pow(weightedMatrix.get(row).get(colBen)-idealSolutionMatrix.get(0).get(colBen),2));
            }
           for(int colCost=9;colCost<10;colCost++){
               calculation=calculation+(Math.pow(weightedMatrix.get(row).get(colCost)-idealSolutionMatrix.get(1).get(colCost),2));
           }
           bestEuclideanDistance.add(sqrt(calculation));
        }
        System.out.println("bestEuclideanDistance:");
        System.out.println(bestEuclideanDistance);
        return bestEuclideanDistance;
    }

    private List<Double> calculateWorstEuclideanDistance(List<List<Double>> weightedMatrix, List<List<Double>> idealSolutionMatrix){
        List<Double> worstEuclideanDistance = new ArrayList<>();
        for(int row=0;row<weightedMatrix.size();row++){
            Double calculation = 0.0;
            for(int colBen=0;colBen<9;colBen++){
                calculation=calculation+(Math.pow(weightedMatrix.get(row).get(colBen)-idealSolutionMatrix.get(1).get(colBen),2));
            }
            for(int colCost=9;colCost<10;colCost++){
                calculation=calculation+(Math.pow(weightedMatrix.get(row).get(colCost)-idealSolutionMatrix.get(0).get(colCost),2));
            }
            worstEuclideanDistance.add(sqrt(calculation));
        }
        System.out.println("worstEuclideanDistance:");
        System.out.println(worstEuclideanDistance);
        return worstEuclideanDistance;
    }

    private List<Double> calculatePerformanceScore(List<Double> bestEuclideanDistance, List<Double> worstEuclideanDistance){
        List<Double> performanceScore = new ArrayList<>();
        for(int i=0;i<bestEuclideanDistance.size();i++){
            performanceScore.add(worstEuclideanDistance.get(i)/(bestEuclideanDistance.get(i)+worstEuclideanDistance.get(i)));
        }
        return performanceScore;
    }


    public void setRanking(List<RankModel> listRank,List<Double> performanceScore){
        for(int i=0;i<listRank.size();i++){
            listRank.get(i).setPerformance_score(performanceScore.get(i));
        }
        addAllRank(listRank);
    }

    @Override
    public List<RankModel> getRankingByPlayerAndCompetition(List<PlayerModel> listPlayer, CompetitionModel competition){
        List<RankModel> listRank = new ArrayList<>();
        for(PlayerModel player: listPlayer){
            listRank.add(personalStatisticService.getPersonalStatisticByCompetitionIdandPlayerId(competition.getId(),player.getId()).getRank());
        }
        return listRank;
    }

    @Override
    public List<RankModel> sortRank(List<RankModel> listRank){
        List<Double> value = new ArrayList<>();
        List<RankModel> sortedRank = new ArrayList<>();
        Map<Double,RankModel> mapValue = new HashMap<Double,RankModel>();
        for(RankModel rank: listRank){
            value.add(rank.getPerformance_score());
            mapValue.put(rank.getPerformance_score(),rank);
        }

        for(int i=0;i<listRank.size();i++){
            sortedRank.add(mapValue.get(Collections.max(value)));
            value.remove(Collections.max(value));
        }
        return sortedRank;
    }

    public void printMatrix(List<List<Double>> matrix){
        System.out.println("matrix: ");
        for(int row=0;row<matrix.size();row++){
           for(int col=0;col<matrix.get(row).size();col++){
               System.out.print(matrix.get(row).get(col));
               System.out.print(" | ");
            }
           System.out.println();
        }
    }



}
