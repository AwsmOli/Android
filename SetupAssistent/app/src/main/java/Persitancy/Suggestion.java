package Persitancy;

import java.io.Serializable;

/**
 * Created by Oliver on 02.10.2014.
 */
public class Suggestion implements Serializable {
    private int useOrder;
    private Setting setting;
    private boolean doIncrease;

    public Suggestion(int useOrder, Setting setting, boolean doIncrease)  {
        this.useOrder = useOrder;
        this.setting = setting;
        this.doIncrease = doIncrease;
    }

    public int getUseOrder() {
        return Math.abs(useOrder);
    }

    public void setUseOrder(int useOrder) {
        this.useOrder = useOrder;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public boolean getDoIncrease() {
        return doIncrease;
    }

    public void setDoIncrease(boolean doIncrease) {
        this.doIncrease = doIncrease;
    }

    @Override
    public String toString() {
        return  doIncrease ? setting.getIncString() : setting.getDecString();
    }
}
