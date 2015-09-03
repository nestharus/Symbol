package com.hiveworkshop.symbol.privilege;

import com.google.common.collect.ImmutableSet;

// set
// has set
// composite
// left, right

public abstract class PrivilegeSet
{
	private static class AndAtomic extends Atomic
	{
		protected ImmutableSet<Privilege> set;

		public AndAtomic(ImmutableSet<Privilege> set)
		{
			super(set);
		}

		@Override
		public boolean accepts(ImmutableSet<Privilege> privileges)
		{
			return privileges.containsAll(set);
		}
	}

	private static class AndTree extends Tree
	{
		public AndTree(PrivilegeSet left, PrivilegeSet right)
		{
			super(left, right);
		}

		@Override
		public boolean accepts(ImmutableSet<Privilege> privileges)
		{
			return left.accepts(privileges) && right.accepts(privileges);
		}
	}

	private abstract static class Atomic extends PrivilegeSet
	{
		protected final ImmutableSet<Privilege> set;

		public Atomic(ImmutableSet<Privilege> set)
		{
			this.set = set;
		}
	}

	private static class OrAtomic extends Atomic
	{

		public OrAtomic(ImmutableSet<Privilege> set)
		{
			super(set);
		}

		@Override
		public boolean accepts(ImmutableSet<Privilege> privileges)
		{
			for (Privilege privilege : set)
			{
				if (privileges.contains(privilege))
				{
					return true;
				}
			}

			return false;
		}

	}

	private static class OrTree extends Tree
	{
		public OrTree(PrivilegeSet left, PrivilegeSet right)
		{
			super(left, right);
		}

		@Override
		public boolean accepts(ImmutableSet<Privilege> privileges)
		{
			return left.accepts(privileges) || right.accepts(privileges);
		}
	}

	private abstract static class Tree extends PrivilegeSet
	{
		protected final PrivilegeSet	left;
		protected final PrivilegeSet	right;

		public Tree(PrivilegeSet left, PrivilegeSet right)
		{
			this.left = left;
			this.right = right;
		}
	}

	public static PrivilegeSet and(ImmutableSet<Privilege> privileges)
	{
		return new AndAtomic(privileges);
	}

	public static PrivilegeSet and(PrivilegeSet privilege1, PrivilegeSet privilege2)
	{
		return new AndTree(privilege1, privilege2);
	}

	public static PrivilegeSet or(ImmutableSet<Privilege> privileges)
	{
		return new OrAtomic(privileges);
	}

	public static PrivilegeSet or(PrivilegeSet privilege1, PrivilegeSet privilege2)
	{
		return new OrTree(privilege1, privilege2);
	}

	public abstract boolean accepts(ImmutableSet<Privilege> privileges);
}
