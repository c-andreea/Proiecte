
#include <fstream>
using namespace std;
ifstream f("date.in");
ofstream g("date.out");

int V[50][50], n,m,s, p[50], D[50];

void citire()
{
   int x,y,i;
   f>>n>>m;
   for(i=1;i<=m;i++)
   {
       f>>x>>y;
       V[x][0]++; V[x][V[x][0]]=y;
       V[y][0]++; V[y][V[y][0]]=x;
   }
   f>>s;
}

void BF(int s)
{
    int st,dr,i,x[50],j;
    st=dr=1;
    p[s]=1;
    x[1]=s;
    D[s]=0;
    while(st<=dr)
    {
        for(i=1; i<=V[x[st]][0]; i++)
         {
            j=V[x[st]][i];
            if(!p[j])
                {
                    dr++;
                    x[dr]=j;
                    p[j]=1;
                    D[j]=D[x[st]]+1;
                }
         }
         st++;
    }
    for(i=1;i<=dr;i++) g<<s<<"->"<<i<<":"<<D[i]<<endl;
}

int main()
{
    citire();
    BF(s);
    f.close();
    g.close();
    return 0;
}
