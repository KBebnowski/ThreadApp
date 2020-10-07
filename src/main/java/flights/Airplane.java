//Klasa zostala stworzona, poniewaz w pakiecie airports wczesniej nie tworzylem wszystkich obiektow, tylko uzylem flyweight

package flights;

import flights.airplaneService.AbstractAirplaneService;

/**
 * Created by Kamil on 15.09.2020.
 */
public class Airplane extends AbstractAirplaneService {

    private String placeOfArrival;
    private String placeOfStart;

    public void setPlaceOfArrival(String placeOfArrival) {
        this.placeOfArrival = placeOfArrival;
    }

    public void setPlaceOfStart(String placeOfStart) {
        this.placeOfStart = placeOfStart;
    }

    @Override
    public String getPlaceOfArrival() {
        return placeOfArrival;
    }

    @Override
    public String getPlaceOfStart() {
        return placeOfStart;
    }
}
