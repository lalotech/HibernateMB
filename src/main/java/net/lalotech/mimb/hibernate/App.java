package net.lalotech.mimb.hibernate;

import net.lalotech.mimb.hibernate.model.Station;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import net.lalotech.mimb.hibernate.model.Location;
import net.lalotech.mimb.hibernate.model.Route;
import net.lalotech.xmltojson.XmlToJsonParser;
import net.lalotech.xmltojson.XmlToJsonUtil;
import net.lalotech.xmltojson.model.Placemark;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )throws Exception
    {
       Session s = HibernateUtil.getSessionFactory().getCurrentSession();
       
       
       s.getTransaction().begin();
       
       //routes 
       /*
       s.persist(new Route("Linea 1"));
       s.persist(new Route("Linea 2"));
       s.persist(new Route("Linea 3"));
       s.persist(new Route("Linea 4"));
       s.persist(new Route("Linea 5"));*/
       
       
       String path = "C:\\Users\\Eduardo\\Downloads\\";     
       
       String linea1 = "Lnea1MetrobsAvdelosInsurgentes.kml";
       String linea2 = "Lnea2MetrobsEje4Sur.kml";
       String linea3 = "Lnea3MetrobsEje1poniente.kml";
       String linea4 = "Lnea4MetrobsBuenavista-SanLzaro-AeropuertoTerminales1y2.kml";
       
       String jsonpath = path+linea4;
        
       JSONObject json = XmlToJsonParser.fileNameToJsonObject(jsonpath);
       
       JSONArray places = XmlToJsonUtil.selectSubArrayJson(json, new String[]{"kml", "Document", "Placemark"});
       Type listType = new TypeToken<List<Placemark>>(){}.getType();
       List<Placemark> p = new Gson().fromJson(places.toString(), listType);
        System.out.println("places: "+p.size());
       for(Placemark l:p){
           s.persist(App.placeToStation(l));
       }
       s.getTransaction().commit();
       System.out.println("Fin");
       
    }
    public static Station placeToStation(Placemark p){
        Station s = new Station();
        s.setName(p.getName());
        System.out.println("desc: "+p.getDescription().length());
        s.setDescription(p.getDescription());
        if(p.getPoint()!=null){
            s.setLocation(new Location(
                     Double.valueOf(p.getPoint().getCoordinates().split(",")[1]),
                     Double.valueOf(p.getPoint().getCoordinates().split(",")[0]))
                    );        
        }
        s.setRoute(new Route(4));
        s.setTransfer(0);
        return s;
    }
}
