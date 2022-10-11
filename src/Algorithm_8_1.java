import java.util.*;
public class Algorithm_8_1 {

    // Переменная для отслеживания времени
    static int time = 1;

    // Функция для выполнения DFS, начиная с узла u
    static void dfs(int u, ArrayList<ArrayList<Integer> > aList,
                    int pre[], int post[], int vis[])
    {

        // Сохранение предварительного номера всякий раз, когда нода попадает в стак с рекурсией
        pre[u] = time;


        time++;
        vis[u] = 1;
        for (int v : aList.get(u)) {
            if (vis[v] == 0)
                dfs(v, aList, pre, post, vis);
        }

        //Сохранение предварительного номера всякий раз, когда нода выходит из  стака с рекурсией
        post[u] = time;
        time++;
    }

    public static void main(String args[])
    {

        // кол-во нод в графе
        int n = 6;

        // список смежности
        ArrayList<ArrayList<Integer> > aList
                = new ArrayList<ArrayList<Integer> >(n + 1);
        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            aList.add(list);
        }
        aList.add(new ArrayList<Integer>());
        int pre[] = new int[n + 1];
        int post[] = new int[n + 1];

        int vis[] = new int[n + 1];

        // края
        aList.get(1).add(2);
        aList.get(2).add(1);
        aList.get(2).add(4);
        aList.get(4).add(2);
        aList.get(1).add(3);
        aList.get(3).add(1);
        aList.get(3).add(4);
        aList.get(4).add(3);
        aList.get(3).add(5);
        aList.get(5).add(3);
        aList.get(5).add(6);
        aList.get(6).add(5);

        // DFS начинается с 1
        dfs(1, aList, pre, post, vis);

        // кол-во нод в графе
        for (int i = 1; i <= n; i++)
            System.out.println("Node " + i + " Pre number "
                    + pre[i] + " Post number " + post[i]);
    }
}
