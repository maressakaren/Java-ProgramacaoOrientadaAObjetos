import javax.swing.JOptionPane;

public class MyJOptionPane {
   

    public MyJOptionPane() {
    }

    public static String showInputDialog(String prhase1, String phrase2) {
        String answer = JOptionPane.showInputDialog(null, prhase1, phrase2, JOptionPane.PLAIN_MESSAGE);
        return answer;
    }

    public static int showConfirmDialog(String phrase1, String phrase2) {
    
       return JOptionPane.showConfirmDialog(null, phrase1,phrase2, JOptionPane.YES_NO_OPTION);
    }

    public static void showMessageDialog (String phrase1, String phrase2){
        JOptionPane.showMessageDialog(null, phrase1, phrase2, JOptionPane.PLAIN_MESSAGE);

    }

}
