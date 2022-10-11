import java.util.*;
import java.util.LinkedList;
public  class Algorithm_8_3 {
    private int V; // количество вершин
    private LinkedList<Integer> adj[]; //

    //конструктор
    Algorithm_8_3(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    //добавляем вершину к графу
    void addEdge(int v, int w) { adj[v].add(w); }

    // функция чтобы принтить Dfs начиная с вершин
    void DFSUtil(int v,boolean visited[])
    {
        // делаем ноду как посещенной и приним ее
        visited[v] = true;
        System.out.print(v + " ");

        int n;

        Iterator<Integer> i =adj[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited);
        }
    }

    // функция для возвращения перевернутого графа
    Algorithm_8_3 getTranspose()
    {
        Algorithm_8_3 g = new Algorithm_8_3(V);
        for (int v = 0; v < V; v++)
        {
            // Повторяется для всех вершин, смежных с этой вершиной
            Iterator<Integer> i =adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }

    void fillOrder(int v, boolean visited[], Stack stack)
    {
        visited[v] = true;

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
                fillOrder(n, visited, stack);
        }
// Все вершины, доступные из v, уже обработаны,
// помещаем v в стек
        stack.push(new Integer(v));
    }

    // Основная функция, которая находит и печатает все строго
    // подключенные компоненты
    void printSCCs()
    {
        Stack stack = new Stack();

        // Пометить все вершины как не посещенные (для первого DFS)
        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;

        // Заполните вершины в стеке в соответствии с их завершением
        // раз
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);

        // создаем новый перевернутый граф
        Algorithm_8_3 gr = getTranspose();

        for (int i = 0; i < V; i++)
            visited[i] = false;

        while (stack.empty() == false)
        {
            int v = (int)stack.pop();

// Вывести сильно связанный компонент выскочившей вершины
    if (visited[v] == false)
            {
                gr.DFSUtil(v, visited);
                System.out.println();
            }
        }
    }

    public static void main(String args[])
    {
        Algorithm_8_3 g = new Algorithm_8_3(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Following are strongly connected components "+
                "in given graph ");
        g.printSCCs();
    }
}
