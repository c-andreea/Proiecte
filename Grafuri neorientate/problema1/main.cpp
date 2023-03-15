#include <iostream>
#include<fstream>
using namespace std;

ifstream f("bipartitcomplet.in");
ofstream g("bipartitcomplet.out");
int n,a[105][105],x[105],k,i,j;
int main()
{
f>>n>>k;
for(i=1;i<=k;i++)
{
    int p;
    f>>p;
    x[p]=1;
}for(i=1;i<=n;i++)
for(j=1;j<=n;j++)
if(x[i]!=x[j])
    a[i][j]=a[j][i]=1;
for(i=1;i<=n;i++)
{for(j=1;j<=n;j++)
    g<<a[i][j]<<" ";g<<endl;}
return 0;}


