import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import static java.lang.Thread.sleep;

public class Router {
  private final Vector<Device> connectionsList;
  private final Semaphore semaphore;
  private final FileWriter file;

  public Router(int connectionsNum) throws IOException {
    semaphore = new Semaphore(connectionsNum);
    connectionsList = new Vector<>(connectionsNum);

    for (int i = 0 ; i < connectionsNum; i++){
      connectionsList.add(i, null);
    }
    file = new FileWriter("OutputLog.txt",true);
  }

  public synchronized void fill(Device device) throws InterruptedException, IOException {
    for (int i = 0 ; i < connectionsList.size(); i++){
      if(connectionsList.get(i) == null){
        connectionsList.add(i, device);
        //Connection 1: C1 Occupied
        System.out.println("Connection " + (i+1) + ": " + device.getDeviceName() + " Occupied");
        file.append("Connection " + (i+1) + ": " + device.getDeviceName() + " Occupied"+"\n");
        sleep(1200);
        break;
      }
    }
  }

  public synchronized void releaseConnection(Device device) throws IOException {

    int index = connectionsList.indexOf(device);
    System.out.println("Connection " + (index + 1) + ": " + device.getDeviceName() + " Logged out");
    file.append("Connection " + (index + 1) + ": " + device.getDeviceName() + " Logged out"+"\n");
    connectionsList.set(index, null);

  }

  public Vector<Device> getConnectionsList(){
    return connectionsList;
  }
  public Semaphore getSemaphore(){
    return semaphore;
  }
  public FileWriter getFile(){
    return file;
  }
}
