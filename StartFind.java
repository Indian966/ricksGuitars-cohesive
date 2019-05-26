import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StartFind {

  public static void main(String[] args) {
    // Set up Rick's inventory
    Inventory inventory = new Inventory();
    DB.initializeInventory(inventory);

    Map properties = new HashMap();
    
    Scanner scan = new Scanner(System.in);	
    
    properties.put("builder", Builder.GIBSON);
    properties.put("backWood", Wood.MAPLE);
    InstrumentSpec whatBryanLikes = new InstrumentSpec(properties);

    List matchingInstruments = inventory.search(whatBryanLikes);
    
    if (!matchingInstruments.isEmpty()) {
      System.out.println("Mister, you might like these instruments:");
      for (Iterator i = matchingInstruments.iterator(); i.hasNext(); ) {
        Instrument instrument = (Instrument)i.next();
        InstrumentSpec spec = instrument.getSpec();
        System.out.println("We have a " + spec.getProperty("instrumentType") +
          " with the following properties:");
        for (Iterator j = spec.getProperties().keySet().iterator(); 
             j.hasNext(); ) {
          String propertyName = (String)j.next();
          if (propertyName.equals("instrumentType"))
            continue;
          System.out.println("    " + propertyName + ": " +
            spec.getProperty(propertyName));
        }  
        System.out.println("  You can have this " + 
          spec.getProperty("instrumentType") + " for $" + 
          instrument.getPrice() + "\n---");
      }
    } else {
      System.out.println("Sorry, Bryan, we have nothing for you.");
    }
  }

  
}
