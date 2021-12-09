import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class Device extends Thread{
  private final String name;
  private final String type;
  private final Router router;
  private final Network network;
  private JLabel label;


  public Device(String name, String type, Router router ,Network network){
    this.name = name;
    this.type = type;
    this.router = router;
    this.network=network;
  }

  public void logIn() throws InterruptedException, IOException {
    //- Connection 1: C1 login
    sleep(1500);
    System.out.println("Connection " + (router.getConnectionsList().indexOf(this) + 1) + ": " + this.getDeviceName() + " login");
    router.getFile().append("Connection " + (router.getConnectionsList().indexOf(this) + 1) + ": " + this.getDeviceName() + " login"+"\n");

  }

  public void performsOnlineActivity() throws InterruptedException, IOException {
    //- Connection 2: C2 performs online activity
    sleep(1500);
    System.out.println("Connection " + (router.getConnectionsList().indexOf(this) + 1) + ": " + this.getDeviceName() + " performs online activity");
    router.getFile().append("Connection " + (router.getConnectionsList().indexOf(this) + 1) + ": " + this.getDeviceName() + " performs online activity"+"\n");
  }

  public void logOut() throws InterruptedException, IOException {
    sleep(1500);
    router.releaseConnection(this);
    Vector<Device> d=router.getConnectionsList();
    boolean lastDevice=true;
    for (Device device : d) {
      if (device != null) {
        lastDevice=false;
        break;
      }
    }
    if(lastDevice) router.getFile().close();
  }

  public ArrayList<JLabel> createImages()
  {

    ArrayList<JLabel> labels= new ArrayList<>();

    if (new  ImageIcon("pc1.png") != null && new  ImageIcon("laptop.png") != null && new  ImageIcon("tablet2.png") != null && new  ImageIcon("m2.png") != null && new  ImageIcon("d.png") != null){

    ImageIcon pc= new ImageIcon("pc1.png");
    ImageIcon laptop= new ImageIcon(("laptop.png"));
    ImageIcon tablet= new ImageIcon(("tablet2.png"));
    ImageIcon mobile= new ImageIcon(("m2.png"));
    ImageIcon unknownDevice= new ImageIcon(("d.png"));


    // 1- pc 2- lap 3- tablet 4- mobile
    labels.add(new JLabel(pc));
    labels.add(new JLabel(laptop));
    labels.add(new JLabel(tablet));
    labels.add(new JLabel(mobile));
    labels.add(new JLabel(unknownDevice));

    for(int i = 0 ; i < 5; i ++){
      labels.get(i).setVisible(false);
    }
  }
    return labels;

  }
  public void showImages(){
    ArrayList<JLabel> labels = createImages();
    if(labels!=null){
      switch (type)
      {
        case "pc":
          labels.get(0).setHorizontalAlignment(SwingConstants.LEFT);
          label = labels.get(0);
          network.getPanel1().add(label);
          labels.get(0).setVisible(true);
          break;
        case "laptop":
          labels.get(1).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(1));
          labels.get(1).setVisible(true);
          label = labels.get(1);

  
          break;
        case "tablet":
          labels.get(2).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(2));
          labels.get(2).setVisible(true);
          label = labels.get(2);

  
          break;
        case "mobile":
          labels.get(3).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(3));
          labels.get(3).setVisible(true);
          label = labels.get(3);

  
          break;
        default:
          labels.get(4).setHorizontalAlignment(SwingConstants.LEFT);
          network.getPanel1().add(labels.get(4));
          labels.get(4).setVisible(true);
          label = labels.get(4);

          break;
  
      }
    }
  }

  @Override
  public void run() {
    showImages();
    try {
      router.getSemaphore().wait(this,router);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }


    try {
      Border border = BorderFactory.createLineBorder(Color.ORANGE, 5);
      router.fill(this);
      label.setBorder(border);
      logIn();
      performsOnlineActivity();
      logOut();
      label.setVisible(false);

    } 
    catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }

    router.getSemaphore().signal();
  }


  public String getDeviceName(){
    return name;
  }
  public String getType(){
    return type;
  }
}
