package planetwars.strategies;

import planetwars.publicapi.IEvent;
import planetwars.publicapi.IPlanet;
import planetwars.publicapi.IPlanetOperations;
import planetwars.publicapi.IStrategy;
import java.util.*;

public class MyNewStrategy implements IStrategy {

    public void takeTurn(List<IPlanet> planets, IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute) {

    }

    public String getName() {
        return "MyNewStrategy";
    }

    public boolean compete() {
        return false;
    }

}
