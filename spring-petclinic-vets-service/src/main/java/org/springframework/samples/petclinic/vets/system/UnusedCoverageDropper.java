package org.springframework.samples.petclinic.vets.system;

public class UnusedCoverageDropper {
    // 50 methods, none are called in tests
    public int add(int a, int b) { return a + b; }
    public int sub(int a, int b) { return a - b; }
    public int mul(int a, int b) { return a * b; }
    public int div(int a, int b) { return b == 0 ? 0 : a / b; }
    public int mod(int a, int b) { return b == 0 ? 0 : a % b; }
    public int max(int a, int b) { return a > b ? a : b; }
    public int min(int a, int b) { return a < b ? a : b; }
    public int abs(int a) { return a < 0 ? -a : a; }
    public int square(int a) { return a * a; }
    public int cube(int a) { return a * a * a; }
    public int pow(int a, int b) { int r = 1; for(int i=0;i<b;i++) r*=a; return r; }
    public boolean isEven(int a) { return a % 2 == 0; }
    public boolean isOdd(int a) { return a % 2 != 0; }
    public boolean isPrime(int a) { if(a<2)return false; for(int i=2;i<=a/2;i++)if(a%i==0)return false; return true; }
    public int gcd(int a, int b) { while(b!=0){int t=b;b=a%b;a=t;}return a; }
    public int lcm(int a, int b) { return a * b / gcd(a, b); }
    public int factorial(int a) { int r=1; for(int i=2;i<=a;i++) r*=i; return r; }
    public int sum(int[] arr) { int s=0; for(int v:arr)s+=v; return s; }
    public int product(int[] arr) { int p=1; for(int v:arr)p*=v; return p; }
    public int max(int[] arr) { int m=Integer.MIN_VALUE; for(int v:arr)if(v>m)m=v; return m; }
    public int min(int[] arr) { int m=Integer.MAX_VALUE; for(int v:arr)if(v<m)m=v; return m; }
    public double avg(int[] arr) { return arr.length==0?0.0:((double)sum(arr))/arr.length; }
    public int countPositive(int[] arr) { int c=0; for(int v:arr)if(v>0)c++; return c; }
    public int countNegative(int[] arr) { int c=0; for(int v:arr)if(v<0)c++; return c; }
    public int countZero(int[] arr) { int c=0; for(int v:arr)if(v==0)c++; return c; }
    public int reverse(int a) { int r=0; while(a!=0){r=r*10+a%10;a/=10;} return r; }
    public boolean isPalindrome(int a) { return a==reverse(a); }
    public int digitSum(int a) { int s=0; while(a!=0){s+=a%10;a/=10;} return s; }
    public int digitCount(int a) { int c=0; while(a!=0){c++;a/=10;} return c; }
    public int[] toDigits(int a) { int c=digitCount(a); int[] d=new int[c]; for(int i=c-1;i>=0;i--){d[i]=a%10;a/=10;} return d; }
    public int fromDigits(int[] d) { int r=0; for(int v:d)r=r*10+v; return r; }
    public int[] sort(int[] arr) { java.util.Arrays.sort(arr); return arr; }
    public int[] reverseArr(int[] arr) { int n=arr.length; int[] r=new int[n]; for(int i=0;i<n;i++)r[i]=arr[n-1-i]; return r; }
    public int[] filterEven(int[] arr) { return java.util.Arrays.stream(arr).filter(x->x%2==0).toArray(); }
    public int[] filterOdd(int[] arr) { return java.util.Arrays.stream(arr).filter(x->x%2!=0).toArray(); }
    public int[] filterPositive(int[] arr) { return java.util.Arrays.stream(arr).filter(x->x>0).toArray(); }
    public int[] filterNegative(int[] arr) { return java.util.Arrays.stream(arr).filter(x->x<0).toArray(); }
    public int[] filterZero(int[] arr) { return java.util.Arrays.stream(arr).filter(x->x==0).toArray(); }
    public int[] mapSquare(int[] arr) { return java.util.Arrays.stream(arr).map(x->x*x).toArray(); }
    public int[] mapCube(int[] arr) { return java.util.Arrays.stream(arr).map(x->x*x*x).toArray(); }
    public int[] mapAbs(int[] arr) { return java.util.Arrays.stream(arr).map(x->x<0?-x:x).toArray(); }
    public int[] mapNegate(int[] arr) { return java.util.Arrays.stream(arr).map(x->-x).toArray(); }
    public int[] mapDouble(int[] arr) { return java.util.Arrays.stream(arr).map(x->2*x).toArray(); }
    public int[] mapHalf(int[] arr) { return java.util.Arrays.stream(arr).map(x->x/2).toArray(); }
    public int[] mapInc(int[] arr) { return java.util.Arrays.stream(arr).map(x->x+1).toArray(); }
    public int[] mapDec(int[] arr) { return java.util.Arrays.stream(arr).map(x->x-1).toArray(); }
    public int[] concat(int[] a, int[] b) { int[] r=new int[a.length+b.length]; System.arraycopy(a,0,r,0,a.length); System.arraycopy(b,0,r,a.length,b.length); return r; }
    public int[] slice(int[] arr, int start, int end) { return java.util.Arrays.copyOfRange(arr,start,end); }
}