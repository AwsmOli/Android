package eu.isawsm.setupassistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Persitancy.CSVParser;
import Persitancy.Problem;

/**
 * Created by olfad on 31.10.2014.
 */
public class Presenter implements Serializable {
    private ArrayList<Problem> problems;
    private ArrayList<String> choices;

    public Presenter(ArrayList<Problem> problems){
        this.problems = problems;
        choices = new ArrayList<String>();
    }

    public ArrayList<Problem> getProblems(){
        return problems;
    }

    public ArrayList<String> getChoices(){
        return choices;
    }

    public void reset(){
        choices = new ArrayList<String>();
    }

    public void filterProblems(String choice) {
        getChoices().add(choice);
        ArrayList<Problem> newList = new ArrayList<Problem>();
        for (Problem problem : getProblems()) {
            if(problem.getCharacteristic().equals(choice)){
                newList.add(problem);
            }
        }
        problems = newList;
    }

    public String getTitle() {
        String title = "";
        for(String choice : choices){
            title += getLocalizedString(choice) + " ";
        }
        return title;
    }

    public String getLastChoice(){
        return getChoices().get(getChoices().size() - 1);
    }

    private String getLocalizedString(String problematicCharacteristic) {
        if(problematicCharacteristic.equals(CSVParser.UNDERSTEER)){
            return String.valueOf(R.string.understeer);
        } else if (problematicCharacteristic.equals(CSVParser.OVERSTEER)){
            return String.valueOf(R.string.oversteer);
        } else if(problematicCharacteristic.equals(CSVParser.STEERING_RESPONSE)){
            return String.valueOf(R.string.steeringResponse);
        } else if(problematicCharacteristic.equals(CSVParser.STRAIGHT_LINE_STABILITY)) {
            return String.valueOf(R.string.stability);
        } else if(problematicCharacteristic.equals(CSVParser.TRACTION_ROLL)){
            return String.valueOf(R.string.roll);
        }
        return null;
    }

    public void filterProblems(Boolean button) {
        
    }
}
