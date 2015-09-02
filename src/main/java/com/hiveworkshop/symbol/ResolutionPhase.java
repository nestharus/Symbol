package com.hiveworkshop.symbol;

import com.google.common.collect.ImmutableSet;

public abstract class ResolutionPhase<T extends Enum<T>>
{
	private final Privilege requiredPrivileges;

	public ResolutionPhase(Privilege requiredPrivileges)
	{
		this.requiredPrivileges = requiredPrivileges;
	}

	public abstract boolean addSymbol(Symbol<T> symbol);

	public Privilege getRequiredPrivileges()
	{
		return requiredPrivileges;
	}

	public abstract ImmutableSet<Symbol<T>> getSymbols(Privilege providedPrivileges, Signature signature);

	public abstract boolean removeSymbol(Symbol<T> symbol);
}