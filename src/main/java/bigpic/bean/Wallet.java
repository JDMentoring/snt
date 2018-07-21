package bigpic.bean;

import java.util.*;

public class Wallet {
    private String[] coins = {"1c", "5c", "25c", "10c"};
    private String[] currency = {"10$", "20$", "50$", "100$"};
    private String[] bills = {"pizza - 20$", "coffee - 2$", "taxi - 12$"};


    public String[] getCoins() {
        return coins;
    }

    public List getCurrency(){
        return Arrays.asList(currency);
    }

    public Set getBills(){
        return new HashSet(Arrays.asList(bills));
    }


}
