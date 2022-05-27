package tugasakhir.playerranking.utility;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;
import tugasakhir.playerranking.model.PlayerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVprocessor {

    private static final String TYPE = "text/csv";

    public static boolean isCSV(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<PlayerGameStatisticModel> csvToPlayerGameStatisticModel(InputStream inputStream, List<PlayerModel> listPlayer, GameModel game, ClubModel club){
        try{
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(inputStream,"UTF8"));
            CSVParser parser = new CSVParser(csvReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<PlayerGameStatisticModel> statistics = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = parser.getRecords();
            for(CSVRecord csvRecord:csvRecords){
                PlayerGameStatisticModel playerGameStatistic = new PlayerGameStatisticModel();
                PlayerModel player = getPlayerFromList(listPlayer,csvRecord.get("NO."));
                playerGameStatistic.setMinute(csvRecord.get("MINS"));
                playerGameStatistic.setPoint(Integer.parseInt(csvRecord.get("PTS")));
                String[] fieldGoal = csvRecord.get("FG").split("-");
                String[] twoPoint = csvRecord.get("2P").split("-");
                String[] threePoint = csvRecord.get("3P").split("-");
                String[] freeThrow = csvRecord.get("FT").split("-");
                playerGameStatistic.setField_goal_made(Integer.parseInt(fieldGoal[0]));
                playerGameStatistic.setField_goal_attempted(Integer.parseInt(fieldGoal[1]));
                if(playerGameStatistic.getField_goal_attempted()!=0){
                    playerGameStatistic.setField_goal_percentage(Double.parseDouble(fieldGoal[0])/Double.parseDouble(fieldGoal[1]));
                }
                else{
                    playerGameStatistic.setField_goal_percentage(0.0);
                }
                playerGameStatistic.setTwo_point_made(Integer.parseInt(twoPoint[0]));
                playerGameStatistic.setTwo_point_attempted(Integer.parseInt(twoPoint[1]));
                if(playerGameStatistic.getTwo_point_attempted()!=0){
                    playerGameStatistic.setTwo_point_percentage(Double.parseDouble(twoPoint[0])/Double.parseDouble(twoPoint[1]));
                }
                else{
                    playerGameStatistic.setTwo_point_percentage(0.0);
                }
                playerGameStatistic.setThree_point_made(Integer.parseInt(threePoint[0]));
                playerGameStatistic.setThree_point_attempted(Integer.parseInt(threePoint[1]));
                if(playerGameStatistic.getThree_point_attempted()!=0){
                    playerGameStatistic.setThree_point_percentage(Double.parseDouble(threePoint[0])/Double.parseDouble(threePoint[1]));
                }
                else{
                    playerGameStatistic.setThree_point_percentage(0.0);
                }
                playerGameStatistic.setFree_throw_made(Integer.parseInt(freeThrow[0]));
                playerGameStatistic.setFree_throw_attempted(Integer.parseInt(freeThrow[1]));
                if(playerGameStatistic.getFree_throw_attempted()!=0){
                    playerGameStatistic.setFree_throw_percentage(Double.parseDouble(freeThrow[0])/Double.parseDouble(freeThrow[1]));
                }
                else{
                    playerGameStatistic.setFree_throw_percentage(0.0);
                }
                playerGameStatistic.setOffRebound(Integer.parseInt(csvRecord.get("OFF")));
                playerGameStatistic.setDefRebound(Integer.parseInt(csvRecord.get("DEF")));
                playerGameStatistic.setTotalRebound(Integer.parseInt(csvRecord.get("REB")));
                playerGameStatistic.setAssist(Integer.parseInt(csvRecord.get("AST")));
                playerGameStatistic.setTurnover(Integer.parseInt(csvRecord.get("TO")));
                playerGameStatistic.setSteal(Integer.parseInt(csvRecord.get("STL")));
                playerGameStatistic.setBlock(Integer.parseInt(csvRecord.get("BLK")));
                playerGameStatistic.setFoul(Integer.parseInt(csvRecord.get("PF")));
                playerGameStatistic.setFoulon(Integer.parseInt(csvRecord.get("FLS ON")));
                playerGameStatistic.setGame_id(game);
                playerGameStatistic.setClub_id(club);
                playerGameStatistic.setPlayer_id(player);
                statistics.add(playerGameStatistic);
            }
            parser.close();
            return statistics;


        }catch(IOException ex){
            throw new RuntimeException("error: "+ ex.getMessage());
        }
    }

    private static PlayerModel getPlayerFromList(List<PlayerModel> listPlayer, String number){
        for(PlayerModel player : listPlayer){
            if(player.getNumber().equals(number)){
                return player;
            }
        }
        return null;
    }

}
