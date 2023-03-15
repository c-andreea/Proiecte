#include <fstream>
using namespace std;
ifstream f("date.in");
ofstream g("date.out");
int A[50][50],n,m;
int x[50],p[50];

int culori(int s)
{
    int st,dr,i,x[50],j,c=1,maxx,t,gasit;
    st=dr=1;
    p[s]=1;
    x[1]=s;
    while(st<=dr)
    {
        for(i=1; i<=n; i++)
         {
            if(A[x[st]][i] && !p[i])
                {
                    dr++;
                    x[dr]=i;
                    maxx=0;
                    for(int k=1; k<=n; k++)
                        if(A[i][k] && p[k]>maxx) maxx=p[k];
                    t=1;
                    do {
                        gasit=0;
                        for(int k=1; k<=n; k++)
                        if(A[i][k] && p[k]==t) gasit=1;
                        if(gasit) t++;
                    }
                    while(t<=maxx && gasit);
                    if(gasit) p[i]=maxx+1;
                    else p[i]=t;
                }
         }
         st++;
    }
    for(i=1;i<=dr;i++) if(p[x[i]]>c) c=p[x[i]];
    return c;
}

int main()
{
    int x,y,c,maxx=0;
    f>>n>>m;
    for(int i=1;i<=m;i++)
    {
        f>>x>>y;
        A[x][y]=A[y][x]=1;
    }
    for(int i=1;i<=n;i++)
    if(!p[i])
        { c=culori(i);
          if(c>maxx) maxx=c;
        }
    g<<maxx<<endl;
    for(int i=1;i<=maxx;i++)
    {
        g<<"culoarea "<<i<<": ";
        for(int j=1;j<=n;j++)
            if(p[j]==i) g<<j<<" ";
        g<<endl;
    }
    f.close();
    g.close();
    return 0;
}
