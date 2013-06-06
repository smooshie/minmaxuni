public class PINKoodit {
    public static void main(String[] args) {
        int sarja;
        for (int i = 1; i <= 9; i++)
        {
            for (int j = 1; j <= 9; j++)
            {
                for (int k = 1; k <= 9; k++)
                {
                    for (int l = 1; l <= 9; l++)
                    {
                        sarja = 1000 * i + 100 * j + 10 * k + l;
                        System.out.println(sarja);
                    }
                }
            }
        }
    }
 }
