package Persitancy;

import java.io.Serializable;

/**
 * Created by olfad on 14.10.2014.
 */
public class Setting implements Serializable {
    private String name;
    private String incPrefix;
    private String decPrefix;
    /*Features:
        Description
        Icon
     */

    public String getIncString(){
        return incPrefix + " " + name;
    }

    public String getDecString(){
        return decPrefix + " " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncPrefix() {
        return incPrefix;
    }

    public void setIncPrefix(String incPrefix) {
        this.incPrefix = incPrefix;
    }

    public String getDecPrefix() {
        return decPrefix;
    }

    public void setDecPrefix(String decPrefix) {
        this.decPrefix = decPrefix;
    }
}
