package com.hiveworkshop.symbol;

import java.util.Set;
import java.util.Stack;

import com.google.common.collect.ImmutableSet;
import com.hiveworkshop.symbol.privilege.Privilege;

public abstract class ResolutionPhase
{
	protected static final Set<Symbol> emptySet = ImmutableSet.of();

	private final Privilege requiredPrivileges;

	protected ResolutionPhase(Privilege requiredPrivileges)
	{
		this.requiredPrivileges = requiredPrivileges;
	}

	public abstract boolean addSymbol(Symbol symbol);

	public Privilege getRequiredPrivileges()
	{
		return requiredPrivileges;
	}

	public abstract Set<Symbol> getSymbols(Privilege providedPrivileges, Signature signature);

	public abstract boolean isProxy();

	public abstract boolean removeSymbol(Symbol symbol);

	public abstract Symbol resolve(Privilege providedPrivileges, Signature signature, Stack<Symbol> path);
}