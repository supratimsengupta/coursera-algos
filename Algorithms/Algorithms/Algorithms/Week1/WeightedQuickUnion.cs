using Algorithms.Week1.Interfaces;

namespace Algorithms.Week1
{
    /// <summary>
    /// The algorithm is very similar to QuickUnion but with the only difference that whenever a Union operation
    /// takes place it checks for the smaller tree of the 2 and makes the root of the larger tree as the root of the smaller tree.
    /// This avoids tall trees.
    /// </summary>
    public class WeightedQuickUnion : IDynamicConnectivity
    {
        private readonly int[] _root, _weight;

        public WeightedQuickUnion(int numberOfItems)
        {
            _root = new int[numberOfItems];
            _weight = new int[numberOfItems];
            for (int i = 0; i < numberOfItems; i++)
            {
                _root[i] = i;
                _weight[i] = 1;
            }
        }

        private int GetRootOf(int index)
        {
            while (_root[index] != index)
            {
                _root[index] = _root[_root[index]];
                index = _root[index];
            }

            return index;
        }

        private int GetWeightOf(int index)
        {
            return _weight[index];
        }

        public void Union(int p, int q)
        {
            int rootOfP = GetRootOf(p);
            int rootOfQ = GetRootOf(q);
            int weightOfP = GetWeightOf(p);
            int weightOfQ = GetWeightOf(q);

            if (weightOfP < weightOfQ)
            {
                _root[p] = rootOfQ;
                _weight[q] += weightOfP;
            }
            else
            {
                _root[q] = rootOfP;
                _weight[p] += weightOfQ;
            }

        }

        public bool Connected(int p, int q)
        {
            return GetRootOf(p) == GetRootOf(q);
        }
    }
}
