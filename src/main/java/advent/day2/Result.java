package advent.day2;


import lombok.Builder;

@Builder
public class Result{
    public Integer power;
    public  Integer gameNumber;


    public Result merge(Result result){
        this.power += result.power;
        this.gameNumber += result.gameNumber;
        return this;
    }
}