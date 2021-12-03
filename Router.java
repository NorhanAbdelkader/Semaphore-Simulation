import java.util.*;
import static java.lang.Thread.sleep;

public class Router {
  private Vector<Device> connectionsList;
  private Semaphore semaphore;
  private int WiFiConnections;

  public Router(int connectionsNum){
    WiFiConnections = connectionsNum;
    semaphore = new Semaphore(connectionsNum);
    connectionsList = new Vector<Device>(connectionsNum);

    for (int i = 0 ; i < WiFiConnections; i++){
      connectionsList.add(i, null);
    }
  }

  public synchronized void fill(Device device) throws InterruptedException{
    for (int i = 0 ; i < WiFiConnections; i++){
      if(connectionsList.get(i) == null){
        connectionsList.add(i, device);
        //Connection 1: C1 Occupied
        System.out.println("Connection " + (i+1) + ": " + device.getDeviceName() + " Occupied");
        sleep(1000);
        break;
      }
    }
  }

  public void logIn(Device device) throws InterruptedException{
    //- Connection 1: C1 login
    sleep(1000);
    System.out.println("Connection " + (connectionsList.indexOf(device) + 1) + ": " + device.getDeviceName() + " login");
  }
  public void performsOnlineActivity(Device device) throws InterruptedException{
    //- Connection 2: C2 performs online activity
    sleep(1000);
    System.out.println("Connection " + (connectionsList.indexOf(device) + 1) + ": " + device.getDeviceName() + " performs online activity");
  }
  public synchronized void logOut(Device device){

    int index = connectionsList.indexOf(device);
    System.out.println("Connection " + (index + 1) + ": " + device.getDeviceName() + " Logged out");
    connectionsList.set(index, null);

    //connectionsList.removeElement(device);
  }

  public Semaphore getSemaphore(){
    return semaphore;
  }
}
