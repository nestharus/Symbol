package org.gradle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class MapResolutionPhase<T extends Enum<T>> extends ResolutionPhase<T> {
	private HashMap<Signature, Set<Symbol<T>>> map;

	public MapResolutionPhase(Privilege requiredPrivileges) {
		super(requiredPrivileges);
		map = new HashMap<Signature, Set<Symbol<T>>>();
	}

	@Override
	public boolean addSymbol(Symbol<T> symbol) {
		Set<Symbol<T>> set = map.getOrDefault(symbol.getSignature(), null);

		if (set == null) {
			set = new HashSet<Symbol<T>>();
			map.put(symbol.getSignature(), set);
		}

		return set.add(symbol);
	}

	@Override
	public ImmutableSet<Symbol<T>> getSymbols(Privilege providedPrivileges,
			Signature signature) {
		if (!this.getRequiredPrivileges().accepts(providedPrivileges)) {
			return ImmutableSet.of();
		}

		Set<Symbol<T>> set = map.getOrDefault(signature, null);
		ImmutableSet.Builder<Symbol<T>> builder = ImmutableSet.builder();

		if (set != null) {
			for (Symbol<T> symbol : set) {
				if (signature.distance(
						symbol.getSignature()) != Signature.INFINITY) {
					builder.add(symbol);
				}
			}
		}

		return builder.build();
	}

	@Override
	public boolean removeSymbol(Symbol<T> symbol) {
		Set<Symbol<T>> set = map.getOrDefault(symbol.getSignature(), null);

		if (set != null) {
			return set.remove(symbol);
		}

		return false;
	}

}
