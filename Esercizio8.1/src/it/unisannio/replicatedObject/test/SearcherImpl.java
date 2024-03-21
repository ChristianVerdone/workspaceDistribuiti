package it.unisannio.replicatedObject.test;

public class SearcherImpl implements Searcher {

	@Override
	public boolean find(Object key) {
		String in = (String) key;
		boolean result = in.length() % 2 == 0;

		for (long i = 0; i < 100000L; i++) {
			for (long j = 0; j < 100L; j++);
		}
		return result;
	}

}
