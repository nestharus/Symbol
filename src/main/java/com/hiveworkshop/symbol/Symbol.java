package com.hiveworkshop.symbol;

import com.hiveworkshop.symbol.privilege.Privilege;

public abstract class Symbol
{
	private ResolutionTable	resolutionTable;
	private Signature		signature;
	private Privilege		requiredPrivileges;

	protected Symbol()
	{
		resolutionTable = null;
		signature = null;
		requiredPrivileges = null;
	}

	protected void build(Privilege requiredPrivileges, Signature signature, ResolutionPhase... phases) throws Exception
	{
		if (resolutionTable != null)
		{
			throw new Exception("Build May Only Be Called Once Per Symbol");
		}

		if (requiredPrivileges == null)
		{
			this.requiredPrivileges = requiredPrivileges;
		}
		this.signature = signature;
		this.resolutionTable = new ResolutionTable(phases);
	}

	@Override
	public boolean equals(Object object)
	{
		return object != null && object instanceof Symbol && signature.equals(((Symbol) object).signature);
	}

	public Privilege getRequiredPrivileges()
	{
		return requiredPrivileges;
	}

	public Signature getSignature()
	{
		return signature;
	}

	@Override
	public int hashCode()
	{
		return signature.hashCode();
	}
}
