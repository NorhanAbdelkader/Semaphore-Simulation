import java.io.IOException;

public class Semaphore {

  private int connections;
  public Semaphore(){
    connections = 0;
  }

  public Semaphore(int val){
    connections = val;
  }

  public synchronized void wait (Device device,Router router) throws IOException, InterruptedException {
    connections --;
    if (connections < 0)
    {

        System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived and waiting.");
        router.getFile().append("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived and waiting."+"\n");
        wait();

      return;
    }
    System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived.");
    router.getFile().append("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived."+"\n");
  }

  public synchronized void signal (){
    connections ++;
    if (connections <= 0)
    {
      notify();
    }
  }
}
