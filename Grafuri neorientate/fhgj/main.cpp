#include <iostream>
#include <fstream>
#define NN 1005
using namespace std;

ifstream f("graf.in");


int n, info[NN], s[NN], d[NN];


void citire()
{
    f >> n;
    for(int i =1 ; i <= n ; ++i)
        f>> info[i] >> s[i] >> d[i];
}
void rsd(int k)
{ cout<<info[k]<<" ";
 if(s[k]) rsd(s[k]);
 if(d[k]) rsd(d[k]);}

int main()
{
    citire();
    rsd(4); return 0;
}
