package threading_in_out;

public class ThreadExit implements Runnable {
    @Override
    public void run() {
        String []string = {
                "--------------- End Program --------------",
                "",
                "     Have",
                "                A",
                "                         Nice",
                "                                   Day !!!",
                "",
                "-------------------------------------------"};
        for (int i =0 ;i< string.length; i++){
            try{
                Thread.sleep(400);
            }catch (InterruptedException ex ){
                ex.printStackTrace();
            }
            System.out.println(string[i]);
        }
        System.exit(0);
    }
}
