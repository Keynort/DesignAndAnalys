import java.util.*;

class Algorithm_8_2 {

    static int time;

    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void APUtil(ArrayList<ArrayList<Integer> > adj, int u,
                       boolean visited[], int disc[], int low[],
                       int parent, boolean isAP[])
    {
        // считаем кол-во детей  в дереве DFS
        int children = 0;

        // помечяем ноду как уже посещенную
        visited[u] = true;

        // иницизирую время и маленькое значние
        disc[u] = low[u] = ++time;

        // проходим через все вершины
        for (Integer v : adj.get(u)) {
            // Если v еще не посещен, то делаем его дочерним элементом u
            // в дереве DFS и повторяем для него
            if (!visited[v]) {
                children++;
                APUtil(adj, v, visited, disc, low, u, isAP);

                // Проверяем, имеет ли поддерево с корнем v
                // соединение с одним из предков u
                low[u] = Math.min(low[u], low[v]);

                // Если u не является корневым и низкое значение одного из
                // его дочерних элементов больше, чем значение обнаружения u.
                if (parent != -1 && low[v] >= disc[u])
                    isAP[u] = true;
            }

        // Обновляем низкое значение u для вызовов родительской функции.
                low[u] = Math.min(low[u], disc[v]);
        }

        // Если u является корнем дерева DFS и имеет двух или более дочерних элементов.
        if (parent == -1 && children > 1)
            isAP[u] = true;
    }

    static void AP(ArrayList<ArrayList<Integer> > adj, int V)
    {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        int time = 0, par = -1;

        // Добавляем этот цикл так, чтобы
// код работал, даже если нам дан
        // отключенный график
        for (int u = 0; u < V; u++)
            if (visited[u] == false)
                APUtil(adj, u, visited, disc, low, par, isAP);

        for (int u = 0; u < V; u++)
            if (isAP[u] == true)
                System.out.print(u + " ");
        System.out.println();
    }

    public static void main(String[] args)
    {

        //создаю пример графа
        int V = 5;
        ArrayList<ArrayList<Integer> > adj1 =
                new ArrayList<ArrayList<Integer> >(V);
        for (int i = 0; i < V; i++)
            adj1.add(new ArrayList<Integer>());
        addEdge(adj1, 1, 0);
        addEdge(adj1, 0, 2);
        addEdge(adj1, 2, 1);
        addEdge(adj1, 0, 3);
        addEdge(adj1, 3, 4);
        System.out.println("Articulation points in first graph");
        AP(adj1, V);

        // второй пример графа
        V = 4;
        ArrayList<ArrayList<Integer> > adj2 =
                new ArrayList<ArrayList<Integer> >(V);
        for (int i = 0; i < V; i++)
            adj2.add(new ArrayList<Integer>());

        addEdge(adj2, 0, 1);
        addEdge(adj2, 1, 2);
        addEdge(adj2, 2, 3);

        System.out.println("Articulation points in second graph");
        AP(adj2, V);

    }
}
