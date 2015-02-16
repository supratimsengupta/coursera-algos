using Algorithms.Week1.Interfaces;

namespace Algorithms.Week1
{
    /// <summary>
    /// Eager Algorithm
    /// Initialization: O(N)
    /// Union: O(N)
    /// Find: O(1)
    /// </summary>
    public class QuickFind : IDynamicConnectivity
    {
        private readonly int[] _items;

        public QuickFind(int numberOfObjects)
        {
            _items = new int[numberOfObjects];
            for (int i = 0; i < numberOfObjects; i++)
            {
                _items[i] = i;
            }
        }

        /// <summary>
        /// Connects 2 nodes p and q
        /// </summary>
        /// <param name="p">the node where the connection starts</param>
        /// <param name="q">the node where the connection ends</param>
        public void Union(int p, int q)
        {
            int valueAtP = _items[p];
            int valueAtQ = _items[q];

            for (int i = 0; i < _items.Length; i++)
            {
                if (_items[i] == valueAtP)
                {
                    _items[i] = valueAtQ;    
                }
                
            }
        }

        /// <summary>
        /// Checks if there is a connection between 2 nodes p and q
        /// </summary>
        /// <param name="p">the node where the connection starts</param>
        /// <param name="q">the node where the connection ends</param>
        /// <returns>True if a connection exists, False otherwise</returns>
        public bool Connected(int p, int q)
        {
            return _items[p] == _items[q];
        }
    }
}
