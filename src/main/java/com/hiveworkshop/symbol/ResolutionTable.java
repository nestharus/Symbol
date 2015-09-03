package com.hiveworkshop.symbol;

import java.util.Arrays;

public class ResolutionTable
{
	private final ResolutionPhase[] phases;

	public ResolutionTable(ResolutionPhase... phases)
	{
		this.phases = phases;
	}

	public ResolutionPhase[] getResolutionPhases()
	{
		return phases;
	}

	public ResolutionPhase[] getResolutionPhases(Enum<?>... phaseTypes)
	{
		Arrays.sort(phaseTypes);

		@SuppressWarnings("unchecked")
		ResolutionPhase[] phases = new ResolutionPhase[phaseTypes.length];

		for (int i = 0; i < phases.length; ++i)
		{
			phases[i] = this.phases[phaseTypes[i].ordinal()];
		}

		return phases;
	}

	public Symbol resolve(Privilege providedPrivileges, Signature signature)
	{
		Symbol closestMatch = null;
		Symbol currentMatch;
		int closestDistance = Signature.INFINITY;
		int currentDistance = 0;

		for (ResolutionPhase phase : phases)
		{
			currentMatch = phase.resolve(providedPrivileges, signature);

			if (currentMatch != null)
			{
				currentDistance = signature.distance(currentMatch.getSignature());
			}

			if (phase.isProxy())
			{

			}
			else
			{

			}

			for (Symbol symbol : phase.getSymbols(providedPrivileges, signature))
			{
				currentDistance = signature.distance(symbol.getSignature());

				if (currentDistance < closestDistance)
				{
					if (currentDistance == 0)
					{
						return symbol;
					}

					closestMatch = symbol;
					closestDistance = currentDistance;
				}
			}
		}

		return closestMatch;
	}
}
