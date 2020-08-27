package com.openthinks.wx.hack.http.base.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Operations implements Runnable {
	private final List<Operation<? extends Object>> operations = new ArrayList<Operation<? extends Object>>();
	private final Map<String, Object> context = new HashMap<String, Object>();

	public final static Operations buid(Operation<? extends Object> op) {
		Operations ops = new Operations();
		ops.add(op);
		return ops;
	}

	public final void clear() {
		operations.clear();
	}

	public final void add(Operation<? extends Object> operation) {
		if (operation == null)
			return;
		operations.add(operation);
		operation.setContext(context);
	}

	public final int size() {
		return operations.size();
	}

	@SuppressWarnings("unchecked")
	public final <T extends Object> T getFirstResult() {
		T opRst = null;
		if (size() > 0) {
			Operation<? extends Object> op = operations.get(0);
			opRst = (T) op.getResult();
		}
		return opRst;
	}

	public final List<Operation<? extends Object>> getAll() {
		return operations;
	}

	public final Object getContext(String key) {
		return context.get(key);
	}

	public void run() {
		for (int i = 0, j = operations.size(); i < j; i++) {
			Operation<?> op = operations.get(i);
			try {
				op.run();
			} catch (Exception e) {
				if (op.isContinueWhenError()) {
					continue;
				} else {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
