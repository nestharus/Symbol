package org.gradle;

import java.util.Arrays;

public class Symbol<T extends Enum<T>> {
	private final ResolutionPhase<T>[] phases;
	private final Signature signature;
	private final Privilege requiredPrivileges;

	@SafeVarargs
	public Symbol(Privilege requiredPrivileges, Signature signature,
			ResolutionPhase<T>... phases) {
		this.requiredPrivileges = requiredPrivileges;
		this.signature = signature;
		this.phases = phases;
	}

	@Override
	public boolean equals(Object object) {
		return object != null && object instanceof Symbol
				&& signature.equals(((Symbol<?>) object).signature);
	}

	public Symbol<T> getClosestSymbol(Privilege providedPrivileges,
			Signature signature) {
		Symbol<T> closestMatch = null;
		int closestDistance = Signature.INFINITY;
		int currentDistance = 0;

		for (ResolutionPhase<T> phase : phases) {
			for (Symbol<T> symbol : phase.getSymbols(providedPrivileges,
					signature)) {
				currentDistance = signature.distance(symbol.getSignature());

				if (currentDistance < closestDistance) {
					if (currentDistance == 0) {
						return symbol;
					}

					closestMatch = symbol;
					closestDistance = currentDistance;
				}
			}
		}

		return closestMatch;
	}

	public Privilege getRequiredPrivileges() {
		return requiredPrivileges;
	}

	public ResolutionPhase<T>[] getResolutionPhases() {
		return phases;
	}

	public ResolutionPhase<T>[] getResolutionPhases(
			@SuppressWarnings("unchecked") T... phaseTypes) {
		Arrays.sort(phaseTypes);

		@SuppressWarnings("unchecked")
		ResolutionPhase<T>[] phases = new ResolutionPhase[phaseTypes.length];

		for (int i = 0; i < phases.length; ++i) {
			phases[i] = this.phases[phaseTypes[i].ordinal()];
		}

		return phases;
	}

	public Signature getSignature() {
		return signature;
	}

	@Override
	public int hashCode() {
		return signature.hashCode();
	}
}
