using Algorithms.Week1.Interfaces;

namespace Algorithms.Week1
{
    /// <summary>
    /// Initialise: O(N)
    /// Union: O(N)
    /// Find: O(N)
    /// </summary>
    public class QuickUnion: IDynamicConnectivity
    {
        private readonly int[] _items;
        public QuickUnion(int numberOfObjects)
        {
            _items = new int[numberOfObjects];
            for (int i = 0; i < numberOfObjects; i++)
            {
                _items[i] = i;
            }
        }

        private int GetRoot(int index)
        {
            while (_items[index] != index)
            {
                index = _items[index];
            }

            return index;
        }
        
        /// <summary>
        /// This Union will take O(N) time
        /// </summary>
        public void Union(int p, int q)
        {
            int rootOfP = GetRoot(p);
            int rootOfQ = GetRoot(q);
            _items[rootOfP] = rootOfQ;
        }

        /// <summary>
        /// This Find will take O(N) time
        /// </summary>
        public bool Connected(int p, int q)
        {
            return GetRoot(p) == GetRoot(q);
        }
    }
}
