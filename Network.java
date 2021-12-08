import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Network{

  private final JFrame connect = new JFrame("Connections");
  private final JPanel panel1 = new JPanel();

  public JPanel getPanel1(){
    return panel1;
  }


  static public void main(String[] args) {
    Network net = new Network();
    net.panel1.setLayout(new  FlowLayout());

    ImageIcon routerImage= new ImageIcon(Objects.requireNonNull(Network.class.getResource("router.jpg")));
    JLabel label = new JLabel(routerImage);
    label.setBounds(1000, 1000, routerImage.getIconWidth(), routerImage.getIconHeight());
    net.connect.add(label);

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


    for (Device inputDevice : inputDevices) {
      inputDevice.start();
    }

    net.connect.setSize(1650, 1000);
    net.connect.getContentPane().add(net.panel1, "North"); 
    net.connect.setVisible(true);
    net.connect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    scan.close();

  }
}