import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Network{

  private final JFrame connect = new JFrame("Connections");
  public JFrame getFrame(){
    return connect;
  }


  static public void main(String[] args) throws InterruptedException{
    Network net = new Network();

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

      Device device = new Device(name, type, router, net);
      inputDevices.add(device);
    }


//    ImageIcon pc= new ImageIcon(Network.class.getResource("pc1.png"));
//    ImageIcon laptop= new ImageIcon(Network.class.getResource("laptop.png"));
//
//    JLabel imageLabel=new JLabel(pc);
//    JLabel imageLabel2=new JLabel(laptop);
//
//    connect.add(imageLabel);
//    connect.add(imageLabel2);


    for(int i = 0 ; i <inputDevices.size();i++){
      inputDevices.get(i).start();
    }

    net.connect.setSize(1650, 1000);
    net.connect.setLayout(new FlowLayout());
    net.connect.setVisible(true);
    net.connect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    scan.close();

  }
}