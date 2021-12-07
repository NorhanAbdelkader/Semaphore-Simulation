import java.util.*;

public class Network{

  static public void main(String[] args) throws InterruptedException{
    Scanner scan = new Scanner(System.in);
    int connections, devices;
    System.out.println("What is the number of WI-FI Connections?");
    connections = scan.nextInt();
    Router router = new Router(connections);

    System.out.println("What is the number of devices Clients want to connect?");
    devices = scan.nextInt();

    ArrayList<Device> inputDevices = new ArrayList<>(devices);

    for(int i = 0 ; i < devices ;i++){
      System.out.print("Enter Device name: ");
      String name = scan.next();
      System.out.print("Enter Device type: ");
      String type = scan.next();

      Device device = new Device(name, type, router);
      inputDevices.add(device);
    }
    for(int i = 0 ; i <inputDevices.size();i++){
      inputDevices.get(i).start();
    }

    scan.close();

  }
}