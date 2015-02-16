namespace Algorithms.Week1.Interfaces
{
    public interface IDynamicConnectivity
    {
        void Union(int p, int q);
        bool Connected(int p, int q);
    }
}
