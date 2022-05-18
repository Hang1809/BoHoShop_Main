package threading_in_out;

public class ThreadLogin implements Runnable {
    @Override
    public void run() {
        String[] list = {"****------------- WEL ------------****",
                        "                 COME                ",
                        "                  To                  ",
                        "*************  BOHO Shop  *************"};
        for (int i =0; i< list.length; i++){
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(list[i]);
        }
    }
}
