#include <fstream>
using namespace std;
ifstream f("graf.in");
ofstream g("graf.out");

int n,m,A[101][101],X[101];

void afisare(int k)
{
    g<<"Varfurile subgrafului: ";
    for(int i=1;i<=k;i++)
        g<<X[i]<<" ";
    g<<endl;
   g<<"Matricea de adiacenta:\n";
    int P[101]={0};
    for(int i=1;i<=k;i++)
        P[X[i]]=1;
    for(int i=1;i<=n;i++)
    {
        for(int j=1;j<=n;j++)
            if(P[i] && P[j]) g<<A[i][j]<<" ";
            else g<<"0 ";
        g<<endl;
    }
}

void back(int k)
{
    for(int i=X[k-1]+1;i<=n;i++)
    {
        X[k]=i;
        afisare(k);
        back(k+1);
    }
}

int main()
{
    f>>n>>m;
    for(int i=1;i<=m;i++)
    {
        int x,y;
        f>>x>>y;
        A[x][y]=A[y][x]=1;
    }
    back(1);
    return 0;
}
