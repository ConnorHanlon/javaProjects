package planetwars.strategies;

import planetwars.publicapi.*;

import java.util.*;


public class MyStrategy implements IStrategy {
    private List<IVisiblePlanet> ivisPlanets;
    private List<IVisiblePlanet> attackingPlanets = new ArrayList<>();
    private HashMap<Integer, IVisiblePlanet> myPlanets = new HashMap<>();
    private HashMap<Integer, IVisiblePlanet> neutralPlanets = new HashMap<>();
    private HashMap<Integer, IVisiblePlanet> enemyPlanets = new HashMap<>();
    private HashMap<Integer, IVisiblePlanet> safePlanets = new HashMap<>();

    public void takeTurn(List<IPlanet> planets, IPlanetOperations planetOps, Queue<IEvent> eventsToExecute) {
        ivisPlanets = determineIVis(planets);
        for (IVisiblePlanet planet : ivisPlanets) {
            updatePlanetStatus(planet);
        }
        for (IVisiblePlanet planet : ivisPlanets) {
            sendODST(planet, planetOps, eventsToExecute);
        }
    }

    /**
     * Sends people to desired planets. Highest priority is sending to neutral planet, second highest is to enemy planet.
     * @param planet planet to ship people to other destination.
     * @param planetOps planet operations interface used for transferring people between planets.
     * @param eventsToExecute event queue that loads moves in a turn.
     */
    public void sendODST(IVisiblePlanet planet, IPlanetOperations planetOps, Queue<IEvent> eventsToExecute) {
        List<IVisiblePlanet> destinations = destination(planet);
        long availablePop = planet.getPopulation() / 2;
        for (IVisiblePlanet d : destinations) {
            if (d.getOwner() == Owner.NEUTRAL) {
                long amount = (long) (availablePop * 0.1);
                if (availablePop != 0) {
                    eventsToExecute.add(planetOps.transferPeople(planet, d, amount == 0 ? 1 : amount));
                    availablePop -= amount == 0 ? 1 : amount;
                }
            } else if (d.getOwner() == Owner.OPPONENT) {
                if (availablePop > (1.1) * d.getSize() / 4) {
                    eventsToExecute.add(planetOps.transferPeople(planet, d, availablePop));
                    availablePop = 0;
                } else {
                    callReinforcements(planet, planetOps, eventsToExecute);
                }
            }
        }
    }

    /**
     * This method is called when a planet does not have enough people to attack an enemy planet. The planet calls
     * surrounding safe planets and has additional people sent from these safe planets.
     */
    public void callReinforcements(IVisiblePlanet planet, IPlanetOperations planetOps, Queue<IEvent> eventsToExecute) {
        List<IVisiblePlanet> safe = new ArrayList<>();
        Set<IEdge> neighbors = planet.getEdges();
        for (IEdge n : neighbors) {
            if (safePlanets.containsKey(n.getDestinationPlanetId())) {
                safe.add(safePlanets.get(n.getDestinationPlanetId()));
            }
        }
        for (IVisiblePlanet s : safe) {
            long amount = 0;
            if (s.getPopulation() >= s.getSize() / 2) {
                amount = s.getPopulation() / 2;
            }
            if (s.getPopulation() - amount > 1) {
                eventsToExecute.add(planetOps.transferPeople(s, planet, amount));
            }
        }
    }


    /**
     * Creates a list of neutral and enemy planets surrounding a particular planet.
     * @param planet the planet sending people.
     * @return list pf neutral and enemy neighboring planets.
     */
    public List<IVisiblePlanet> destination(IVisiblePlanet planet) {
        List<IVisiblePlanet> dest = new ArrayList<>();
        Set<IEdge> neighbors = planet.getEdges();
        for (IEdge n : neighbors) {
            if (neutralPlanets.containsKey(n.getDestinationPlanetId())) {
                dest.add(neutralPlanets.get(n.getDestinationPlanetId()));
            } else if (enemyPlanets.containsKey(n.getDestinationPlanetId())) {
                dest.add(enemyPlanets.get(n.getDestinationPlanetId()));
            }
        }
        return dest;
    }

    /**
     * Checks a planet to see whether it is already accounted for. If it is accounted for in either the
     * neutralPlanet or myPlanet list, the planet is checked for safety to determine if the safety level has changed.
     * If the safety has changed, the planet is moved to the correct list of either safePlanets or attackingPlanets.
     *
     * @param planet planet to be checked and updated.
     */
    public void updatePlanetStatus(IVisiblePlanet planet) {
        if (planet.getOwner() == Owner.SELF) {
            if (!myPlanets.containsKey(planet.getId())) {
                myPlanets.put(planet.getId(), planet);
                if (isSafe(planet)) {
                    if (attackingPlanets.contains(planet)) {
                        safePlanets.put(planet.getId(), planet);
                        attackingPlanets.remove(planet);
                    }
                } else {
                    if (!safePlanets.containsKey(planet.getId())) {
                        attackingPlanets.add(planet);
                        safePlanets.remove(planet);
                    }
                }
            } else {
                if (isSafe(planet)) {
                    safePlanets.put(planet.getId(), planet);
                } else {
                    attackingPlanets.add(planet);
                }
            }
            updateOldDict(myPlanets, planet);
        } else if (planet.getOwner() == Owner.NEUTRAL) {
            if (!neutralPlanets.containsKey(planet.getId())) {
                neutralPlanets.put(planet.getId(), planet);
                updateOldDict(neutralPlanets, planet);
            }
        } else {
            if (!enemyPlanets.containsKey(planet.getId())) {
                enemyPlanets.put(planet.getId(), planet);
                updateOldDict(enemyPlanets, planet);
            }
        }
    }

    /**
     * When a planet is moved from one HashMap to another, this method deletes the old entry in the previous HashMap.
     *
     * @param newLoc the new HashMap that the planet is being moved to.
     * @param planet the planet that is moving from one HashMap to another.
     */
    public void updateOldDict(HashMap newLoc, IVisiblePlanet planet) {
        List<HashMap<Integer, IVisiblePlanet>> toCheck = new ArrayList<>();
        toCheck.add(neutralPlanets);
        toCheck.add(myPlanets);
        toCheck.add(enemyPlanets);
        toCheck.remove(newLoc);
        for (HashMap<Integer, IVisiblePlanet> map : toCheck) {
            if (map.containsKey(planet.getId())) {
                map.remove(planet.getId());
            }
        }
    }


    /**
     * Determines the IVisible planets in the playing field.
     *
     * @param planets list of all planets in the playing field.
     * @return list of IVisible planets.
     */
    public List<IVisiblePlanet> determineIVis(List<IPlanet> planets) {
        List<IVisiblePlanet> ivis = new ArrayList<>();
        for (IPlanet planet : planets) {
            if (planet instanceof IVisiblePlanet) {
                ivis.add((IVisiblePlanet) planet);
            }
        }
        return ivis;
    }

    /**
     * Determines if planet has an enemy planet adjacent to itself, and if it
     * does, the planet is not safe.
     *
     * @param planet the planet to be checked
     * @return boolean true if planet is safe, false if not safe
     */
    public boolean isSafe(IVisiblePlanet planet) {
        Set<IEdge> neighbors = planet.getEdges();
        for (IEdge edge : neighbors) {
            if (!myPlanets.containsKey(edge.getDestinationPlanetId())) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return "Connor's Strategy";
    }

    public boolean compete() {
        return false;
    }
}
