package Persitancy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olfad on 14.10.2014.
 */
public class Problem implements Serializable{
    private String characteristic;
    private String position;
    private Boolean onThrottle;
    private List<Suggestion> suggestionList;

    public Problem(String characteristic, String position, Boolean onThrottle) {
        this.characteristic = characteristic;
        this.position = position;
        this.onThrottle = onThrottle;

        suggestionList = new ArrayList<Suggestion>();
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getOnThrottle() {
        return onThrottle;
    }

    public void setOnThrottle(Boolean onThrottle) {
        this.onThrottle = onThrottle;
    }
    public void addSuggestion(Suggestion s){
        suggestionList.add(s);
    }

    public List<Suggestion> getSuggestionList(){
        return suggestionList;
    }

    public List<Suggestion> getSortedSuggestionList() {
        List<Suggestion> sortedSuggestionList = new ArrayList<Suggestion>();
        for(int i = 1; i < 10; i++){
            for(Suggestion s : suggestionList){
                if(Math.abs(s.getUseOrder()) == i){
                    sortedSuggestionList.add(s);
                }
            }
        }
        return sortedSuggestionList;
    }
}
