package org.gradle;

import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class SetResolutionPhase<T extends Enum<T>> extends ResolutionPhase<T> {
	private final Set<Symbol<T>> symbols;

	public SetResolutionPhase(Privilege requiredPrivileges) {
		super(requiredPrivileges);
		symbols = new LinkedHashSet<Symbol<T>>();
	}

	@Override
	public boolean addSymbol(Symbol<T> symbol) {
		return symbols.add(symbol);
	}

	@Override
	public ImmutableSet<Symbol<T>> getSymbols(Privilege providedPrivileges,
			Signature signature) {
		ImmutableSet.Builder<Symbol<T>> builder = ImmutableSet.builder();

		if (!this.getRequiredPrivileges().accepts(providedPrivileges)) {
			return ImmutableSet.of();
		}

		for (Symbol<T> symbol : symbols) {
			if (signature
					.distance(symbol.getSignature()) != Signature.INFINITY) {
				builder.add(symbol);
			}
		}

		return builder.build();
	}

	@Override
	public boolean removeSymbol(Symbol<T> symbol) {
		return symbols.remove(symbol);
	}

}
