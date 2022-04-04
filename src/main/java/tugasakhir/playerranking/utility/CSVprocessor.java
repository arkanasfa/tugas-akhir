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
                playerGameStatistic.setPlayer_id(player);
                playerGameStatistic.setMinute(csvRecord.get("MINS"));
                playerGameStatistic.setPoint(Integer.parseInt(csvRecord.get("PTS")));
                playerGameStatistic.setField_goal_made(Integer.parseInt(csvRecord.get("FG").substring(0,1)));
                playerGameStatistic.setField_goal_attempted(Integer.parseInt(csvRecord.get("FG").substring(2,3)));
                playerGameStatistic.setTwo_point_made(Integer.parseInt(csvRecord.get("2P").substring(0,1)));
                playerGameStatistic.setTwo_point_attempted(Integer.parseInt(csvRecord.get("2P").substring(2,3)));
                playerGameStatistic.setThree_point_made(Integer.parseInt(csvRecord.get("3P").substring(0,1)));
                playerGameStatistic.setThree_point_attempted(Integer.parseInt(csvRecord.get("3P").substring(2,3)));
                playerGameStatistic.setFree_throw_made(Integer.parseInt(csvRecord.get("FT").substring(0,1)));
                playerGameStatistic.setFree_throw_attempted(Integer.parseInt(csvRecord.get("FT").substring(2,3)));
                playerGameStatistic.setOffRebound(Integer.parseInt(csvRecord.get("OFF")));
                playerGameStatistic.setDefRebound(Integer.parseInt(csvRecord.get("DEF")));
                playerGameStatistic.setAssist(Integer.parseInt(csvRecord.get("AST")));
                playerGameStatistic.setTurnover(Integer.parseInt(csvRecord.get("TO")));
                playerGameStatistic.setSteal(Integer.parseInt(csvRecord.get("STL")));
                playerGameStatistic.setBlock(Integer.parseInt(csvRecord.get("BLK")));
                playerGameStatistic.setFoul(Integer.parseInt(csvRecord.get("PF")));
                playerGameStatistic.setGame_id(game);
                playerGameStatistic.setClub_id(club);
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
