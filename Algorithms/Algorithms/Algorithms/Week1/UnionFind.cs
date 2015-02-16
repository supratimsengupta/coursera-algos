using System;
using Algorithms.Week1.Interfaces;

namespace Algorithms.Week1
{
    public class UnionFind : IDynamicConnectivity
    {
        private readonly int _numberOfObjects;

        public UnionFind(int numberOfObjects)
        {
            _numberOfObjects = numberOfObjects;
        }

        public void Union(int @from, int to)
        {
            throw new NotImplementedException();
        }

        public bool Connected(int @from, int q)
        {
            throw new NotImplementedException();
        }
    }
}
