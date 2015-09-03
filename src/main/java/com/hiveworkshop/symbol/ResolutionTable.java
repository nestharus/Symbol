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

		ResolutionPhase[] phases = new ResolutionPhase[phaseTypes.length];

		for (int i = 0; i < phases.length; ++i)
		{
			phases[i] = this.phases[phaseTypes[i].ordinal()];
		}

		return phases;
	}
}
