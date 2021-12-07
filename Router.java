import java.util.*;
import static java.lang.Thread.sleep;

public class Router {
  private Vector<Device> connectionsList;
  private Semaphore semaphore;

  public Router(int connectionsNum){
    semaphore = new Semaphore(connectionsNum);
    connectionsList = new Vector<Device>(connectionsNum);

    for (int i = 0 ; i < connectionsNum; i++){
      connectionsList.add(i, null);
    }
  }

  public synchronized void fill(Device device) throws InterruptedException{
    for (int i = 0 ; i < connectionsList.size(); i++){
      if(connectionsList.get(i) == null){
        connectionsList.add(i, device);
        //Connection 1: C1 Occupied
        System.out.println("Connection " + (i+1) + ": " + device.getDeviceName() + " Occupied");
        sleep(1500);
        break;
      }
    }
  }

  public synchronized void releaseConnection(Device device){

    int index = connectionsList.indexOf(device);
    System.out.println("Connection " + (index + 1) + ": " + device.getDeviceName() + " Logged out");
    connectionsList.set(index, null);

  }

  public Vector<Device> getConnectionsList(){
    return connectionsList;
  }
  public Semaphore getSemaphore(){
    return semaphore;
  }
}
