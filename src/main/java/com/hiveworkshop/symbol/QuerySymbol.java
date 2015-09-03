package com.hiveworkshop.symbol;

public class QuerySymbol extends Query
{

	public QuerySymbol(Symbol origin)
	{
		super(origin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean search(Signature signature)
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
					}

					closestMatch = symbol;
					closestDistance = currentDistance;
				}
			}
		}

		return false;
	}

}
