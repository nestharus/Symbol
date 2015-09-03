package com.hiveworkshop.symbol;

import java.util.LinkedList;
import java.util.List;

public abstract class Query
{
	protected Symbol		origin;
	protected List<Symbol>	path;

	public Query(Symbol origin)
	{
		this.origin = origin;
		path = new LinkedList<Symbol>();
	}

	public List<Symbol> getPath()
	{
		return path;
	}

	public abstract boolean search(Signature signature);
}
